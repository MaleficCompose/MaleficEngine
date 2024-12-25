package xyz.malefic.compose.engine

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import xyz.malefic.compose.engine.factory.BoxFactory
import xyz.malefic.compose.engine.factory.ColumnFactory
import xyz.malefic.compose.engine.factory.RowFactory
import xyz.malefic.compose.engine.factory.TextFactory
import xyz.malefic.compose.engine.pocket.background
import xyz.malefic.compose.engine.pocket.divide
import xyz.malefic.compose.engine.pocket.outline
import xyz.malefic.compose.engine.pocket.padding
import xyz.malefic.compose.engine.pocket.tooltip

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
                            .padding(horizontal = 3.dp)
                            .background()
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
            TextTest()
        }
    }
