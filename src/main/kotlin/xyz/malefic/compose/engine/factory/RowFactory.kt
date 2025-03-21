package xyz.malefic.compose.engine.factory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * A factory class for creating and managing a composable Row layout.
 *
 * @constructor Rather than setting properties through the constructor, it is recommended to set them directly using `.apply` and property access syntax
 *
 * @property modifier The modifier to be applied to the Row.
 * @property horizontalArrangement The horizontal arrangement of the Row's children.
 * @property verticalAlignment The horizontal alignment of the Row's children.
 * @property content The composable content to be displayed within the Row.
 *
 * This class implements the ComposableFactory interface, providing a method to
 * create a composable Row with specified arrangements and alignment. It also
 * includes a method to apply modifications and invoke the composable function.
 */
class RowFactory(
    var modifier: Modifier = Modifier,
    var horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    var verticalAlignment: Alignment.Vertical = Alignment.Top,
    var content: @Composable RowScope.() -> Unit,
) : ComposableFactory {
    override var mods = listOf(modifier)

    /**
     * Composes a Row layout with the specified modifier, horizontal arrangement,
     * vertical alignment, and content. This method returns a composable function
     * that can be invoked to render the Row.
     *
     * @return A composable function that renders the Row with the configured properties.
     */
    @Composable
    override fun compose(): @Composable () -> Unit =
        {
            Row(mods.combined, horizontalArrangement, verticalAlignment) {
                content()
            }
        }
}
