package com.ravenzip.workshop.forms.textfield

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

open class TextFieldState(val readonly: Boolean = false) {
    private val _isReadonly: MutableState<Boolean> = mutableStateOf(readonly)
    private val _isFocused: MutableState<Boolean> = mutableStateOf(false)

    val isFocused: Boolean
        get() = _isFocused.value

    val isReadonly: Boolean
        get() = _isReadonly.value

    val isEditable: Boolean
        get() = !_isReadonly.value

    fun changeFocusState(value: Boolean) {
        _isFocused.value = value
    }

    fun readonly() {
        _isReadonly.value = true
    }

    fun editable() {
        _isReadonly.value = false
    }

    fun setReadonly(readonly: Boolean) {
        _isReadonly.value = readonly
    }
}
