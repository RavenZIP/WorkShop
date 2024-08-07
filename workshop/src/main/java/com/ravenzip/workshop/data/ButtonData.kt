package com.ravenzip.workshop.data

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape

class ButtonContentConfig(val text: TextConfig, val icon: IconConfig, val onClick: () -> Unit = {})

class ButtonContainerConfig(
    @FloatRange(from = 0.0, to = 1.0) val width: Float,
    val colors: ButtonColors,
    val shape: Shape,
    val contentPadding: PaddingValues
) {
    companion object {
        @Composable
        fun lightStyle() =
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(0.08f),
                contentColor = MaterialTheme.colorScheme.primary)
    }
}
