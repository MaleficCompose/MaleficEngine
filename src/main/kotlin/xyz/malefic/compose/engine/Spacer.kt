package xyz.malefic.compose.engine

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun (@Composable () -> Unit).space(
    height: Dp? = null,
    width: Dp? = null,
): @Composable () -> Unit =
    {
        this()
        Spacer(
            Modifier
                .width(width ?: 8.dp)
                .then(if (height != null) Modifier.height(height) else Modifier),
        )
    }
