package com.ravenzip.workshop.data.appbar

import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData

data class BottomNavigationItem(
    val label: String,
    val route: String,
    val icon: IconData,
    val iconConfig: IconConfig,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
)
