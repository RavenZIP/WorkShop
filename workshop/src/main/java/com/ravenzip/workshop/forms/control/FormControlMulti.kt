package com.ravenzip.workshop.forms.control

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.ravenzip.workshop.data.Equatable
import com.ravenzip.workshop.enums.ValueChangeType
import com.ravenzip.workshop.forms.ValueChange
import com.ravenzip.workshop.forms.control.base.AbstractFormControl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import org.jetbrains.annotations.ApiStatus.Experimental

// TODO сделать комбинированный вариант для reset (resetValue + value как параметр функции),
// оставить как есть, либо сделать только параметр функции
@Experimental
@Stable
open class FormControlMulti<T : Equatable>(
    initialValue: List<T>,
    private val resetValue: List<T> = emptyList(),
    private val validators: List<(List<T>) -> String?> = emptyList(),
    disable: Boolean = false,
) : AbstractFormControl(disable) {
    private val _state: SnapshotStateList<T> = mutableStateListOf()

    private val _valueChanges: MutableStateFlow<ValueChange<List<T>>> =
        MutableStateFlow(ValueChange(initialValue, ValueChangeType.INITIALIZE))
    val valueChangesWithTypeChanges = _valueChanges.asSharedFlow()

    val valueChanges =
        valueChangesWithTypeChanges.map { valueChangesWithTypeChanges ->
            valueChangesWithTypeChanges.value
        }

    @Stable
    val value: List<T>
        get() = _state

    fun setValue(vararg value: T) {
        value.forEach { item ->
            if (item in _state) {
                _state.remove(item)
            } else {
                _state.add(item)
            }
        }

        _valueChanges.update { ValueChange(_state.toList(), ValueChangeType.SET) }
        updateValidity()
    }

    private fun updateValidity() {
        val error = validators.firstNotNullOfOrNull { validator -> validator(value) } ?: ""
        super.setError(error)
    }

    override fun reset() {
        _state.clear()
        _state.addAll(resetValue)
        _valueChanges.update { ValueChange(_state.toList(), ValueChangeType.RESET) }
        super.reset()
    }
}
