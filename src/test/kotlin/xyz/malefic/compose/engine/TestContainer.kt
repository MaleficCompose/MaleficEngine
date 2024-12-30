package xyz.malefic.compose.engine

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import xyz.malefic.compose.engine.factory.*
import xyz.malefic.compose.engine.fuel.center
import xyz.malefic.compose.engine.fuel.fuel
import xyz.malefic.compose.engine.fuel.tooltip
import xyz.malefic.compose.engine.pocket.*

@Suppress("unused")
internal class TestContainer {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    @Preview
    @ComposableTest
    fun ButtonTest() {
        var buttonText by remember { mutableStateOf("Option 1") }

        MaterialTheme {
            BoxFactory {
                ColumnFactory {
                    ButtonFactory()
                        .apply {
                            onClick = {
                                buttonText = if (buttonText == "Option 1") "Option 2" else "Option 1"
                            }
                            content = {
                                TextFactory(buttonText)()
                            }
                        }.compose()
                        .tooltip {
                            TextFactory("Hello!")()
                        }.space(width = 8.dp)()
                    ButtonFactory() *= {
                        onClick = {
                            buttonText = if (buttonText == "Option 1") "Option 2" else "Option 1"
                        }
                        content = {
                            TextFactory(buttonText)()
                        }
                    }
                } *= {
                    horizontalAlignment = Alignment.CenterHorizontally
                    verticalArrangement = Arrangement.Center
                }
            } *= {
                contentAlignment = Alignment.Center
                modifier = Modifier.fillMaxSize()
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    @Preview
    @ComposableTest
    fun TextTest() =
        MaterialTheme {
            BoxFactory {
                ColumnFactory {
                    TextFactory()
                        .apply {
                            text = "Text 1"
                        }.compose()
                        .tooltip {
                            TextFactory()
                                .apply {
                                    text = "This is the tooltip of the first text component."
                                }.compose()
                                .padding(3.dp)
                                .background()
                                .outline()()
                        }.divide()()
                    TextFactory("Text 2")()
                } *= {
                    horizontalAlignment = Alignment.CenterHorizontally
                    verticalArrangement = Arrangement.Center
                }
            } *= {
                contentAlignment = Alignment.Center
                modifier = Modifier.fillMaxSize()
            }
        }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    @Preview
    @ComposableTest
    fun TextTest2() =
        MaterialTheme {
            BoxFactory {
                RowFactory {
                    TextFactory("Text 1")
                        .compose()
                        .tooltip {
                            TextFactory("This is the tooltip of the first text component.")()
                        }.divide(vertical = true)()
                    TextFactory("Text 2")()
                } *= {
                    verticalAlignment = Alignment.CenterVertically
                    horizontalArrangement = Arrangement.Center
                    modifier = Modifier.fillMaxSize()
                }
            } *= {
                contentAlignment = Alignment.Center
                modifier = Modifier.fillMaxSize()
            }
        }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    @Preview
    @ComposableTest
    fun FuelTest() {
        MaterialTheme {
            fuel {
                TextFactory("Fuel Composable")()
            } * {
                tooltip {
                    TextFactory("This is a tooltip for the fuel composable.")
                        .apply {
                            color = Color.White
                        }.compose()
                        .padding(3.dp)
                        .background(color = Color.Black)
                        .outline(color = Color.Red)()
                }
                center()
            }
        }
    }
}
