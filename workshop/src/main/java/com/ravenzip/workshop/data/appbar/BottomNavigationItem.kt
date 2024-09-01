package com.ravenzip.workshop.data.appbar

import com.ravenzip.workshop.data.icon.Icon
import com.ravenzip.workshop.data.icon.IconConfig

data class BottomNavigationItem(
    val label: String,
    val route: String,
    val icon: Icon,
    val iconConfig: IconConfig,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
)
