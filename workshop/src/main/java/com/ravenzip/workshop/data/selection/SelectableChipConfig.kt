package com.ravenzip.workshop.data.selection

import androidx.compose.ui.graphics.vector.ImageVector
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.icon.IconConfig

data class SelectableChipConfig(
    val isSelected: Boolean,
    val text: String,
    val textConfig: TextConfig,
    val icon: ImageVector,
    val iconConfig: IconConfig,
)
