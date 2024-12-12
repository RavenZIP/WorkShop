package com.ravenzip.workshop.forms.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ravenzip.workshop.forms.FormState
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
open class TextFieldState<T>(
    private val initialValue: T,
    resetValue: T = initialValue,
    validators: List<(T) -> String?> = emptyList(),
    disable: Boolean = false,
    readonly: Boolean = false,
) : FormState<T>(initialValue, resetValue, validators, disable, readonly) {
    private val _isFocused: MutableState<Boolean> = mutableStateOf(false)

    val isFocused: Boolean
        get() = _isFocused.value

    fun changeFocusState(value: Boolean) {
        _isFocused.value = value
    }
}
