package com.ravenzip.workshop.data.appbar

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import com.ravenzip.workshop.data.icon.IconConfig

@Immutable
class BackArrow(val icon: ImageVector, val iconConfig: IconConfig, val onClick: () -> Unit)
