package com.spongycode.basicnewsapp.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
    val filteredNews: SnapshotStateList<News> = SnapshotStateList()

    private val _queryText = mutableStateOf("")
    val queryText: State<String> = _queryText

    private val _isAscending = mutableStateOf(false)
    val isAscending: State<Boolean> = _isAscending

    private val _shouldFilterSortDialog = mutableStateOf(false)
    val shouldFilterSortDialog: State<Boolean> = _shouldFilterSortDialog

    init {
        fetchNews()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.EnteredSearchQuery -> {
                _queryText.value = event.queryText
                filter()
            }

            is HomeEvent.PressedSortingFilter -> {
                _isAscending.value = !_isAscending.value
                sort()
                _shouldFilterSortDialog.value = false
            }
        }
    }

    fun toggleVisibilityFilterSortDialog() {
        _shouldFilterSortDialog.value = !_shouldFilterSortDialog.value
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                newsRepository.getNews()?.let {
                    _news.addAll(it.sortedByDescending { news -> news.timesAgo })
                    filter()
                }
            } catch (_: Exception) {
            }
        }
    }


    private fun sort() {
        val sortedList =
            if (isAscending.value) filteredNews.sortedBy { news -> news.timesAgo }
            else filteredNews.sortedByDescending { news -> news.timesAgo }
        filteredNews.clear()
        filteredNews.addAll(sortedList)
    }

    private fun filter() {
        val filteredNewsList = _news.filter { item ->
            item.title.contains(_queryText.value, ignoreCase = true)
        }
        filteredNews.clear()
        filteredNews.addAll(filteredNewsList)
    }
}
