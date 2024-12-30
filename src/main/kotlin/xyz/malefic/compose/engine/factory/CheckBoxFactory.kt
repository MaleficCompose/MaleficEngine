package xyz.malefic.compose.engine.factory

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxColors
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A factory class for creating a `CheckBox` composable with customizable components.
 *
 * This class implements the `ComposableFactory` interface, allowing the creation
 * of a `CheckBox` with various optional parameters such as modifier, checked state,
 * onCheckedChange callback, and colors. It provides default values for many parameters,
 * enabling flexible and easy setup of a `CheckBox`.
 *
 * @property checked State of the `CheckBox`.
 * @property onCheckedChange Callback to be invoked when the checked state changes.
 * @property modifier Modifier to be applied to the `CheckBox`.
 * @property enabled Whether the `CheckBox` is enabled.
 * @property interactionSource Optional interaction source for handling interaction events.
 * @property colors Colors used to style the `CheckBox`.
 */
class CheckBoxFactory(
    var checked: Boolean,
    var onCheckedChange: ((Boolean) -> Unit)?,
    var modifier: Modifier = Modifier,
    var enabled: Boolean = true,
    var interactionSource: MutableInteractionSource? = null,
    var colors: CheckboxColors? = null,
) : ComposableFactory {
    /**
     * Composes a `CheckBox` with the specified configuration.
     *
     * This method constructs a `CheckBox` composable using the parameters
     * defined in the `CheckBoxFactory` class. It applies default values
     * where necessary and allows for customization of various components
     * such as the modifier, checked state, and colors.
     *
     * @return A composable function that represents the configured `CheckBox`.
     */
    @Composable
    override fun compose(): @Composable () -> Unit =
        {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                colors = colors ?: CheckboxDefaults.colors(),
            )
        }
}
