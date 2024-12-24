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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun ButtonTest() {
    var buttonText by remember { mutableStateOf("Option 1") }

    MaterialTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                ButtonFactory()
                    .apply {
                        onClick = {
                            buttonText = if (buttonText == "Option 1") "Option 2" else "Option 1"
                        }
                        content = {
                            Text(buttonText)
                        }
                    }.compose()
                    .tooltip(tooltip = {
                        Text("Hello!")
                    })
                    .space()
                    .invoke()
                ButtonFactory()
                    .applyAndInvoke {
                        onClick = {
                            buttonText = if (buttonText == "Option 1") "Option 2" else "Option 1"
                        }
                        content = {
                            Text(buttonText)
                        }
                    }
            }
        }
    }
}

fun main() =
    application {
        Window(onCloseRequest = ::exitApplication, title = "Button Test") {
            ButtonTest()
        }
    }
