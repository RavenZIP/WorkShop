package com.ravenzip.workshop.forms.valueChange

import androidx.compose.runtime.Stable

@Stable data class ValueChange<T>(val value: T, val typeChanges: ValueChangeType)
