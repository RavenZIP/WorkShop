package com.ravenzip.workshop.data

import androidx.compose.material3.MenuItemColors

interface IAppBarItem {
    val icon: IconConfig
    val onClick: () -> Unit
}

class AppBarItem(
    override val icon: IconConfig,
    override val onClick: () -> Unit = {},
) : IAppBarItem

class AppBarMenuItem(
    override val icon: IconConfig,
    override val onClick: () -> Unit = {},
    val enabled: Boolean = true,
    val colors: MenuItemColors? = null,
    val text: String = "",
) : IAppBarItem

data class BottomNavigationItem(
    val label: String,
    val route: String,
    val icon: Icon,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

class BackArrow(val icon: IconConfig, val onClick: () -> Unit)

internal enum class BottomItemsTextState {
    SHOW_ALL,
    ONLY_SELECTED,
    HIDDEN
}
