package com.spongycode.basicnewsapp.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.spongycode.basicnewsapp.screens.components.NewsItem
import com.spongycode.basicnewsapp.screens.components.FilterSortDialog
import com.spongycode.basicnewsapp.screens.components.PlaceholderMessageText
import com.spongycode.basicnewsapp.screens.components.SearchBar
import com.spongycode.basicnewsapp.screens.components.SortingFilter
import com.spongycode.basicnewsapp.screens.components.Topbar
import com.spongycode.basicnewsapp.util.Constants
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val news = viewModel.filteredNews
    val queryText = viewModel.queryText
    val configuration = LocalConfiguration.current
    val width = configuration.screenWidthDp
    val height = configuration.screenHeightDp
    val shouldFilterSortDialog = viewModel.shouldFilterSortDialog


    Scaffold(topBar = {
    }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Topbar(
                    title = "Latest News",
                    modifier = Modifier.height((height / 12).dp)
                )
            }
            stickyHeader {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .width((width - 10).dp)
                        .background(Color.LightGray)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SearchBar(
                            modifier = Modifier
                                .width((width * 0.85).dp)
                                .background(
                                    MaterialTheme.colorScheme.background
                                ),
                            text = queryText.value,
                            onChange = { text ->
                                viewModel.onEvent(
                                    HomeEvent.EnteredSearchQuery(
                                        text
                                    )
                                )
                            }
                        )
                        SortingFilter(
                            modifier = Modifier
                                .width((width * 0.15).dp)
                                .background(MaterialTheme.colorScheme.background),
                            onShowDialog = { viewModel.toggleVisibilityFilterSortDialog() },
                            isAscending = viewModel.isAscending.value
                        )
                    }
                }
            }
            items(news) { news ->
                Spacer(modifier = Modifier.height(Constants.SMALL_HEIGHT))
                val url = news.url.split("//")[1]
                val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())

                NewsItem(
                    modifier = Modifier
                        .width((width - 10).dp)
                        .height((height / 5).dp),
                    title = news.title,
                    timesAgo = news.timesAgo,
                    imageUrl = news.imageUrl,
                    onClick = {
                        navController.navigate("details/${encodedUrl}")
                    }
                )
            }
        }
        when (viewModel.homeState.value) {
            HomeState.Error -> PlaceholderMessageText("Oops, some error occurred.")
            HomeState.Loading -> PlaceholderMessageText("Loading latest news..")
            HomeState.Success -> {
                Unit
            }
        }
    }
    if (shouldFilterSortDialog.value) {
        FilterSortDialog(
            isAscending = viewModel.isAscending.value,
            onToggleSort = { viewModel.onEvent(HomeEvent.PressedSortingFilter) },
            onDismissRequest = { viewModel.toggleVisibilityFilterSortDialog() }
        )
    }
}