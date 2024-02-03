package com.spongycode.basicnewsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.spongycode.basicnewsapp.components.NewsItem
import com.spongycode.basicnewsapp.components.Topbar
import com.spongycode.basicnewsapp.data.model.News
import com.spongycode.basicnewsapp.ui.theme.BasicNewsAppTheme
import com.spongycode.basicnewsapp.util.Constants

val dummyList = listOf(
    News(
        "Is this what an early-stage slowdown looks like?",
        "image.com",
        "13m ago"
    ),
    News(
        "Tangerine’s pretty self-care app combines habit and mood tracking",
        "image.com",
        "20m ago"
    )
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicNewsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp(
                        dummyList
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun MainApp(
    newsItemList: List<News> = emptyList()
) {
    Scaffold(topBar = {
        Topbar("Latest News")
    }) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding())
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn {
                items(newsItemList) { news ->
                    repeat(4) {
                        Spacer(modifier = Modifier.height(Constants.SMALL_HEIGHT))
                        NewsItem(title = news.title, timesAgo = news.timesAgo)
                    }
                }
            }
        }
    }
}