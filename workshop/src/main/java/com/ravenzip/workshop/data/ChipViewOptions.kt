package com.ravenzip.workshop.data

import com.ravenzip.workshop.data.icon.Icon
import com.ravenzip.workshop.data.icon.IconConfig
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
data class ChipViewOptions(
    val text: String,
    val textConfig: TextConfig,
    val icon: Icon,
    val iconConfig: IconConfig,
)
