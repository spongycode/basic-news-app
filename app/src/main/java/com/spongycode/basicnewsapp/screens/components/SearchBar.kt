package com.spongycode.basicnewsapp.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String = "",
    onChange: (String) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = text,
        onValueChange = { onChange(it) },
        placeholder = {
            Text(
                color = MaterialTheme.colorScheme.inversePrimary, text = "Search news..."
            )
        },
        modifier = modifier.padding(vertical = 5.dp),
        shape = RoundedCornerShape(28),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            focusManager.clearFocus()
        }),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.LightGray,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        trailingIcon = {
            Box(
                modifier = Modifier.padding(end = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (text.isNotBlank()) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "clear text",
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    onChange("")
                                },
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
        },
        textStyle = TextStyle(
            fontWeight = FontWeight.W500, fontSize = 18.sp
        )
    )
}