package com.ravenzip.workshop.data.selection

import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.icon.Icon
import com.ravenzip.workshop.data.icon.IconConfig

data class SelectableChipConfig(
    val isSelected: Boolean,
    val text: String,
    val textConfig: TextConfig,
    val icon: Icon,
    val iconConfig: IconConfig,
)
