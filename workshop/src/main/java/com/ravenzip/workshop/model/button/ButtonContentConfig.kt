package com.ravenzip.workshop.model.button

import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.model.TextConfig
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData

@Immutable
class ButtonContentConfig(
    val text: String,
    val textConfig: TextConfig,
    val icon: IconData,
    val iconConfig: IconConfig,
    val onClick: () -> Unit = {},
)
