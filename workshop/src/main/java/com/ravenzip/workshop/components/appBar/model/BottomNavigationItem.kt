package com.ravenzip.workshop.components.appBar.model

import androidx.compose.runtime.Stable
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData

@Stable
data class BottomNavigationItem(
    val label: String,
    val route: String,
    val icon: IconData,
    val iconConfig: IconConfig,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
)
