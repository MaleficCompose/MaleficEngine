@file:Suppress("unused")

package xyz.malefic.compose.engine.fuel

import androidx.annotation.FloatRange
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import xyz.malefic.compose.engine.factory.BoxFactory
import xyz.malefic.compose.engine.factory.divAssign

/**
 * Centers the composable function within the `fuel` instance.
 *
 * This method wraps the composable function in a Box with `fillMaxSize`
 * and `contentAlignment` set to `Alignment.Center`, ensuring the content
 * is centered within its parent.
 *
 * @param mod An optional [androidx.compose.ui.Modifier] to be applied to the Box.
 * @return The `fuel` instance with centered content.
 */
@Composable
fun fuel.center(mod: Modifier = Modifier.Companion): fuel =
    this.apply {
        val originalFunction = function
        function = {
            BoxFactory {
                originalFunction()
            } /= {
                modifier = mod.fillMaxSize()
                contentAlignment = Alignment.Center
            }
        }
    }

/**
 * Adds a tooltip to the composable function within the `fuel` instance.
 *
 * This method wraps the composable function in a tooltip area, allowing
 * a tooltip to be displayed after a specified delay and positioned
 * according to the given placement strategy.
 *
 * @param modifier The modifier to be applied to the tooltip area.
 * @param delayMillis The delay in milliseconds before the tooltip is shown. Defaults to 500ms.
 * @param tooltipPlacement The placement strategy for the tooltip. Defaults to cursor point with an offset.
 * @param tooltip The composable function representing the tooltip content.
 * @return The `fuel` instance with the tooltip applied.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun fuel.tooltip(
    modifier: Modifier = Modifier.Companion,
    delayMillis: Int = 500,
    tooltipPlacement: TooltipPlacement =
        TooltipPlacement.CursorPoint(
            offset = DpOffset(0.dp, 16.dp),
        ),
    tooltip: @Composable () -> Unit,
): fuel =
    this.apply {
        val originalFunction = function
        function = {
            TooltipArea(
                tooltip,
                modifier,
                delayMillis,
                tooltipPlacement,
            ) {
                originalFunction()
            }
        }
    }

/**
 * Applies a background color to the composable function within the `fuel` instance.
 *
 * This method wraps the composable function in a Box with a background color
 * that defaults to the MaterialTheme's background color, allowing for consistent theming.
 *
 * @param color The background color to apply. Defaults to MaterialTheme's background color.
 * @param mod An optional [Modifier] to be applied to the composable function.
 * @return The `fuel` instance with the specified background color applied.
 */
@Composable
fun fuel.background(
    color: Color = MaterialTheme.colors.background,
    mod: Modifier = Modifier.Companion,
): fuel =
    this.apply {
        val originalFunction = function
        function = {
            BoxFactory {
                originalFunction()
            } /= {
                modifier = mod.background(color)
            }
        }
    }

/**
 * Adds an outline to the composable function within the `fuel` instance.
 *
 * This method wraps the composable function in a Box with a border
 * of the specified color and width.
 *
 * @param color The color of the outline. Defaults to MaterialTheme's onBackground color.
 * @param width The width of the outline. Defaults to 1.dp.
 * @param mod An optional [Modifier] to be applied to the Box.
 * @return The `fuel` instance with the applied outline.
 */
@Composable
fun fuel.outline(
    color: Color = MaterialTheme.colors.onBackground,
    width: Dp = 1.dp,
    mod: Modifier = Modifier.Companion,
): fuel =
    this.apply {
        val originalFunction = function
        function = {
            BoxFactory {
                originalFunction()
            } /= {
                modifier = mod.border(width, color)
            }
        }
    }

/**
 * Adds click functionality to the composable function within the `fuel` instance.
 *
 * This method wraps the composable function in a Box with a clickable modifier,
 * allowing it to respond to click events.
 *
 * @param mod An optional [Modifier] to be applied to the Box.
 * @param enabled Whether the clickable area is enabled. Defaults to true.
 * @param onClickLabel The label for the click action, used for accessibility.
 * @param role The role of the clickable area, used for accessibility.
 * @param onClick The callback to be invoked when the area is clicked.
 * @return The `fuel` instance with the clickable functionality applied.
 */
