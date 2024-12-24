package xyz.malefic.compose.engine

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun TextTest() =
    MaterialTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                TextFactory()
                    .apply {
                        text = "Text 1"
                    }.compose()
                    .space()
                    .tooltip(tooltip = {
                        Text("Hello!")
                    })
                    .invoke()
                TextFactory()
                    .applyAndInvoke {
                        text = "Text 2"
                    }
            }
        }
    }

fun main() =
    application {
        Window(onCloseRequest = ::exitApplication, title = "Text Test") {
            TextTest()
        }
    }
