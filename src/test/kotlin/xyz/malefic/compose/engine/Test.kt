package xyz.malefic.compose.engine

import androidx.compose.runtime.currentComposer
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions

fun main() {
    val testFunctions =
        TestContainer::class
            .functions
            .filter { it.findAnnotation<ComposableTest>() != null }

    println("Available tests:")
    testFunctions.forEachIndexed { index, function ->
        println("${index + 1}: ${function.name}")
    }

    print("Enter the number of the test to run: ")
    val choice = readlnOrNull()?.toIntOrNull()

    if (choice != null && choice in 1..testFunctions.size) {
        val selectedFunction = testFunctions[choice - 1]
        println("Selected function: ${selectedFunction.name} with params ${selectedFunction.parameters}")
        val testContainer = TestContainer()
        application {
            Window(onCloseRequest = ::exitApplication, title = selectedFunction.name) {
                selectedFunction.call(testContainer, currentComposer, 0)
            }
        }
    } else {
        println("Invalid choice.")
    }
}

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class ComposableTest
