package com.ravenzip.workshop.forms

import androidx.compose.runtime.Stable
import com.ravenzip.workshop.enums.ValueChangeType

@Stable data class ValueChange<T>(val value: T, val typeChanges: ValueChangeType)
