package xyz.malefic.compose.engine.pocket

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Suppress("unused", "kotlin:S101", "ClassName")
class fuel(
    var function: @Composable () -> Unit,
) {
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
    fun space(
        height: Dp? = null,
        width: Dp? = null,
    ): fuel =
        this.apply {
            function.space(height, width)
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
    fun divide(
        thickness: Dp = 1.dp,
        color: Color = MaterialTheme.colors.onBackground,
        vertical: Boolean = true,
    ): fuel =
        this.apply {
            function = function.divide(thickness, color, vertical)
        }

    /**
     * Centers the composable function within the `fuel` instance.
     *
     * This method wraps the composable function in a Box with `fillMaxSize`
     * and `contentAlignment` set to `Alignment.Center`, ensuring the content
     * is centered within its parent.
     *
     * @param mod An optional [Modifier] to be applied to the Box.
     * @return The `fuel` instance with centered content.
     */
    @Composable
    fun center(mod: Modifier = Modifier): fuel =
        this.apply {
            function = function.center(mod)
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
    fun tooltip(
        modifier: Modifier = Modifier,
        delayMillis: Int = 500,
        tooltipPlacement: TooltipPlacement =
            TooltipPlacement.CursorPoint(
                offset = DpOffset(0.dp, 16.dp),
            ),
        tooltip: @Composable () -> Unit,
    ): fuel =
        this.apply {
            function = function.tooltip(modifier, delayMillis, tooltipPlacement, tooltip)
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
    fun background(
        color: Color = MaterialTheme.colors.background,
        mod: Modifier = Modifier,
    ): fuel =
        this.apply {
            function = function.background(color, mod)
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
    fun outline(
        color: Color = MaterialTheme.colors.onBackground,
        width: Dp = 1.dp,
        mod: Modifier = Modifier,
    ): fuel =
        this.apply {
            function = function.outline(color, width, mod)
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
    fun padding(
        all: Dp = 0.dp,
        mod: Modifier = Modifier,
    ): fuel =
        this.apply {
            function = function.padding(all, mod)
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
    fun padding(
        horizontal: Dp = 0.dp,
        vertical: Dp = 0.dp,
        mod: Modifier = Modifier,
    ): fuel =
        this.apply {
            function = function.padding(horizontal, vertical, mod)
        }

    /**
     * Invokes the composable function stored in this `fuel` instance.
     * This operator function allows the `fuel` object to be called as a function,
     * executing the composable logic defined in the `function` property.
     */
    @Composable
    operator fun invoke() {
        function()
    }

    @Composable
    operator fun times(block: @Composable fuel.() -> Unit) {
        block()
        this()
    }
}
