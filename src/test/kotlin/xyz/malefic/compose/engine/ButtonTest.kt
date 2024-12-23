package xyz.malefic.compose.engine

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import xyz.malefic.compose.engine.button.ButtonFactory

@Composable
@Preview
fun ButtonTest() {
    var buttonText by remember { mutableStateOf("Option 1") }

    MaterialTheme {
        Surface(
            modifier =
                androidx.compose.ui.Modifier
                    .fillMaxSize(),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier =
                    androidx.compose.ui.Modifier
                        .fillMaxSize(),
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
                    .space()
                    .invoke()
                ButtonFactory()
                    .apply {
                        onClick = {
                            buttonText = if (buttonText == "Option 1") "Option 2" else "Option 1"
                        }
                        content = {
                            Text(buttonText)
                        }
                    }.invoke()
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
