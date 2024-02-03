package com.spongycode.basicnewsapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spongycode.basicnewsapp.R
import com.spongycode.basicnewsapp.util.Constants
import com.spongycode.basicnewsapp.util.Fonts


@Preview
@Composable
fun NewsItem(
    title: String = "Title of the news",
    timesAgo: String = "23m ago",
    onClick: () -> Unit = {},
    bannerId: Int = R.drawable.ic_launcher_background
) {
    val configuration = LocalConfiguration.current
    val width = (configuration.screenWidthDp - 10)
    val height = (configuration.screenHeightDp / 5)
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .clip(RoundedCornerShape(Constants.VERY_SMALL_HEIGHT))
            .width(width.dp)
            .height(height.dp)
            .clickable {
                onClick()
            },
    ) {
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(Constants.VERY_SMALL_HEIGHT))
        ) {

            Image(
                painter = painterResource(id = bannerId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
            )
        }

        Box(
            modifier = Modifier
                .width(width.dp)
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
                        color = Color(0xFF0C336F),
                        shape = RoundedCornerShape(0.dp),
                    )
                    .padding(horizontal = 5.dp),
                text = timesAgo,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.W500,
                color = Color.White,
                fontFamily = Fonts.poppinsFamily
            )
        }
    }
}