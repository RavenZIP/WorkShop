package com.ravenzip.workshop.data.appbar

import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData

@Immutable
internal interface IAppBarItem {
    val icon: IconData
    val iconConfig: IconConfig
    val onClick: () -> Unit
}
