package com.ravenzip.workshop.data

import androidx.compose.runtime.Immutable
import androidx.compose.ui.state.ToggleableState

@Immutable data class CheckBoxTreeValue<T>(val parent: ToggleableState, val children: List<T>)
