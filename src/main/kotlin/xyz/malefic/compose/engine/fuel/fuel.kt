package xyz.malefic.compose.engine.fuel

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import xyz.malefic.compose.engine.factory.BoxFactory
import xyz.malefic.compose.engine.factory.ComposableFactory
import xyz.malefic.compose.engine.factory.divAssign

/**
 * A class representing a composable container that holds a composable function.
 *
 * The `fuel` class allows the encapsulation and invocation of a composable function
 * through operator overloading. It provides functionality to invoke the stored
 * composable function directly or to apply additional composable logic before
 * invocation using the `timesAssign` operator.
 *
 * @property factory An optional value indicating the factory that produced this `fuel`
 * @property function A composable function to be executed when the `fuel` instance
 * is invoked. If null, the `fuel` instance will be generated from the factory.
 */
@Suppress("kotlin:S101", "ClassName")
class fuel(
    val factory: ComposableFactory? = null,
    var function: (@Composable () -> Unit)? = null,
) {
    init {
        require((factory != null) xor (function != null)) { "Either factory or function must be non-null, but not both." }
    }

    /**
     * A list of composable wrapper functions that can be applied to the main composable function.
     * Each wrapper function takes a composable function as input and executes something with it.
     */
    var wrappers: List<WrapperComposable> = emptyList<WrapperComposable>()

    /**
     * Invokes the composable function stored in this `fuel` instance.
     *
     * This operator function applies all the wrapper functions in the `wrappers` list
     * to the main composable function before invoking it.
     */
    @Composable
    operator fun invoke() {
        wrappers.fold(
            initial = function ?: factory!!.compose(),
            operation = { acc, wrapper -> { wrapper(acc) } },
        )()
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
        this()
    }

    /**
     * Applies the given `Modifier` to this `fuel` instance.
     *
     * If the `function` property is non-null, it wraps the original function
     * in a `BoxFactory` and applies the modifier using the `divAssign` operator.
     * If the `factory` property is non-null, it directly applies the modifier
     * to the factory's `mods` property.
     *
     * @param mod The `Modifier` to be applied.
     */
    @Composable
    fun mod(vararg mod: Modifier) =
        this.apply {
            function?.let { originalFunction ->
                function = {
                    BoxFactory {
                        originalFunction()
                    } /= {
                        mods += mod
                    }
                }
            } ?: run {
                factory!!.mods += mod
            }
        }
}

/**
 * A type alias for a composable wrapper function.
 *
 * `WrapperComposable` represents a function that takes a composable function as input
 * and applies additional composable logic to it.
 */
typealias WrapperComposable = @Composable (@Composable () -> Unit) -> Unit
