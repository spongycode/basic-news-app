package com.spongycode.basicnewsapp.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.spongycode.basicnewsapp.R
import com.spongycode.basicnewsapp.util.Constants
import com.spongycode.basicnewsapp.util.Fonts
import com.spongycode.basicnewsapp.util.TimesAgo
import com.spongycode.basicnewsapp.util.bounceClick

@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    title: String = "Title of the news",
    timesAgo: String = "23m ago",
    imageUrl: String = "image.com",
    onClick: () -> Unit = {},
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = modifier
            .bounceClick(0.98f)
            .clip(RoundedCornerShape(Constants.VERY_SMALL_HEIGHT))
            .clickable {
                onClick()
            },
    ) {
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(Constants.VERY_SMALL_HEIGHT))
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.news_placeholder_logo),
                contentDescription = null,
                error = painterResource(id = R.drawable.news_placeholder_logo),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xFF000000)),
                        startY = 0f,
                        endY = 100f
                    )
                )
                .padding(10.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.W600,
                color = Color.White,
                fontFamily = Fonts.poppinsFamily,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {

            Text(
                modifier = Modifier
                    .background(
                        color = Color(0xFF64B45D),
                        shape = RoundedCornerShape(bottomStart = Constants.VERY_SMALL_HEIGHT),
                    )
                    .padding(horizontal = 10.dp, vertical = 3.dp),
                text = TimesAgo.getTimeAgo(timesAgo),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.W500,
                color = Color.White,
                fontFamily = Fonts.poppinsFamily
            )
        }
    }
}
