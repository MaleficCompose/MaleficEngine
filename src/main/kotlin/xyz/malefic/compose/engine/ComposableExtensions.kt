package xyz.malefic.compose.engine

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
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
 * Centers the content of a composable function within a Box layout.
 *
 * This extension function allows a composable function to be centered
 * within its parent by wrapping it in a Box with `fillMaxSize` and
 * `contentAlignment` set to `Alignment.Center`.
 *
 * @param mod An optional [Modifier] to be applied to the Box.
 * @return A composable function with centered content.
 */
@Composable
fun (@Composable () -> Unit).center(mod: Modifier = Modifier): @Composable () -> Unit =
    {
        Box(Modifier.fillMaxSize().then(mod), contentAlignment = Alignment.Center) {
            this@center()
        }
    }

/**
 * Adds a tooltip to a composable function.
 *
 * This extension function allows you to attach a tooltip to any composable
 * function. The tooltip will appear after a specified delay and can be
 * positioned relative to the cursor or another point.
 *
 * @receiver The composable function to which the tooltip will be attached.
 * @param tooltip The composable function representing the tooltip content.
 * @param modifier The modifier to be applied to the tooltip area.
 * @param delayMillis The delay in milliseconds before the tooltip is shown.
 * @param tooltipPlacement The placement strategy for the tooltip.
 * @return A composable function with the tooltip applied.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun (@Composable () -> Unit).tooltip(
    tooltip: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    delayMillis: Int = 500,
    tooltipPlacement: TooltipPlacement =
        TooltipPlacement.CursorPoint(
            offset = DpOffset(0.dp, 16.dp),
        ),
): @Composable () -> Unit =
    {
        TooltipArea(
            tooltip,
            modifier,
            delayMillis,
            tooltipPlacement,
        ) {
            this()
        }
    }
