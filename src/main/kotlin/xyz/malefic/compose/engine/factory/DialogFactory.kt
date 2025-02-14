package xyz.malefic.compose.engine.factory

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * A factory class for creating a Dialog in Jetpack Compose.
 *
 * @property onDismissRequest A callback to be invoked when the dialog is dismissed.
 * @property properties The properties to configure the dialog's behavior and appearance.
 * @property content The composable content to be displayed inside the dialog.
 */
class DialogFactory(
    var modifier: Modifier = Modifier.Companion,
    var onDismissRequest: () -> Unit,
    var properties: DialogProperties,
    var content: @Composable () -> Unit,
) : ComposableFactory {
    override var mods = listOf(modifier)

    /**
     * Composes the dialog with the specified properties and content.
     *
     * @return A composable function that creates the dialog.
     */
    @Composable
    override fun compose(): @Composable () -> Unit =
        {
            BoxFactory {
                Dialog(onDismissRequest, properties) {
                    content()
                }
            } /= {
                modifier = mods.combined
            }
        }
}
