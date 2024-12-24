package xyz.malefic.compose.engine

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import sun.util.calendar.CalendarUtils.mod

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
        Box(mod.fillMaxSize(), contentAlignment = Alignment.Center) {
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

/**
 * Applies a background color to a composable function using the current MaterialTheme's background color.
 *
 * This extension function wraps a composable function in a Box with a background color
 * that defaults to the MaterialTheme's background color, allowing for consistent theming.
 *
 * @param color The background color to apply. Defaults to MaterialTheme's background color.
 * @param mod An optional [Modifier] to be applied to the composable function.
 * @return A composable function with the specified background color applied.
 */
@Composable
fun (@Composable () -> Unit).background(
    color: Color = MaterialTheme.colors.background,
    mod: Modifier = Modifier,
): @Composable () -> Unit =
    {
        Box(mod.background(color)) {
            this@background()
        }
    }

/**
 * Adds an outline to the composable function using a specified color and width.
 *
 * This extension function allows a composable function to have an outline
 * by wrapping it in a Box with a border.
 *
 * @param color The color of the outline. Defaults to MaterialTheme's onBackground color.
 * @param width The width of the outline. Defaults to 1.dp.
 * @param mod An optional [Modifier] to be applied to the Box.
 * @return A composable function with the applied outline.
 */
@Composable
fun (@Composable () -> Unit).outline(
    color: Color = MaterialTheme.colors.onBackground,
    width: Dp = 1.dp,
    mod: Modifier = Modifier,
): @Composable () -> Unit =
    {
        Box(mod.border(width, color)) {
            this@outline()
        }
    }
