package com.ravenzip.workshop.data.appbar

import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.data.icon.Icon
import com.ravenzip.workshop.data.icon.IconConfig

@Immutable
class AppBarItem(
    override val icon: Icon,
    override val iconConfig: IconConfig,
    override val onClick: () -> Unit = {},
) : IAppBarItem
