package com.ravenzip.workshop.data

import androidx.compose.material3.MenuItemColors

interface AppBarButton {
    val icon: Icon
    val enabled: Boolean
    val onClick: () -> Unit
}

class AppBarItem(
    override val icon: Icon,
    override val enabled: Boolean = true,
    override val onClick: () -> Unit,
) : AppBarButton

class AppBarMenuItem(
    override val icon: Icon,
    override val enabled: Boolean = true,
    override val onClick: () -> Unit,
    val colors: MenuItemColors? = null,
    val text: String = ""
) : AppBarButton

data class BottomNavigationItem(
    val label: String,
    val route: String,
    val icon: Icon,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

internal enum class BottomItemsTextState {
    SHOW_ALL,
    ONLY_SELECTED,
    HIDDEN
}
