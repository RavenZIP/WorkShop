package com.ravenzip.workshop.components.appBar.model

import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData

@Immutable class BackArrow(val icon: IconData, val iconConfig: IconConfig, val onClick: () -> Unit)
