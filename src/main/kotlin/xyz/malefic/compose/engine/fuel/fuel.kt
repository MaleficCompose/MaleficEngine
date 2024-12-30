package xyz.malefic.compose.engine.fuel

import androidx.compose.runtime.Composable

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
     * Applies the given composable block to this `fuel` instance and then invokes
     * the composable function stored in this instance. This operator function
     * allows chaining of composable logic with the `fuel` object.
     *
     * @param block A composable extension function on `fuel` to be executed before
     * invoking the main composable function.
     */
    @Composable
    operator fun times(block: @Composable fuel.() -> Unit) {
        block()
        this()
    }
}
