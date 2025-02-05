package com.ravenzip.workshop.data.selection

import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData

@Deprecated("Не использовать, вскоре будет удален")
data class SelectableChipConfig(
    val isSelected: Boolean,
    val text: String,
    val textConfig: TextConfig,
    val icon: IconData,
    val iconConfig: IconConfig,
)
