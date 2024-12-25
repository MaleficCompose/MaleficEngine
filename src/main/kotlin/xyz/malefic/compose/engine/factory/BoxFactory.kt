package xyz.malefic.compose.engine.factory

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * A factory class for creating and invoking a composable Box layout.
 *
 * @constructor Rather than setting properties through the constructor, it is recommended to set them directly using `.apply` and property access syntax
 *
 * @property modifier The modifier to be applied to the Box.
 * @property contentAlignment The alignment of the content inside the Box.
 * @property propagateMinConstraints Whether to propagate minimum constraints to the Box.
 * @property content The composable content to be displayed inside the Box.
 */
class BoxFactory(
    var modifier: Modifier = Modifier,
    var contentAlignment: Alignment = Alignment.TopStart,
    var propagateMinConstraints: Boolean = false,
    var content: @Composable BoxScope.() -> Unit,
) : ComposableFactory {
    /**
     * Composes a Box layout with the specified properties.
     *
     * @return A composable lambda that creates a Box with the given modifier,
     * content alignment, and minimum constraints propagation, containing the specified content.
     */
    @Composable
    override fun compose(): @Composable () -> Unit =
        {
            Box(modifier, contentAlignment, propagateMinConstraints) {
                content()
            }
        }

    /**
     * Invokes the composable function created by the `compose` method.
     *
     * This method triggers the rendering of the Box layout with the specified
     * properties and content.
     */
    @Composable
    override fun invoke() = compose().invoke()

    /**
     * Applies a configuration block to the BoxFactory and then invokes the box.
     *
     * This method allows for modifying the BoxFactory's properties using the provided
     * lambda block, and subsequently renders the box with the updated configuration.
     *
     * @param block A lambda block to configure the BoxFactory properties.
     */
    @Composable
    fun applyAndInvoke(block: BoxFactory.() -> Unit) {
        block()
        invoke()
    }
}
