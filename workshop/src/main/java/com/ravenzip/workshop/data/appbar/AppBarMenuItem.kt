package com.ravenzip.workshop.data.appbar

import androidx.compose.material3.MenuItemColors
import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData

@Immutable
class AppBarMenuItem(
    override val icon: IconData,
    override val iconConfig: IconConfig,
    override val onClick: () -> Unit = {},
    val enabled: Boolean = true,
    val colors: MenuItemColors? = null,
    val text: String = "",
    val textConfig: TextConfig = TextConfig.Small,
) : IAppBarItem
