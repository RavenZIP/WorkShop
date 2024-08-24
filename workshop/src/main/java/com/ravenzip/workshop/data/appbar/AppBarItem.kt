package com.ravenzip.workshop.data.appbar

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import com.ravenzip.workshop.data.icon.IconConfig

@Immutable
class AppBarItem(
    override val icon: ImageVector,
    override val iconConfig: IconConfig,
    override val onClick: () -> Unit = {},
) : IAppBarItem
