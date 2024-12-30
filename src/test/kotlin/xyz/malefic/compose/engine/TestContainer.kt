package xyz.malefic.compose.engine

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import xyz.malefic.compose.engine.factory.*
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
        var buttonText by remember { mutableStateOf("Option 1") }

        MaterialTheme {
            BoxFactory {
                ColumnFactory {
                    ButtonFactory() / {
                        onClick = {
                            buttonText = if (buttonText == "Option 1") "Option 2" else "Option 1"
                        }
                        content = {
                            TextFactory(buttonText)()
                        }
                    } *= {
                        tooltip {
                            TextFactory("Hello!")()
                        }
                        space(8.dp)
                    }
                    ButtonFactory() /= {
                        onClick = {
                            buttonText = if (buttonText == "Option 1") "Option 2" else "Option 1"
                        }
                        content = {
                            TextFactory(buttonText)()
                        }
                    }
                } /= {
                    horizontalAlignment = Alignment.CenterHorizontally
                    verticalArrangement = Arrangement.Center
                }
            } /= {
                contentAlignment = Alignment.Center
                modifier = Modifier.fillMaxSize()
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    @ComposableTest
    fun TextTest() =
        MaterialTheme {
            BoxFactory {
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
                } /= {
                    horizontalAlignment = Alignment.CenterHorizontally
                    verticalArrangement = Arrangement.Center
                }
            } /= {
                contentAlignment = Alignment.Center
                modifier = Modifier.fillMaxSize()
            }
        }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    @ComposableTest
    fun TextTest2() =
        MaterialTheme {
            BoxFactory {
                RowFactory {
                    TextFactory("Text 1") *= {
                        tooltip {
                            TextFactory("This is the tooltip of the first text component.")()
                        }
                        divide()
                    }
                    TextFactory("Text 2")()
                } /=
                    {
                        verticalAlignment = Alignment.CenterVertically
                        horizontalArrangement = Arrangement.Center
                        modifier = Modifier.fillMaxSize()
                    }
            } /=
                {
                    contentAlignment = Alignment.Center
                    modifier = Modifier.fillMaxSize()
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
