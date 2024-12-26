package xyz.malefic.compose.engine.pocket

import androidx.compose.runtime.Composable
import xyz.malefic.compose.engine.factory.ComposableFactory

/**
 * An operator function that allows a composable factory to be modified
 * with a block of code and then invoked. The block is executed in the
 * context of the factory, and the factory is invoked immediately after.
 *
 * @param block A lambda with receiver that modifies the factory.
 */
@Composable
operator fun <T : ComposableFactory> T.timesAssign(block: T.() -> Unit) {
    block()
    this()
}