@Composable
fun fuel.clickable(
    mod: Modifier = Modifier.Companion,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
): fuel =
    this.apply {
        val originalFunction = function
        function = {
            BoxFactory {
                originalFunction()
            } /= {
                modifier = mod.clickable(enabled, onClickLabel, role) { onClick() }
            }
        }
    }

/**
 * Sets the composable function within the `fuel` instance to fill the maximum width.
 *
 * This method wraps the composable function in a Box with `fillMaxWidth`
 * set to the specified fraction, allowing it to fill the available width.
 *
 * @param fraction The fraction of the width to fill. Defaults to 1.0 (full width).
 * @param mod An optional [Modifier] to be applied to the Box.
 * @return The `fuel` instance with the fillMaxWidth modifier applied.
 */
@Composable
fun fuel.fillMaxWidth(
    @FloatRange(from = 0.0, to = 1.0) fraction: Float = 1f,
    mod: Modifier = Modifier.Companion,
): fuel =
    this.apply {
        val originalFunction = function
        function = {
            BoxFactory {
                originalFunction()
            } /= {
                modifier = mod.fillMaxWidth(fraction)
            }
        }
    }

/**
 * Applies uniform padding to the composable function within the `fuel` instance.
 *
 * This method wraps the composable function in a Box with specified uniform padding.
 *
 * @param all The padding to apply. Defaults to 0.dp.
 * @param mod An optional [Modifier] to be applied to the Box.
 * @return The `fuel` instance with the specified padding applied.
 */
@Composable
fun fuel.padding(
    all: Dp = 0.dp,
    mod: Modifier = Modifier.Companion,
): fuel =
    this.apply {
        val originalFunction = function
        function = {
            BoxFactory {
                originalFunction()
            } /= {
                modifier = mod.padding(all)
            }
        }
    }

/**
 * Applies horizontal and vertical padding to the composable function within the `fuel` instance.
 *
 * This method wraps the composable function in a Box with specified horizontal and vertical padding.
 *
 * @param horizontal The horizontal padding to apply. Defaults to 0.dp.
 * @param vertical The vertical padding to apply. Defaults to 0.dp.
 * @param mod An optional [Modifier] to be applied to the Box.
 * @return The `fuel` instance with the specified padding applied.
 */
@Composable
fun fuel.padding(
    horizontal: Dp = 0.dp,
    vertical: Dp = 0.dp,
    mod: Modifier = Modifier.Companion,
): fuel =
    this.apply {
        val originalFunction = function
        function = {
            BoxFactory {
                originalFunction()
            } /= {
                modifier = mod.padding(horizontal, vertical)
            }
        }
    }

/**
 * Applies padding to the composable function within the `fuel` instance.
 *
 * This method wraps the composable function in a Box with specified padding for each side.
 *
 * @param start The padding to apply to the start side. Defaults to 0.dp.
 * @param top The padding to apply to the top side. Defaults to 0.dp.
 * @param end The padding to apply to the end side. Defaults to 0.dp.
 * @param bottom The padding to apply to the bottom side. Defaults to 0.dp.
 * @param mod An optional [Modifier] to be applied to the Box.
 * @return The `fuel` instance with the specified padding applied.
 */
@Composable
fun fuel.padding(
    start: Dp = 0.dp,
    top: Dp = 0.dp,
    end: Dp = 0.dp,
    bottom: Dp = 0.dp,
    mod: Modifier = Modifier.Companion,
): fuel =
    this.apply {
        val originalFunction = function
        function = {
            BoxFactory {
                originalFunction()
            } /= {
                modifier = mod.padding(start, top, end, bottom)
            }
        }
    }

/**
 * Applies padding values to the composable function within the `fuel` instance.
 *
 * This method wraps the composable function in a Box with specified padding values.
 *
 * @param paddingValues The padding values to apply.
 * @param mod An optional [Modifier] to be applied to the Box.
 * @return The `fuel` instance with the specified padding values applied.
 */
@Composable
fun fuel.padding(
    paddingValues: PaddingValues,
    mod: Modifier = Modifier.Companion,
): fuel =
    this.apply {
        val originalFunction = function
        function = {
            BoxFactory {
                originalFunction()
            } /= {
                modifier = mod.padding(paddingValues)
            }
        }
    }
