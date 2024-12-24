package xyz.malefic.compose.engine

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun TextTest() =
    MaterialTheme {
        BoxFactory {
            ColumnFactory {
                TextFactory()
                    .apply {
                        text = "Text 1"
                    }.compose()
                    .tooltip(tooltip = {
                        TextFactory()
                            .apply {
                                text = "This is the tooltip of the first text component."
                            }.compose()
                            .background(mod = Modifier.padding(horizontal = 3.dp))
                            .outline()
                            .invoke()
                    })
                    .divide()
                    .invoke()
                TextFactory()
                    .applyAndInvoke {
                        text = "Text 2"
                    }
            }.applyAndInvoke {
                horizontalAlignment = Alignment.CenterHorizontally
                verticalArrangement = Arrangement.Center
            }
        }.applyAndInvoke {
            contentAlignment = Alignment.Center
            modifier = Modifier.fillMaxSize()
        }
    }

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun TextTest2() =
    MaterialTheme {
        BoxFactory {
            RowFactory {
                TextFactory()
                    .apply {
                        text = "Text 1"
                    }.compose()
                    .tooltip(tooltip = {
                        TextFactory().applyAndInvoke {
                            text = "This is the tooltip of the first text component."
                        }
                    })
                    .divide(isVertical = true)
                    .invoke()
                TextFactory()
                    .applyAndInvoke {
                        text = "Text 2"
                    }
            }.applyAndInvoke {
                verticalAlignment = Alignment.CenterVertically
                horizontalArrangement = Arrangement.Center
                modifier = Modifier.fillMaxSize()
            }
        }.applyAndInvoke {
            contentAlignment = Alignment.Center
            modifier = Modifier.fillMaxSize()
        }
    }

fun main() =
    application {
        Window(onCloseRequest = ::exitApplication, title = "Text Test") {
            TextTest2()
        }
    }
