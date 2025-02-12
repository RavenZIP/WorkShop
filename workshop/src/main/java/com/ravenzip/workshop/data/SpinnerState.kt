package com.ravenzip.workshop.data

import androidx.compose.runtime.Stable

@Stable data class SpinnerState(val isLoading: Boolean, val text: String)
