package com.ravenzip.workshop.data

import androidx.compose.material3.MenuItemColors
import androidx.compose.ui.graphics.vector.ImageVector

class TopNavigationItem(
    val icon: ImageVector,
    val description: String,
    val text: String = "",
    val colors: MenuItemColors? = null,
    val enabled: Boolean,
    val onClick: () -> Unit
)

data class BottomNavigationItem(
    val label: String,
    val route: String,
    val icon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)
