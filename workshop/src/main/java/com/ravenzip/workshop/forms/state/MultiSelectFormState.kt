package com.ravenzip.workshop.forms.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
open class MultiSelectFormState<T>(
    initialValue: List<T>,
    source: List<T>,
    private val comparableKey: (T) -> Any,
    private val sourceView: (T) -> String,
    private val validators: List<(List<T>) -> String?> = emptyList(),
    private val disable: Boolean = false,
) {
    private val _state: SnapshotStateList<T> = mutableStateListOf()
    private val _isDisabled: MutableState<Boolean> = mutableStateOf(disable)
    private val _error: MutableState<String> = mutableStateOf("")
    private val _items = source.toList()

    private val _valueChanges: MutableStateFlow<List<T>> = MutableStateFlow(emptyList())
    val valueChanges = _valueChanges.asSharedFlow()

    val value: List<T>
        get() = _state.toList()

    val items: List<T>
        get() = _items

    fun view(value: T): String = sourceView(value)

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

    open fun setValue(value: T) {
        if (isSelected(value)) {
            _state.remove(value)
        } else {
            _state.add(value)
        }

        _valueChanges.update { _state.toList() }
        updateValidity()
    }

    fun selectAll() {
        val unselectedItems = _items.filter { !isSelected(it) }
        _state.addAll(unselectedItems)
    }

    fun unselectAll() {
        _state.clear()
    }

    private fun updateValidity() {
        _error.value = validators.firstNotNullOfOrNull { validator -> validator(_state) } ?: ""
    }

    fun isSelected(value: T): Boolean {
        return _state.any { selected -> comparableKey(selected) == comparableKey(value) }
    }

    fun disable() {
        _isDisabled.value = true
    }

    fun enable() {
        _isDisabled.value = false
    }

    fun reset() {
        _state.clear()
        _valueChanges.update { listOf() }
        _isDisabled.value = disable
        _error.value = ""
    }

    init {
        _state.addAll(initialValue)
    }
}
