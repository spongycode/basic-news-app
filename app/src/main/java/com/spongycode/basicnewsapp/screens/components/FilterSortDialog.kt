package com.spongycode.basicnewsapp.screens.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.spongycode.basicnewsapp.util.Constants
import com.spongycode.basicnewsapp.util.Fonts

@Composable
fun FilterSortDialog(
    title: String = "Select sorting order",
    isAscending: Boolean = true,
    onToggleSort: () -> Unit = {},
    onDismissRequest: () -> Unit = {}
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Box(
            Modifier
                .clip(RectangleShape)
                .background(Color.White)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.W600,
                    color = Color.Black,
                    fontFamily = Fonts.poppinsFamily
                )
                Spacer(modifier = Modifier.height(Constants.SMALL_HEIGHT))
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = isAscending,
                            onClick = {
                                if (!isAscending) {
                                    onToggleSort()
                                }
                            }
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = isAscending, onClick = {
                        if (!isAscending) {
                            onToggleSort()
                        }
                    })
                    Text(
                        text = "Ascending order",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.W500,
                        color = Color.Black,
                        fontFamily = Fonts.poppinsFamily
                    )
                }
                Spacer(modifier = Modifier.height(Constants.VERY_SMALL_HEIGHT))
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = !isAscending,
                            onClick = {
                                if (isAscending) {
                                    onToggleSort()
                                }
                            }
                        )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(selected = !isAscending, onClick = {
                        if (isAscending) {
                            onToggleSort()
                        }
                    })
                    Text(
                        text = "Descending order",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.W500,
                        color = Color.Black,
                        fontFamily = Fonts.poppinsFamily
                    )
                }
            }
        }
    }
}
