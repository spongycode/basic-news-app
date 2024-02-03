package com.spongycode.basicnewsapp.data.repository

import com.spongycode.basicnewsapp.data.model.JsonResponse
import com.spongycode.basicnewsapp.data.model.News
import com.spongycode.basicnewsapp.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository {
    override suspend fun getNews(): List<News>? = withContext(Dispatchers.IO) {
        val connection = URL(Constants.URL).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Accept", "application/json")
        connection.connectTimeout = 10000
        connection.readTimeout = 10000
        connection.doInput = true
        connection.doOutput = false

        try {
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = InputStreamReader(connection.inputStream)
                reader.use { input ->
                    val response = StringBuilder()
                    val bufferedReader = BufferedReader(input)

                    bufferedReader.forEachLine {
                        response.append(it.trim())
                    }
                    return@withContext parseJsonResponse(response.toString())?.articles
                }
            }
        } catch (_: Exception) {
        }
        return@withContext null
    }
}

private val json = Json {
    ignoreUnknownKeys = true
}

fun parseJsonResponse(jsonString: String): JsonResponse? {
    return try {
        json.decodeFromString(jsonString)
    } catch (e: Exception) {
        null
    }
}