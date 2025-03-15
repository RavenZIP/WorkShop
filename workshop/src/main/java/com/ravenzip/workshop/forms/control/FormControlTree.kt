package com.ravenzip.workshop.forms.control

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.state.ToggleableState
import com.ravenzip.workshop.data.CheckBoxTreeValue
import com.ravenzip.workshop.data.Equatable
import com.ravenzip.workshop.enums.ValueChangeType
import com.ravenzip.workshop.forms.ValueChanges
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

@Immutable
// TODO необходимо обеспечить интеграцию с FormGroup
class FormControlTree<T : Equatable>(
    initialValue: List<T>,
    private val resetValue: List<T> = emptyList(),
    private val validators: List<(CheckBoxTreeValue<T>) -> String?> = emptyList(),
    disable: Boolean = false,
) : BaseControl(disable) {
    private val _state: MutableState<CheckBoxTreeValue<T>> =
        mutableStateOf(CheckBoxTreeValue(parent = ToggleableState.Off, children = initialValue))

    private val _valueChanges: MutableStateFlow<ValueChanges<CheckBoxTreeValue<T>>> =
        MutableStateFlow(ValueChanges(_state.value, ValueChangeType.INITIALIZE))
    val valueChangesWithTypeChanges = _valueChanges.asSharedFlow()

    val valueChanges =
        valueChangesWithTypeChanges.map { valueChangesWithTypeChanges ->
            valueChangesWithTypeChanges.value
        }

    @Stable
    val value
        get() = _state.value

    fun setValue(value: List<T>) {
        _state.value = _state.value.copy(children = recalculateChildren(value))
        _valueChanges.update { ValueChanges(_state.value, ValueChangeType.SET) }
        updateValidity()
    }

    fun setValue(vararg value: T) {
        _state.value = _state.value.copy(children = recalculateChildren(value.toList()))
        _valueChanges.update { ValueChanges(_state.value, ValueChangeType.SET) }
        updateValidity()
    }

    fun setValue(value: ToggleableState) {
        _state.value = _state.value.copy(parent = value)
        _valueChanges.update { ValueChanges(_state.value, ValueChangeType.SET) }
        updateValidity()
    }

    private fun recalculateChildren(value: List<T>): List<T> {
        val currentChildrenValue = _state.value.children.toMutableList()

        value.forEach { item ->
            if (item in _state.value.children) {
                currentChildrenValue.remove(item)
            } else {
                currentChildrenValue.add(item)
            }
        }

        return currentChildrenValue
    }

    private fun updateValidity() {
        val error = validators.firstNotNullOfOrNull { validator -> validator(value) } ?: ""
        super.setError(error)
    }

    override fun reset() {
        _state.value = _state.value.copy(children = recalculateChildren(resetValue))
        _valueChanges.update { ValueChanges(_state.value, ValueChangeType.RESET) }
        super.reset()
    }
}
