package xyz.malefic.compose.engine.factory

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation

/**
 * A factory class for creating a customizable `TextField` composable.
 *
 * @property value The current text to be displayed in the text field.
 * @property onValueChange Callback triggered when the text field value changes.
 * @property modifier Modifier to be applied to the text field.
 * @property enabled Whether the text field is enabled for user interaction.
 * @property readOnly Whether the text field is read-only.
 * @property textStyle The style to be applied to the text field's text.
 * @property label Optional composable to display as a label.
 * @property placeholder Optional composable to display as a placeholder.
 * @property leadingIcon Optional composable to display as a leading icon.
 * @property trailingIcon Optional composable to display as a trailing icon.
 * @property isError Whether the text field is in an error state.
 * @property visualTransformation Visual transformation applied to the text field.
 * @property keyboardOptions Configuration for the software keyboard.
 * @property keyboardActions Actions to be performed when keyboard events occur.
 * @property singleLine Whether the text field is a single line.
 * @property maxLines Maximum number of lines for the text field.
 * @property minLines Minimum number of lines for the text field.
 * @property interactionSource Optional interaction source for the text field.
 * @property shape The shape of the text field's outline.
 * @property colors The colors used for the text field's components.
 */
class TextFieldFactory(
    var value: TextFieldValue,
    var onValueChange: (TextFieldValue) -> Unit,
    var modifier: Modifier = Modifier,
    var enabled: Boolean = true,
    var readOnly: Boolean = false,
    var textStyle: TextStyle? = null,
    var label: @Composable (() -> Unit)? = null,
    var placeholder: @Composable (() -> Unit)? = null,
    var leadingIcon: @Composable (() -> Unit)? = null,
    var trailingIcon: @Composable (() -> Unit)? = null,
    var isError: Boolean = false,
    var visualTransformation: VisualTransformation = VisualTransformation.None,
    var keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    var keyboardActions: KeyboardActions = KeyboardActions(),
    var singleLine: Boolean = false,
    var maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    var minLines: Int = 1,
    var interactionSource: MutableInteractionSource? = null,
    var shape: Shape? = null,
    var colors: TextFieldColors? = null,
) : ComposableFactory {
    /**
     * Composes a `TextField` with the specified properties.
     *
     * This method constructs a `TextField` composable using the properties
     * defined in the `TextFieldFactory` class, allowing for customization
     * of appearance and behavior, such as text style, icons, and keyboard
     * options.
     *
     * @return A composable lambda that renders the configured `TextField`.
     */
    @Composable
    override fun compose(): @Composable () -> Unit =
        {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier,
                enabled = enabled,
                readOnly = readOnly,
                textStyle = textStyle ?: LocalTextStyle.current,
                label = label,
                placeholder = placeholder,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                maxLines = maxLines,
                minLines = minLines,
                interactionSource = interactionSource,
                shape = shape ?: TextFieldDefaults.TextFieldShape,
                colors = colors ?: TextFieldDefaults.textFieldColors(),
            )
        }
}
