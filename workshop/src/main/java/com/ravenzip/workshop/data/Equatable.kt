package com.ravenzip.workshop.data

import androidx.compose.runtime.Immutable

@Immutable
interface Equatable {
    override fun equals(other: Any?): Boolean
}
