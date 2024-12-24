package xyz.malefic.compose.engine

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Adds a spacer with optional height and width to a composable function.
 *
 * @receiver The composable function to which the spacer will be added. To apply this to a ButtonFactory, `ButtonFactory().compose().space()` can be used.
 * @param height The optional height of the spacer. Defaults to null, meaning no height is applied.
 * @param width The optional width of the spacer. Defaults to 8.dp.
 * @return A composable function with the added spacer.
 */
@Composable
fun (@Composable () -> Unit).space(
    height: Dp? = null,
    width: Dp? = 8.dp,
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
 * Adds a divider with optional thickness and color to a composable function.
 *
 * @receiver The composable function to which the divider will be added. To apply this to a ButtonFactory, `ButtonFactory().compose().divider()` can be used.
 * @param thickness The optional thickness of the divider. Defaults to 1.dp.
 * @param color The optional color of the divider. Defaults to Color.Black.
 * @param isVertical Whether the divider should be vertical. Defaults to false.
 * @return A composable function with the added divider.
 */
@Composable
fun (@Composable () -> Unit).divide(
    thickness: Dp = 1.dp,
    color: Color = Color.Black,
    isVertical: Boolean = false,
): @Composable () -> Unit =
    {
        this()
        Divider(
            color = color,
            modifier =
                Modifier
                    .then(if (isVertical) Modifier.fillMaxHeight().width(thickness) else Modifier.fillMaxWidth().height(thickness)),
        )
    }
