package com.ravenzip.workshop.data.button

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
class ButtonContainerConfig(
    @FloatRange(from = 0.0, to = 1.0) val width: Float,
    val colors: ButtonColors,
    val shape: Shape,
    val contentPadding: PaddingValues,
) {
    companion object {
        fun smallButtonConfig(colors: ButtonColors) =
            ButtonContainerConfig(
                width = 1f,
                colors = colors,
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(12.dp),
            )

        @Composable
        fun lightStyle() =
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(0.08f),
                contentColor = MaterialTheme.colorScheme.primary,
            )
    }
}
