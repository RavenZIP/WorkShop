package com.ravenzip.workshop.components.appBar.model

import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData

@Immutable
internal interface IAppBarItem {
    val icon: IconData
    val iconConfig: IconConfig
    val onClick: () -> Unit
}
