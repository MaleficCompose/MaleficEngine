package xyz.malefic.compose.engine

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import xyz.malefic.compose.engine.factory.ButtonFactory
import xyz.malefic.compose.engine.factory.ColumnFactory
import xyz.malefic.compose.engine.factory.RowFactory
import xyz.malefic.compose.engine.factory.TextFactory
import xyz.malefic.compose.engine.factory.div
import xyz.malefic.compose.engine.factory.divAssign
import xyz.malefic.compose.engine.factory.timesAssign
import xyz.malefic.compose.engine.fuel.background
import xyz.malefic.compose.engine.fuel.center
import xyz.malefic.compose.engine.fuel.divide
import xyz.malefic.compose.engine.fuel.outline
import xyz.malefic.compose.engine.fuel.padding
import xyz.malefic.compose.engine.fuel.space
import xyz.malefic.compose.engine.fuel.tooltip

@Suppress("unused")
internal class TestContainer {
    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    @ComposableTest
    fun ButtonTest() {
        val text1 = "Hello World!"
        val text2 = "Goodbye World!"
        var buttonText by remember { mutableStateOf(text1) }

        MaterialTheme {
            ColumnFactory {
                ButtonFactory() / {
                    onClick = {
                        buttonText = if (buttonText == text1) text2 else text1
                    }
                    content = {
                        TextFactory(buttonText)()
                    }
                } *= {
                    tooltip {
                        TextFactory("Hello!")()
                    }
                    space(50.dp)
                }
                ButtonFactory() /= {
                    onClick = {
                        buttonText = if (buttonText == text1) text2 else text1
                    }
                    content = {
                        TextFactory(buttonText)()
                    }
                }
            } *= {
                center()
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    @ComposableTest
    fun TextTest() =
        MaterialTheme {
            ColumnFactory {
                TextFactory("Text 1") *= {
                    tooltip {
                        TextFactory("This is the tooltip of the first text component.") *= {
                            padding(3.dp)
                            background()
                            outline()()
                        }
                    }
                    divide(vertical = false)
                }
                TextFactory("Text 2")()
            } / {
                horizontalAlignment = Alignment.CenterHorizontally
            } *= {
                center()
            }
        }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    @ComposableTest
    fun TextTest2() =
        MaterialTheme {
            RowFactory {
                TextFactory("Text 1") *= {
                    tooltip {
                        TextFactory("This is the tooltip of the first text component.")()
                    }
                    divide()
                }
                TextFactory("Text 2")()
            } / {
                verticalAlignment = Alignment.CenterVertically
            } *= {
                center()
            }
        }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    @ComposableTest
    fun FuelFactoryText() =
        MaterialTheme {
            TextFactory() / {
                text = "Fuel Composable"
            } *= {
                tooltip {
                    TextFactory("This is a tooltip for the fuel composable").apply {
                        color = MaterialTheme.colors.background
                    } *= {
                        padding(3.dp)
                        background(MaterialTheme.colors.onBackground)
                        outline(MaterialTheme.colors.primary)
                    }
                }
                center()
            }
        }
}
