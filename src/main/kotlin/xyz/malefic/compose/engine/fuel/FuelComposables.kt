package xyz.malefic.compose.engine.fuel

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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
        function = {
            this()
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
 * @return The `fuel` instance with the added divider.
 */
@Composable
fun fuel.divide(
    thickness: Dp = 1.dp,
    color: Color = MaterialTheme.colors.onBackground,
    vertical: Boolean = true,
): fuel =
    this.apply {
        function = {
            this()
            Divider(
                color = color,
                modifier =
                    Modifier
                        .then(if (vertical) Modifier.width(thickness) else Modifier.height(thickness))
                        .then(if (vertical) Modifier.fillMaxHeight() else Modifier.fillMaxWidth()),
            )
        }
    }
