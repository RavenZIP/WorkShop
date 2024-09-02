package com.ravenzip.workshop.data.button

import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.icon.Icon
import com.ravenzip.workshop.data.icon.IconConfig

@Immutable
class ButtonContentConfig(
    val text: String,
    val textConfig: TextConfig,
    val icon: Icon,
    val iconConfig: IconConfig,
    val onClick: () -> Unit = {},
)
