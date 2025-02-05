package com.ravenzip.workshop.data.button

import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData

@Immutable
class ButtonContentConfig(
    val text: String,
    val textConfig: TextConfig,
    val icon: IconData,
    val iconConfig: IconConfig,
    val onClick: () -> Unit = {},
)
