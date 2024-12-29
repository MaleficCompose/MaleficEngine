package xyz.malefic.compose.engine.pocket

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Adds a spacer to a composable function.
 *
 * @receiver The composable function to which the spacer will be added.
 * @param height The height of the spacer. Defaults to null, meaning no height is applied.
 * @param width The width of the spacer. Defaults to null, meaning no height is applied.
 * @return A composable function with the added spacer.
 */
@Composable
fun (@Composable () -> Unit).space(
    height: Dp? = null,
    width: Dp? = null,
): @Composable () -> Unit =
    {
        this()
        Spacer(
            Modifier
                .then(if (width != null) Modifier.width(width) else Modifier)
                .then(if (height != null) Modifier.height(height) else Modifier),
        )
    }

/**
 * Adds a divider to a composable function.
 *
 * @receiver The composable function to which the divider will be added.
 * @param thickness The thickness of the divider. Defaults to 1.dp.
 * @param color The color of the divider. Defaults to the color that goes on the background of the material theme.
 * @param vertical Whether the divider should be vertical. Defaults to true.
 * @return A composable function with the added divider.
 */
@Composable
fun (@Composable () -> Unit).divide(
    thickness: Dp = 1.dp,
    color: Color = MaterialTheme.colors.onBackground,
    vertical: Boolean = true,
): @Composable () -> Unit =
    {
        this()
        Divider(
            color = color,
            modifier =
                Modifier
                    .then(if (vertical) Modifier.width(thickness) else Modifier.height(thickness))
                    .then(if (vertical) Modifier.fillMaxHeight() else Modifier.fillMaxWidth()),
        )
    }
