package com.ravenzip.workshop.forms

import androidx.compose.runtime.Stable
import com.ravenzip.workshop.enums.ValueChangeType

@Stable data class ValueChanges<T>(val value: T, val typeChanges: ValueChangeType)
