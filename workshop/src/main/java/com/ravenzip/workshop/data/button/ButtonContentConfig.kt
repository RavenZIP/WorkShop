package com.ravenzip.workshop.data.button

import androidx.compose.ui.graphics.vector.ImageVector
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.icon.IconConfig

class ButtonContentConfig(
    val text: String,
    val textConfig: TextConfig,
    val icon: ImageVector,
    val iconConfig: IconConfig,
    val onClick: () -> Unit = {},
)
