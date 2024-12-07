package com.ravenzip.workshop.forms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import org.jetbrains.annotations.ApiStatus.Experimental

// TODO реализовать запрет null значений -> надо ли?
// TODO запоминать когда контрол потрогали -> надо ли?
@Experimental
class FormState<T>(
    private val initialValue: T,
    private val validators: List<(T) -> String?> = emptyList(),
    private val disable: Boolean = false,
    private val readonly: Boolean = false,
) {
    private val _state: MutableState<T> = mutableStateOf(initialValue)
    private val _isDisabled: MutableState<Boolean> = mutableStateOf(disable)
    private val _isReadonly: MutableState<Boolean> = mutableStateOf(readonly)
    private val _errors: SnapshotStateList<String> = mutableStateListOf()

    private val _valueChanges: MutableStateFlow<T> = MutableStateFlow(initialValue)
    val valueChanges = _valueChanges.asSharedFlow()

    val value: T
        get() = _state.value

    val isValid: Boolean
        get() = _errors.isEmpty()

    val isInvalid: Boolean
        get() = _errors.isNotEmpty()

    val errorMessage: String
        get() = _errors.firstOrNull() ?: ""

    val isEnabled: Boolean
        get() = !_isDisabled.value

    val isDisabled: Boolean
        get() = _isDisabled.value

    val isReadonly: Boolean
        get() = _isReadonly.value

    val isEditable: Boolean
        get() = !_isReadonly.value

    fun setValue(value: T) {
        _state.value = value
        _valueChanges.update { value }
        updateValidity()
    }

    private fun updateValidity() {
        _errors.clear()
        val errors = validators.mapNotNull { validator -> validator(value) }
        _errors.addAll(errors)
    }

    fun disable() {
        _isDisabled.value = true
    }

    fun enable() {
        _isDisabled.value = false
    }

    fun readonly() {
        _isReadonly.value = true
    }

    fun editable() {
        _isReadonly.value = false
    }

    fun reset() {
        _state.value = initialValue
        _valueChanges.update { initialValue }
        _isDisabled.value = disable
        _isReadonly.value = readonly
        _errors.clear()
    }

    companion object {
        @Composable
        fun <T> create(initialValue: T): FormState<T> where T : Any {
            return rememberSaveable { FormState(initialValue) }
        }
    }
}
