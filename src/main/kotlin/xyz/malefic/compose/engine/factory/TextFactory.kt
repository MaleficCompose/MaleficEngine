package xyz.malefic.compose.engine.factory

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

/**
 * A factory class for creating and rendering a Composable Text with customizable properties.
 *
 * This class implements the ComposableFactory interface and provides methods to configure
 * and render a Text composable with various attributes such as color, font size, style,
 * alignment, and more. It includes methods to compose the text, invoke it for rendering,
 * and apply configuration changes before rendering.
 *
 * @constructor Rather than setting properties through the constructor, it is recommended to set them directly using `.apply` and property access syntax
 *
 * @property text The text content to be displayed.
 * @property modifier Modifier to be applied to the text.
 * @property color The color of the text.
 * @property fontSize The size of the text.
 * @property fontStyle The style of the font (e.g., italic).
 * @property fontWeight The weight of the font (e.g., bold).
 * @property fontFamily The font family to be used.
 * @property letterSpacing The spacing between letters.
 * @property textDecoration Decorations to apply to the text (e.g., underline).
 * @property textAlign The alignment of the text.
 * @property lineHeight The height of the lines of text.
 * @property overflow How visual overflow should be handled.
 * @property softWrap Whether the text should break at soft line breaks.
 * @property maxLines The maximum number of lines for the text.
 * @property minLines The minimum number of lines for the text.
 * @property onTextLayout Callback executed when the text layout is calculated.
 * @property style The style to be applied to the text.
 */
class TextFactory(
    var text: String = "",
    var modifier: Modifier = Modifier,
    var color: Color = Color.Unspecified,
    var fontSize: TextUnit = TextUnit.Unspecified,
    var fontStyle: FontStyle? = null,
    var fontWeight: FontWeight? = null,
    var fontFamily: FontFamily? = null,
    var letterSpacing: TextUnit = TextUnit.Unspecified,
    var textDecoration: TextDecoration? = null,
    var textAlign: TextAlign? = null,
    var lineHeight: TextUnit = TextUnit.Unspecified,
    var overflow: TextOverflow = TextOverflow.Clip,
    var softWrap: Boolean = true,
    var maxLines: Int = Int.MAX_VALUE,
    var minLines: Int = 1,
    var onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    var style: TextStyle? = null,
) : ComposableFactory {
    override var mods = listOf(modifier)

    /**
     * Creates a Composable Text with customizable properties.
     *
     * This function returns a Composable lambda that constructs a Text using
     * the properties defined in the TextFactory class. The text can be
     * customized with various attributes such as style, color, fontSize,
     * textAlign, overflow, and maxLines.
     *
     * @return A Composable lambda that represents the configured Text.
     */
    @Composable
    override fun compose(): @Composable () -> Unit =
        {
            Text(
                text = text,
                modifier = mods.combined,
                color = color,
                fontSize = fontSize,
                fontStyle = fontStyle,
                fontWeight = fontWeight,
                fontFamily = fontFamily,
                letterSpacing = letterSpacing,
                textDecoration = textDecoration,
                lineHeight = lineHeight,
                overflow = overflow,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines,
                onTextLayout = onTextLayout,
                style = style ?: LocalTextStyle.current,
            )
        }
}
