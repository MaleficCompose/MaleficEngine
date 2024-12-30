package xyz.malefic.compose.engine.pocket

import androidx.compose.runtime.Composable
import xyz.malefic.compose.engine.factory.ComposableFactory
import xyz.malefic.compose.engine.fuel.fuel

/**
 * Applies a composable block to the current instance of a `ComposableFactory` and returns the instance.
 * This operator function allows chaining of composable logic using the division operator.
 *
 * If the block needs a @Composable reference, use .apply instead.
 *
 * @param block A composable extension function on the current instance to be executed.
 * @return The current instance after applying the block.
 */
operator fun <T : ComposableFactory> T.div(block: T.() -> Unit): T = this.apply(block)

/**
 * An operator function that allows a composable factory to be modified
 * with a block of code and then invoked. The block is executed in the
 * context of the factory, and the factory is invoked immediately after.
 *
 * Essentially, this function is a combination of apply then compose and invoke.
 *
 * @param block A lambda with receiver that modifies the factory.
 * @receiver A composable factory instance.
 */
@Composable
operator fun <T : ComposableFactory> T.divAssign(block: @Composable T.() -> Unit) {
    block()
    this()
}

/**
 * Applies a composable block to the `fuel` instance of a `ComposableFactory`. This operator function allows chaining of composable
 * logic with the `fuel` object, enabling additional composable operations.
 *
 * @param block A composable extension function on `fuel` to be executed before
 * invoking the main composable function.
 */
@Composable
operator fun <T : ComposableFactory> T.times(block: @Composable fuel.() -> Unit) =
    fuel.apply {
        block()
    }

/**
 * Applies a composable block to the `fuel` instance of a `ComposableFactory` and then
 * invokes the composable function. This operator function allows chaining of composable
 * logic with the `fuel` object, enabling additional composable operations.
 *
 * @param block A composable extension function on `fuel` to be executed before
 * invoking the main composable function.
 */
@Composable
operator fun <T : ComposableFactory> T.timesAssign(block: @Composable fuel.() -> Unit) {
    fuel.apply {
        block()
    }()
}
