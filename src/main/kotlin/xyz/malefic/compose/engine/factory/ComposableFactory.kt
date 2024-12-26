package xyz.malefic.compose.engine.factory

import androidx.compose.runtime.Composable

/**
 * An interface for factories that produce a composable function.
 */
interface ComposableFactory {
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
