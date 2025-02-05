package com.ravenzip.workshop.data.appbar

import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData

@Immutable class BackArrow(val icon: IconData, val iconConfig: IconConfig, val onClick: () -> Unit)
