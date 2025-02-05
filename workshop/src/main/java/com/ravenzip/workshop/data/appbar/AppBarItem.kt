package com.ravenzip.workshop.data.appbar

import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData

@Immutable
class AppBarItem(
    override val icon: IconData,
    override val iconConfig: IconConfig,
    override val onClick: () -> Unit = {},
) : IAppBarItem
