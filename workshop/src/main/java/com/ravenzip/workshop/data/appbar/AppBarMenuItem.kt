package com.ravenzip.workshop.data.appbar

import androidx.compose.material3.MenuItemColors
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import com.ravenzip.workshop.data.icon.IconConfig

@Immutable
class AppBarMenuItem(
    override val icon: ImageVector,
    override val iconConfig: IconConfig,
    override val onClick: () -> Unit = {},
    val enabled: Boolean = true,
    val colors: MenuItemColors? = null,
    val text: String = "",
) : IAppBarItem
