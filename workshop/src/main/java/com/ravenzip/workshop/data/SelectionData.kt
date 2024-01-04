package com.ravenzip.workshop.data

import androidx.compose.material3.CheckboxColors
import androidx.compose.runtime.MutableState
import androidx.compose.ui.state.ToggleableState

data class SelectionParameters(val isSelected: Boolean, val text: String)

data class RootParameters(
    val state: MutableState<ToggleableState>,
    val text: String,
    val colors: CheckboxColors
)
