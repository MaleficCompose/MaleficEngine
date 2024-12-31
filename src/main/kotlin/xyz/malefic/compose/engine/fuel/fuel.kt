package xyz.malefic.compose.engine.fuel

import androidx.compose.runtime.Composable

/**
 * A class representing a composable container that holds a composable function.
 *
 * The `fuel` class allows the encapsulation and invocation of a composable function
 * through operator overloading. It provides functionality to invoke the stored
 * composable function directly or to apply additional composable logic before
 * invocation using the `timesAssign` operator.
 *
 * @property function A composable function to be executed when the `fuel` instance
 * is invoked.
 */
@Suppress("kotlin:S101", "ClassName")
class fuel(
    var function: @Composable () -> Unit,
) {
    /**
     * Invokes the composable function stored in this `fuel` instance.
     * This operator function allows the `fuel` object to be called as a function,
     * executing the composable logic defined in the `function` property.
     */
    @Composable
    operator fun invoke() {
        function()
    }

    /**
     * Applies the given composable block to this `fuel` instance and returns the instance.
     * This operator function allows chaining of composable logic with the `fuel` object.
     *
     * @param block A composable extension function on `fuel` to be executed.
     * @return The current `fuel` instance after applying the block.
     */
    @Composable
    operator fun times(block: @Composable fuel.() -> Unit): fuel {
        block()
        return this
    }

    /**
     * Applies the given composable block to this `fuel` instance and then invokes
     * the composable function stored in this instance. This operator function
     * allows chaining of composable logic with the `fuel` object.
     *
     * @param block A composable extension function on `fuel` to be executed before
     * invoking the main composable function.
     */
    @Composable
    operator fun timesAssign(block: @Composable fuel.() -> Unit) {
        block()
        function()
    }
}
