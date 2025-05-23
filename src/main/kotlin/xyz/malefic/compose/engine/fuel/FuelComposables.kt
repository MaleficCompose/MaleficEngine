package xyz.malefic.compose.engine.fuel

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import xyz.malefic.compose.engine.factory.BoxFactory
import xyz.malefic.compose.engine.factory.RowFactory
import xyz.malefic.compose.engine.factory.divAssign
import xyz.malefic.compose.engine.factory.timesAssign

/**
 * Adds a spacer to the composable function within the `fuel` instance.
 *
 * This method appends a spacer with optional height and width to the composable
 * function, allowing for flexible layout adjustments.
 *
 * @param height The optional height of the spacer. Defaults to null, meaning no height is applied.
 * @param width The optional width of the spacer. Defaults to null, meaning no height is applied.
 * @return The `fuel` instance with the added spacer.
 */
@Composable
fun fuel.space(
    height: Dp? = null,
    width: Dp? = null,
): fuel =
    this.apply {
        wrappers += @Composable { function ->
            function()
            Spacer(
                Modifier
                    .then(if (width != null) Modifier.width(width) else Modifier)
                    .then(if (height != null) Modifier.height(height) else Modifier),
            )
        }
    }

/**
 * Adds a divider to the composable function within the `fuel` instance.
 *
 * This method appends a divider with specified thickness and color to the
 * composable function, allowing for visual separation of content.
 *
 * @param thickness The thickness of the divider. Defaults to 1.dp.
 * @param color The color of the divider. Defaults to the color that goes on the background of the material theme.
 * @param vertical Whether the divider should be vertical. Defaults to true.
 * @param mod An optional [Modifier] to apply to the divider.
 * @return The `fuel` instance with the added divider.
 */
@Composable
fun fuel.divide(
    thickness: Dp = 1.dp,
    color: Color = MaterialTheme.colors.onBackground,
    vertical: Boolean = true,
    mod: Modifier = Modifier.Companion,
): fuel =
    this.apply {
        wrappers += @Composable { function ->
            function()
            BoxFactory() *= {
                background(
                    color,
                    mod =
                        mod.then(
                            if (vertical) {
                                Modifier.fillMaxHeight().width(thickness)
                            } else {
                                Modifier.fillMaxWidth().height(thickness)
                            },
                        ),
                )
            }
        }
    }

/**
 * Adds a text element to the composable function within the `fuel` instance.
 *
 * @param text The text to be displayed.
 * @return The `fuel` instance with the added text element.
 */
@Composable
fun fuel.text(text: String) =
    this.apply {
        wrappers += @Composable { function ->
            function()
            Text(text = text)
        }
    }

/**
 * Adds a button element to the composable function within the `fuel` instance.
 *
 * @param onClick The callback to be invoked when the button is clicked.
 * @param content The composable content to be displayed inside the button.
 * @return The `fuel` instance with the added button element.
 */
@Composable
fun fuel.button(
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) = this.apply {
    wrappers += @Composable { function ->
        function()
        Button(onClick = onClick) {
            content()
        }
    }
}

/**
 * Adds an image element to the composable function within the `fuel` instance.
 *
 * @param painter The painter to draw the image.
 * @param contentDescription The content description for the image.
 * @return The `fuel` instance with the added image element.
 */
@Composable
fun fuel.image(
    painter: Painter,
    contentDescription: String?,
) = this.apply {
    wrappers += @Composable { function ->
        function()
        Image(painter = painter, contentDescription = contentDescription)
    }
}

/**
 * Adds a row layout to the composable function within the `fuel` instance.
 *
 * @param mod The modifier to be applied to the row.
 * @param horizontal The horizontal arrangement of the row's children.
 * @param vertical The vertical alignment of the row's children.
 * @param content The composable content to be displayed inside the row.
 * @return The `fuel` instance with the added row layout.
 */
@Composable
fun fuel.row(
    mod: Modifier = Modifier,
    horizontal: Arrangement.Horizontal = Arrangement.Start,
    vertical: Alignment.Vertical = Alignment.Top,
    content: @Composable RowScope.() -> Unit,
) = this.apply {
    wrappers += @Composable { function ->
        function()
        RowFactory {
            content()
        } /= {
            modifier = mod
            horizontalArrangement = horizontal
            verticalAlignment = vertical
        }
    }
}

/**
 * Adds a column layout to the composable function within the `fuel` instance.
 *
 * @param modifier The modifier to be applied to the column.
 * @param verticalArrangement The vertical arrangement of the column's children.
 * @param horizontalAlignment The horizontal alignment of the column's children.
 * @param content The composable content to be displayed inside the column.
 * @return The `fuel` instance with the added column layout.
 */
@Composable
fun fuel.column(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit,
) = this.apply {
    wrappers += @Composable { function ->
        function()
        Column(modifier, verticalArrangement, horizontalAlignment) {
            content()
        }
    }
}
