package com.ravenzip.workshop.model

import androidx.compose.runtime.Stable

@Stable data class SpinnerState(val isLoading: Boolean, val text: String)
