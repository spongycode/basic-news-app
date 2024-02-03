package com.spongycode.basicnewsapp.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object TimesAgo {

    fun getTimeAgo(time: String): String {
        val parts = time.split('Z')
        val serverTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(parts[0])
        val serverCalendar = Calendar.getInstance().apply { timeInMillis = serverTime?.time ?: 0 }

        val localCalendar = Calendar.getInstance()
        val durationMillis = localCalendar.timeInMillis - serverCalendar.timeInMillis

        val sdf = SimpleDateFormat("MMM d 'at' HH:mm", Locale.getDefault())

        return when {
            durationMillis >= 2 * 24 * 60 * 60 * 1000 -> sdf.format(serverCalendar.time)

            durationMillis >= 24 * 60 * 60 * 1000 -> "${durationMillis / (24 * 60 * 60 * 1000)} days ago"

            durationMillis >= 60 * 60 * 1000 -> "${durationMillis / (60 * 60 * 1000)} hour${if (durationMillis >= 2 * 60 * 60 * 1000) "s" else ""} ago"
            durationMillis >= 60 * 1000 -> "${durationMillis / (60 * 1000)} min${if (durationMillis >= 2 * 60 * 1000) "s" else ""} ago"
            else -> "${durationMillis / 1000} sec${if (durationMillis >= 2000) "s" else ""} ago"
        }
    }
}
