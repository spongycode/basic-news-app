package com.spongycode.basicnewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.spongycode.basicnewsapp.screens.components.DataPayloadDialog
import com.spongycode.basicnewsapp.screens.components.NavContainer
import com.spongycode.basicnewsapp.ui.theme.BasicNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicNewsAppTheme {
                var showDataPayloadDialog by remember { mutableStateOf(true) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    intent.extras?.let {
                        if (it.get("type") != null && showDataPayloadDialog) {
                            DataPayloadDialog(
                                bundle = intent.extras!!,
                                onDismissRequest = { showDataPayloadDialog = false }
                            )
                        }
                    }
                    NavContainer(startDestination = "home")
                }
            }
        }
    }
}