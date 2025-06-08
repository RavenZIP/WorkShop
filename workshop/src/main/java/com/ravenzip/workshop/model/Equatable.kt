package com.ravenzip.workshop.model

import androidx.compose.runtime.Immutable

@Immutable
// TODO доработать его использование, потому что если кто-то захочет сделать лист со строками или
// числами, то будет проблема
interface Equatable {
    override fun equals(other: Any?): Boolean
}
