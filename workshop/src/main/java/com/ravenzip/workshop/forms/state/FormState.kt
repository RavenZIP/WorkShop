package com.ravenzip.workshop.forms.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import org.jetbrains.annotations.ApiStatus.Experimental

// TODO реализовать запрет null значений -> надо ли?
// TODO запоминать когда контрол потрогали -> надо ли?
@Experimental
open class FormState<T>(
    private val initialValue: T,
    private val resetValue: T = initialValue,
    private val validators: List<(T) -> String?> = emptyList(),
    disable: Boolean = false,
) : BaseFormState(disable) {
    private val _state: MutableState<T> = mutableStateOf(initialValue)

    private val _valueChanges: MutableStateFlow<T> = MutableStateFlow(initialValue)
    val valueChanges = _valueChanges.asSharedFlow()

    val value: T
        get() = _state.value

    open fun setValue(value: T) {
        _state.value = value
        _valueChanges.update { value }
        updateValidity()
    }

    private fun updateValidity() {
        val error = validators.firstNotNullOfOrNull { validator -> validator(value) } ?: ""
        super.setError(error)
    }

    override fun reset() {
        _state.value = resetValue
        _valueChanges.update { resetValue }
        super.reset()
    }

    companion object {
        @Composable
        fun <T> create(
            initialValue: T,
            resetValue: T = initialValue,
            validators: List<(T) -> String?> = emptyList(),
            disable: Boolean = false,
        ): FormState<T> where T : Any {
            return rememberSaveable { FormState(initialValue, resetValue, validators, disable) }
        }
    }
}
