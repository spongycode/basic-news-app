package com.spongycode.basicnewsapp.screens.components


import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.spongycode.basicnewsapp.util.Constants
import com.spongycode.basicnewsapp.util.Fonts


const val column1Weight = .5f
const val column2Weight = .5f

@Composable
fun DataPayloadDialog(
    bundle: Bundle = Bundle(),
    onDismissRequest: () -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val height = (configuration.screenHeightDp / 2)
    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .height(height.dp)
                .background(Color.White)
                .padding(vertical = 20.dp)
                .wrapContentWidth()
        ) {
            Column(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Data Payloads",
                    fontWeight = FontWeight.W600,
                    fontSize = 18.sp,
                    fontFamily = Fonts.poppinsFamily
                )
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    NormalText(text = "key", weight = column1Weight, title = true)
                    NormalText(text = "value", weight = column2Weight, title = true)
                }
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
                LazyColumn {
                    for (key in bundle.keySet()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(3.dp)
                                    .wrapContentHeight()
                                    .clip(RoundedCornerShape(Constants.VERY_SMALL_HEIGHT))
                                    .background(Color(0xFFCAF7E7)),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 5.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    NormalText(text = key, weight = column1Weight)
                                    NormalText(
                                        text = bundle.get(key).toString(),
                                        weight = column2Weight
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RowScope.NormalText(
    text: String,
    weight: Float,
    alignment: TextAlign = TextAlign.Center,
    title: Boolean = false
) {
    Text(
        text = text,
        Modifier
            .weight(weight)
            .padding(horizontal = 10.dp, vertical = if (title) 10.dp else 0.dp)
            .basicMarquee(),
        fontWeight = if (title) FontWeight.Medium else FontWeight.W500,
        textAlign = alignment,
        fontSize = if (title) 18.sp else 15.sp,
        fontFamily = Fonts.poppinsFamily
    )
}