package xyz.malefic.compose.engine

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.runBlocking
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions

fun main() =
    runBlocking {
        val testFunctions =
            TestContainer::class
                .functions
                .filter { it.findAnnotation<ComposableTest>() != null }

        application {
            var showDialog by remember { mutableStateOf(true) }
            var currentFunction by remember { mutableStateOf(testFunctions.first()) }

            Window(
                onCloseRequest = {
                    showDialog = false
                    exitApplication()
                },
                title = currentFunction.name,
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    currentFunction.call(TestContainer(), currentComposer, 0)

                    if (showDialog) {
                        DialogWindow(onCloseRequest = { showDialog = false }) {
                            Column {
                                testFunctions.forEach { function ->
                                    Button(onClick = {
                                        currentFunction = function
                                        showDialog = false
                                    }) {
                                        Text(function.name)
                                    }
                                }
                            }
                        }
                    }

                    IconButton(
                        onClick = { showDialog = true },
                        modifier = Modifier.align(Alignment.TopEnd).padding(8.dp),
                    ) {
                        Icon(Icons.Filled.Settings, contentDescription = "Change Function")
                    }
                }
            }
        }
    }

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class ComposableTest
