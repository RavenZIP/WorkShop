package com.ravenzip.workshop.forms.control

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.ravenzip.workshop.forms.control.base.AbstractFormControl
import com.ravenzip.workshop.forms.control.model.ValueChange
import com.ravenzip.workshop.forms.control.model.ValueChangeType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

@Stable
class FormControlMulti<T>(
    initialValue: List<T>,
    private val equals: (T, T) -> Boolean,
    private val validators: List<(List<T>) -> String?> = emptyList(),
    disable: Boolean = false,
) : AbstractFormControl(disable) {
    private val resetValue = initialValue

    private val _state: SnapshotStateList<T> = mutableStateListOf()

    private val _valueChanges: MutableStateFlow<ValueChange<List<T>>> =
        MutableStateFlow(ValueChange(initialValue, ValueChangeType.Initialize))
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
            if (isSelected(item)) {
                _state.remove(item)
            } else {
                _state.add(item)
            }
        }

        _valueChanges.update { ValueChange(_state.toList(), ValueChangeType.Set) }
        updateValidity()
    }

    internal fun isSelected(item: T): Boolean {
        return _state.any { x -> equals(x, item) }
    }

    private fun updateValidity() {
        val error = validators.firstNotNullOfOrNull { validator -> validator(value) } ?: ""
        super.setError(error)
    }

    override fun reset() = resetStateTo(resetValue)

    fun reset(vararg value: T) = resetStateTo(value.toList())

    fun reset(value: List<T>) = resetStateTo(value)

    private fun resetStateTo(value: List<T>) {
        _state.clear()
        _state.addAll(value)
        _valueChanges.update { ValueChange(_state.toList(), ValueChangeType.Reset) }
        super.reset()
    }
}
