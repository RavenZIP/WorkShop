package com.ravenzip.workshop.forms.control

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
open class FormControlMulti<T>(
    initialValue: List<T>,
    source: List<T>,
    private val comparableKey: (T) -> Any,
    private val sourceView: (T) -> String,
    private val validators: List<(List<T>) -> String?> = emptyList(),
    disable: Boolean = false,
) : BaseControl(disable) {
    private val _state: SnapshotStateList<T> = mutableStateListOf()
    private val _items = source.toList()

    private val _valueChanges: MutableStateFlow<List<T>> = MutableStateFlow(emptyList())
    val valueChanges = _valueChanges.asSharedFlow()

    val value: List<T>
        get() = _state.toList()

    val items: List<T>
        get() = _items

    fun view(value: T): String = sourceView(value)

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
        _valueChanges.update { emptyList() }
    }

    private fun updateValidity() {
        val error = validators.firstNotNullOfOrNull { validator -> validator(value) } ?: ""
        super.setError(error)
    }

    fun isSelected(value: T): Boolean {
        return _state.any { selected -> comparableKey(selected) == comparableKey(value) }
    }

    override fun reset() {
        _state.clear()
        _valueChanges.update { emptyList() }
        super.reset()
    }

    init {
        _state.addAll(initialValue)
    }
}
