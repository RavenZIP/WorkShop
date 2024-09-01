package com.ravenzip.workshop.data.appbar

import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.data.icon.Icon
import com.ravenzip.workshop.data.icon.IconConfig

@Immutable class BackArrow(val icon: Icon, val iconConfig: IconConfig, val onClick: () -> Unit)
