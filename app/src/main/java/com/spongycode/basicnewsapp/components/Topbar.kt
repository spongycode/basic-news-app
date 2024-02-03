package com.spongycode.basicnewsapp.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.spongycode.basicnewsapp.util.Fonts


@ExperimentalMaterial3Api
@Composable
fun Topbar(title: String) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                color = MaterialTheme.colorScheme.primary,
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Fonts.poppinsFamily
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}
