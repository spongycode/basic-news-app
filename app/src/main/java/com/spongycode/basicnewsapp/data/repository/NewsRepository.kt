package com.spongycode.basicnewsapp.data.repository

import com.spongycode.basicnewsapp.data.model.News

interface NewsRepository {
    suspend fun getNews(): List<News>?
}
