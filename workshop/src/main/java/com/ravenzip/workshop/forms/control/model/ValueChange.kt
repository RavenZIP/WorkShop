package com.ravenzip.workshop.forms.control.model

import androidx.compose.runtime.Stable
import com.ravenzip.workshop.forms.control.model.ValueChangeType

@Stable
data class ValueChange<T>(val value: T, val typeChanges: ValueChangeType)