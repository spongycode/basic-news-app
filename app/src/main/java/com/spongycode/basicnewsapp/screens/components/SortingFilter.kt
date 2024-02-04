package com.spongycode.basicnewsapp.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.spongycode.basicnewsapp.R
import com.spongycode.basicnewsapp.util.bounceClick

@Composable
fun SortingFilter(
    modifier: Modifier = Modifier,
    isAscending: Boolean = false,
    onShowDialog: () -> Unit = {}
) {
    Icon(
        modifier = modifier
            .bounceClick(0.98f)
            .size(40.dp)
            .clickable { onShowDialog() },
        painter = painterResource(id = if (isAscending) R.drawable.asc_filter else R.drawable.desc_filter),
        contentDescription = null
    )
}