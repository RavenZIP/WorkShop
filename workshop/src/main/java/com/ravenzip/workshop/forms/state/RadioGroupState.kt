package com.ravenzip.workshop.forms.state

import com.ravenzip.workshop.forms.FormState
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
// TODO добавить валидацию
open class RadioGroupState<T>(
    private val initialValue: T,
    source: List<T>,
    private val comparableKey: (T) -> Any,
    private val sourceView: (T) -> String,
    resetValue: T = initialValue,
    disable: Boolean = false,
) : FormState<T>(initialValue, resetValue, emptyList(), disable) {
    private val _items = source.toList()

    val items: List<T>
        get() = _items

    open fun view(value: T): String = sourceView(value)

    fun isSelected(value: T): Boolean {
        return comparableKey(this.value) == comparableKey(value)
    }
}
