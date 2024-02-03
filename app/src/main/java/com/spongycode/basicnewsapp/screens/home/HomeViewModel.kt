package com.spongycode.basicnewsapp.screens.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spongycode.basicnewsapp.data.model.News
import com.spongycode.basicnewsapp.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val _news = mutableStateListOf<News>()
    val news: SnapshotStateList<News> = _news

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                newsRepository.getNews()?.let {
                    _news.addAll(it)
                }
            } catch (_: Exception) {
            }
        }
    }
}