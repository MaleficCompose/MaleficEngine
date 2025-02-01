package xyz.malefic.compose.engine.factory

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import xyz.malefic.compose.engine.fuel.fuel

/**
 * An interface for factories that produce a composable function.
 */
interface ComposableFactory {
    /**
     * Provides a `fuel` instance initialized with the composable function
     * from this factory. This allows invoking the composable logic
     * through the `fuel` interface, enabling chaining and additional
     * composable operations.
     */
    val fuel: fuel
        get() = fuel(this)
    var mods: List<Modifier>

    /**
     * Creates a composable function.
     *
     * @return A composable lambda.
     */
    @Composable
    fun compose(): @Composable () -> Unit

    /**
     * Invokes the composed function.
     */
    @Composable
    operator fun invoke() = compose().invoke()
}
