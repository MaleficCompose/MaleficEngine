package xyz.malefic.compose.engine.factory

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.DrawerDefaults
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.contentColorFor
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

/**
 * A factory class for creating a `Scaffold` composable with customizable components.
 *
 * This class implements the `ComposableFactory` interface, allowing the creation
 * of a `Scaffold` with various optional parameters such as top bar, bottom bar,
 * floating action button, and drawer configurations. It provides default values
 * for many parameters, enabling flexible and easy setup of a `Scaffold` layout.
 *
 * @property modifier Modifier to be applied to the `Scaffold`.
 * @property scaffoldState State of the `Scaffold`, defaulting to a remembered state.
 * @property topBar Composable function for the top bar.
 * @property bottomBar Composable function for the bottom bar.
 * @property snackbarHost Composable function for the snackbar host.
 * @property floatingActionButton Composable function for the floating action button.
 * @property floatingActionButtonPosition Position of the floating action button.
 * @property isFloatingActionButtonDocked Whether the floating action button is docked.
 * @property drawerContent Composable function for the drawer content.
 * @property drawerGesturesEnabled Whether gestures are enabled for the drawer.
 * @property drawerShape Shape of the drawer.
 * @property drawerElevation Elevation of the drawer.
 * @property drawerBackgroundColor Background color of the drawer.
 * @property drawerContentColor Content color of the drawer.
 * @property drawerScrimColor Scrim color of the drawer.
 * @property backgroundColor Background color of the `Scaffold`.
 * @property contentColor Content color of the `Scaffold`.
 * @property content Composable function for the main content of the `Scaffold`.
 */
class ScaffoldFactory(
    var modifier: Modifier = Modifier,
    var scaffoldState: ScaffoldState? = null,
    var topBar: @Composable () -> Unit = {},
    var bottomBar: @Composable () -> Unit = {},
    var snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    var floatingActionButton: @Composable () -> Unit = {},
    var floatingActionButtonPosition: FabPosition = FabPosition.End,
    var isFloatingActionButtonDocked: Boolean = false,
    var drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
    var drawerGesturesEnabled: Boolean = true,
    var drawerShape: Shape? = null,
    var drawerElevation: Dp = DrawerDefaults.Elevation,
    var drawerBackgroundColor: Color? = null,
    var drawerContentColor: Color? = null,
    var drawerScrimColor: Color? = null,
    var backgroundColor: Color? = null,
    var contentColor: Color? = null,
    var content: @Composable (PaddingValues) -> Unit,
) : ComposableFactory {
    /**
     * Composes a `Scaffold` with the specified configuration.
     *
     * This method constructs a `Scaffold` composable using the parameters
     * defined in the `ScaffoldFactory` class. It applies default values
     * where necessary and allows for customization of various components
     * such as the top bar, bottom bar, floating action button, and drawer.
     *
     * @return A composable function that represents the configured `Scaffold`.
     */
    @Composable
    override fun compose(): @Composable () -> Unit =
        {
            Scaffold(
                modifier = modifier,
                scaffoldState = scaffoldState ?: rememberScaffoldState(),
                topBar = topBar,
                bottomBar = bottomBar,
                snackbarHost = snackbarHost,
                floatingActionButton = floatingActionButton,
                floatingActionButtonPosition = floatingActionButtonPosition,
                isFloatingActionButtonDocked = isFloatingActionButtonDocked,
                drawerContent = drawerContent,
                drawerGesturesEnabled = drawerGesturesEnabled,
                drawerShape = drawerShape ?: MaterialTheme.shapes.large,
                drawerElevation = drawerElevation,
                drawerBackgroundColor = drawerBackgroundColor ?: MaterialTheme.colors.surface,
                drawerContentColor = drawerContentColor ?: contentColorFor(drawerBackgroundColor ?: MaterialTheme.colors.surface),
                drawerScrimColor = drawerScrimColor ?: DrawerDefaults.scrimColor,
                backgroundColor = backgroundColor ?: MaterialTheme.colors.background,
                contentColor = contentColor ?: contentColorFor(backgroundColor ?: MaterialTheme.colors.background),
            ) {
                content(it)
            }
        }
}
