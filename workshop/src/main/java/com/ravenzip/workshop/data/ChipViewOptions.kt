package com.ravenzip.workshop.data

import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
data class ChipViewOptions(
    val text: String,
    val textConfig: TextConfig,
    val icon: IconData,
    val iconConfig: IconConfig,
)
