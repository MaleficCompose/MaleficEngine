package xyz.malefic.compose.engine.factory

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

/**
 * A factory class for creating customizable buttons with various properties.
 *
 * @constructor Rather than setting properties through the constructor, it is recommended to set them directly using `.apply` and property access syntax
 *
 * @property onClick A lambda function to be executed when the button is clicked.
 * @property modifier A [Modifier] for customizing the appearance and behavior of the button.
 * @property enabled A boolean indicating whether the button is enabled or disabled.
 * @property interactionSource An optional [MutableInteractionSource] for handling interaction events.
 * @property elevation An optional [ButtonElevation] for defining the button's elevation.
 * @property shape An optional [Shape] for defining the button's shape.
 * @property border An optional [BorderStroke] for defining the button's border.
 * @property colors An optional [ButtonColors] for defining the button's color scheme.
 * @property contentPadding The padding values for the button's content, defaulting to [ButtonDefaults.ContentPadding].
 * @property content A composable lambda for defining the content of the button.
 */
class ButtonFactory(
    var onClick: () -> Unit = {},
    var modifier: Modifier = Modifier.Companion,
    var enabled: Boolean = true,
    var interactionSource: MutableInteractionSource? = null,
    var elevation: ButtonElevation? = null,
    var shape: Shape? = null,
    var border: BorderStroke? = null,
    var colors: ButtonColors? = null,
    var contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    var content: @Composable RowScope.() -> Unit = {},
) : ComposableFactory {
    override var mods = listOf(modifier)

    /**
     * Creates a Composable Button with customizable properties.
     *
     * This function returns a Composable lambda that constructs a Button using
     * the properties defined in the ButtonFactory class. The button can be
     * customized with various attributes such as onClick behavior, modifier,
     * enabled state, interaction source, elevation, shape, border, colors, and
     * content padding. If certain properties are not set, default values from
     * ButtonDefaults or MaterialTheme are used.
     *
     * @return A Composable lambda that represents the configured Button.
     */
    @Composable
    override fun compose(): @Composable () -> Unit =
        {
            Button(
                onClick = onClick,
                modifier = mods.combined,
                enabled = enabled,
                interactionSource = interactionSource,
                elevation = elevation ?: ButtonDefaults.elevation(),
                shape = shape ?: MaterialTheme.shapes.small,
                border = border,
                colors = colors ?: ButtonDefaults.buttonColors(),
                contentPadding = contentPadding,
            ) {
                content()
            }
        }
}
