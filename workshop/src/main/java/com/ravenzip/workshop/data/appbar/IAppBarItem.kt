package com.ravenzip.workshop.data.appbar

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import com.ravenzip.workshop.data.icon.IconConfig

@Immutable
internal interface IAppBarItem {
    val icon: ImageVector
    val iconConfig: IconConfig
    val onClick: () -> Unit
}
