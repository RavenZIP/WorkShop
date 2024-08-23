package com.ravenzip.workshop.data

import androidx.compose.material3.MenuItemColors
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

@Immutable
internal interface IAppBarItem {
    val icon: ImageVector
    val iconConfig: IconConfig
    val onClick: () -> Unit
}

@Immutable
class AppBarItem(
    override val icon: ImageVector,
    override val iconConfig: IconConfig,
    override val onClick: () -> Unit = {},
) : IAppBarItem

@Immutable
class AppBarMenuItem(
    override val icon: ImageVector,
    override val iconConfig: IconConfig,
    override val onClick: () -> Unit = {},
    val enabled: Boolean = true,
    val colors: MenuItemColors? = null,
    val text: String = "",
) : IAppBarItem

data class BottomNavigationItem(
    val label: String,
    val route: String,
    val icon: ImageVector,
    val iconConfig: IconConfig,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
)

@Immutable
class BackArrow(val icon: ImageVector, val iconConfig: IconConfig, val onClick: () -> Unit)

internal enum class BottomItemsTextState {
    SHOW_ALL,
    ONLY_SELECTED,
    HIDDEN,
}
