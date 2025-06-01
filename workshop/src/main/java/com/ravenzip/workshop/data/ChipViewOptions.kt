package com.ravenzip.workshop.data

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
@Immutable
data class ChipViewOptions(
    val text: String,
    val textConfig: TextConfig,
    val icon: IconData,
    val iconConfig: IconConfig,
)
