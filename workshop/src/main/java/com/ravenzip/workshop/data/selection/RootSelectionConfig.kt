package com.ravenzip.workshop.data.selection

import androidx.compose.material3.CheckboxColors
import androidx.compose.runtime.MutableState
import androidx.compose.ui.state.ToggleableState

data class RootSelectionConfig(
    val state: MutableState<ToggleableState>,
    val text: String,
    val colors: CheckboxColors,
)
