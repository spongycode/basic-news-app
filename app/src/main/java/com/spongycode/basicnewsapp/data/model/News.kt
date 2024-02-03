package com.spongycode.basicnewsapp.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class News(
    val title: String,
    @SerialName("urlToImage") val imageUrl: String,
    @SerialName("publishedAt") val timesAgo: String,
    val url: String
)

@Serializable
data class JsonResponse(
    val status: String,
    val articles: List<News>
)
