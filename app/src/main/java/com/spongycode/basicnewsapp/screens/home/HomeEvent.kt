package com.spongycode.basicnewsapp.screens.home

sealed class HomeEvent {
    data class EnteredSearchQuery(val queryText: String) : HomeEvent()
    data object PressedSortingFilter : HomeEvent()
}