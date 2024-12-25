package xyz.malefic.compose.engine.factory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * A factory class for creating and managing a composable Column layout.
 *
 * @constructor Rather than setting properties through the constructor, it is recommended to set them directly using `.apply` and property access syntax
 *
 * @property modifier The modifier to be applied to the Column.
 * @property verticalArrangement The vertical arrangement of the Column's children.
 * @property horizontalAlignment The horizontal alignment of the Column's children.
 * @property content The composable content to be displayed within the Column.
 *
 * This class implements the ComposableFactory interface, providing a method to
 * create a composable Column with specified arrangements and alignment. It also
 * includes a method to apply modifications and invoke the composable function.
 */
class ColumnFactory(
    var modifier: Modifier = Modifier,
    var verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    var horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    var content: @Composable ColumnScope.() -> Unit,
) : ComposableFactory {
    /**
     * Creates a composable lambda function that renders a Column layout
     * with the specified modifier, vertical arrangement, and horizontal alignment.
     * The content of the Column is defined by the `content` property.
     *
     * @return A composable lambda function for rendering the Column.
     */
    @Composable
    override fun compose(): @Composable () -> Unit =
        {
            Column(modifier, verticalArrangement, horizontalAlignment) {
                content()
            }
        }

    /**
     * Invokes the composable function created by the `compose` method.
     * This method is part of the ComposableFactory interface implementation.
     */
    @Composable
    override fun invoke() = compose().invoke()

    /**
     * Applies the given block of modifications to the ColumnFactory instance
     * and then invokes the composable function. This allows for dynamic
     * configuration and immediate rendering of the Column layout.
     *
     * @param block A lambda function that applies modifications to the
     * ColumnFactory instance.
     */
    @Composable
    fun applyAndInvoke(block: ColumnFactory.() -> Unit) {
        block()
        invoke()
    }
}
