package com.ravenzip.workshop.forms.state

import com.ravenzip.workshop.forms.FormState
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
open class RadioButtonState<T>(
    private val initialValue: T,
    source: List<T>,
    private val comparableKey: (T) -> Any,
    private val sourceView: (T) -> String,
    resetValue: T = initialValue,
    validators: List<(T) -> String?> = emptyList(),
    disable: Boolean = false,
    readonly: Boolean = false,
) : FormState<T>(initialValue, resetValue, validators, disable, readonly) {
    private val _items = source.toList()

    val items: List<T>
        get() = _items

    open fun view(value: T): String = sourceView(value)

    fun isSelected(value: T): Boolean {
        return comparableKey(this.value) == comparableKey(value)
    }
}
