package xyz.malefic.compose.engine.factory

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A factory class for creating a Composable Card with customizable properties.
 *
 * This class implements the ComposableFactory interface and provides a method
 * to compose a Card with specified attributes such as modifier, shape, background
 * color, content color, border, and elevation. The content of the Card is defined
 * by a Composable lambda passed to the constructor.
 *
 * @property modifier Modifier to be applied to the Card.
 * @property shape Shape of the Card, defaults to MaterialTheme's medium shape if null.
 * @property backgroundColor Background color of the Card, defaults to MaterialTheme's surface color if null.
 * @property contentColor Content color of the Card, defaults to a color derived from the background color if null.
 * @property border Optional border to be applied to the Card.
 * @property elevation Elevation of the Card, defaults to 1.dp.
 * @property content Composable lambda defining the content inside the Card.
 */
class CardFactory(
    var modifier: Modifier = Modifier,
    var shape: Shape? = null,
    var backgroundColor: Color? = null,
    var contentColor: Color? = null,
    var border: BorderStroke? = null,
    var elevation: Dp = 1.dp,
    var content: @Composable () -> Unit,
) : ComposableFactory {
    /**
     * Composes a Card with the specified properties.
     *
     * @return A composable lambda that creates a Card with the given modifier,
     * shape, background color, content color, border, and elevation, containing
     * the specified content.
     */
    @Composable
    override fun compose(): @Composable () -> Unit =
        {
            Card(
                modifier = modifier,
                shape = shape ?: MaterialTheme.shapes.medium,
                backgroundColor = backgroundColor ?: MaterialTheme.colors.surface,
                contentColor = contentColor ?: contentColorFor(backgroundColor ?: MaterialTheme.colors.surface),
                border = border,
                elevation = elevation,
            ) {
                content()
            }
        }
}
