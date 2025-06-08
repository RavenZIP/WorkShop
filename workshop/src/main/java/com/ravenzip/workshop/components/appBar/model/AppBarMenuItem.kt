package com.ravenzip.workshop.components.appBar.model

import androidx.compose.material3.MenuItemColors
import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.model.TextConfig
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData

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
