package com.ravenzip.workshop.components.chip.model

import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.model.TextConfig
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData
import org.jetbrains.annotations.ApiStatus

@ApiStatus.Experimental
@Immutable
data class ChipViewOptions(
    val text: String,
    val textConfig: TextConfig,
    val icon: IconData,
    val iconConfig: IconConfig,
)