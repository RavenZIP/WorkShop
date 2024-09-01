package com.ravenzip.workshop.data.appbar

import androidx.compose.material3.MenuItemColors
import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.data.icon.Icon
import com.ravenzip.workshop.data.icon.IconConfig

@Immutable
class AppBarMenuItem(
    override val icon: Icon,
    override val iconConfig: IconConfig,
    override val onClick: () -> Unit = {},
    val enabled: Boolean = true,
    val colors: MenuItemColors? = null,
    val text: String = "",
) : IAppBarItem
