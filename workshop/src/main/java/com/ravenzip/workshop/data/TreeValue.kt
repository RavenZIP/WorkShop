package com.ravenzip.workshop.data

import androidx.compose.runtime.Stable
import androidx.compose.ui.state.ToggleableState

@Stable data class TreeValue<T>(val parent: ToggleableState, val children: List<T>)
