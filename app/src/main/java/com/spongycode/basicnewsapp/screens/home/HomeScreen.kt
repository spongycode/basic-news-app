package com.spongycode.basicnewsapp.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.spongycode.basicnewsapp.screens.components.NewsItem
import com.spongycode.basicnewsapp.screens.components.Topbar
import com.spongycode.basicnewsapp.util.Constants
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val news = viewModel.news

    Scaffold(topBar = {
        Topbar("Latest News")
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn {
                items(news) { news ->
                    Spacer(modifier = Modifier.height(Constants.SMALL_HEIGHT))
                    val url = news.url.split("//")[1]
                    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())

                    NewsItem(
                        title = news.title,
                        timesAgo = news.timesAgo,
                        imageUrl = news.imageUrl,
                        onClick = {
                            navController.navigate("details/${encodedUrl}")
                        }
                    )
                }
            }
        }
    }
}