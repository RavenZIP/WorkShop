package com.ravenzip.workshop.forms.control

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
open class BaseControl(private val disable: Boolean = false) {
    private val _isDisabled: MutableState<Boolean> = mutableStateOf(disable)
    private val _error: MutableState<String> = mutableStateOf("")

    val isValid: Boolean
        get() = _error.value.isEmpty()

    val isInvalid: Boolean
        get() = _error.value.isNotEmpty()

    val errorMessage: String
        get() = _error.value

    val isEnabled: Boolean
        get() = !_isDisabled.value

    val isDisabled: Boolean
        get() = _isDisabled.value

    open fun setError(error: String) {
        _error.value = error
    }

    fun disable() {
        _isDisabled.value = true
    }

    fun enable() {
        _isDisabled.value = false
    }

    open fun reset() {
        _isDisabled.value = disable
        _error.value = ""
    }
}
