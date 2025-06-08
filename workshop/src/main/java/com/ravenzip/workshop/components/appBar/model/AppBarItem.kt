package com.ravenzip.workshop.components.appBar.model

import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData

@Immutable
class AppBarItem(
    override val icon: IconData,
    override val iconConfig: IconConfig,
    override val onClick: () -> Unit = {},
) : IAppBarItem
