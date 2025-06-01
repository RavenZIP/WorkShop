package com.ravenzip.workshop.forms.control.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf

@Stable
abstract class AbstractControl(private val disable: Boolean = false) {
    private val _isDisabled: MutableState<Boolean> = mutableStateOf(disable)

    @Stable
    val isEnabled: Boolean
        get() = !_isDisabled.value

    @Stable
    val isDisabled: Boolean
        get() = _isDisabled.value

    fun disable() {
        _isDisabled.value = true
    }

    fun enable() {
        _isDisabled.value = false
    }

    open fun reset() {
        _isDisabled.value = disable
    }
}
