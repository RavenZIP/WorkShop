package com.ravenzip.workshop.forms.control.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf

@Stable
open class AbstractFormControl(disable: Boolean = false) : AbstractControl(disable) {
    private val _error: MutableState<String> = mutableStateOf("")

    @Stable
    val isValid: Boolean
        get() = _error.value.isEmpty()

    @Stable
    val isInvalid: Boolean
        get() = _error.value.isNotEmpty()

    @Stable
    val errorMessage: String
        get() = _error.value

    open fun setError(error: String) {
        _error.value = error
    }

    override fun reset() {
        super.reset()
        _error.value = ""
    }
}
