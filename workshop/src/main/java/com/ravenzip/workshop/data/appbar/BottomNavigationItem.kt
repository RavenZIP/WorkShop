package com.ravenzip.workshop.data.appbar

import androidx.compose.ui.graphics.vector.ImageVector
import com.ravenzip.workshop.data.icon.IconConfig

data class BottomNavigationItem(
    val label: String,
    val route: String,
    val icon: ImageVector,
    val iconConfig: IconConfig,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
)
