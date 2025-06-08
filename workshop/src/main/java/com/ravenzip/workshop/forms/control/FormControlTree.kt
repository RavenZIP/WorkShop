package com.ravenzip.workshop.forms.control

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.state.ToggleableState
import com.ravenzip.workshop.data.Equatable
import com.ravenzip.workshop.data.TreeValue
import com.ravenzip.workshop.enums.ValueChangeType
import com.ravenzip.workshop.forms.ValueChange
import com.ravenzip.workshop.forms.control.base.AbstractFormControl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

@Stable
class FormControlTree<T : Equatable>(
    initialValue: List<T>,
    private val resetValue: List<T> = emptyList(),
    private val validators: List<(TreeValue<T>) -> String?> = emptyList(),
    disable: Boolean = false,
) : AbstractFormControl(disable) {
    private val _state: MutableState<TreeValue<T>> =
        mutableStateOf(TreeValue(parent = ToggleableState.Off, children = initialValue))

    private val _valueChanges: MutableStateFlow<ValueChange<TreeValue<T>>> =
        MutableStateFlow(ValueChange(_state.value, ValueChangeType.INITIALIZE))
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
        _valueChanges.update { ValueChange(_state.value, ValueChangeType.SET) }
        updateValidity()
    }

    fun setValue(vararg value: T) {
        _state.value = _state.value.copy(children = recalculateChildren(value.toList()))
        _valueChanges.update { ValueChange(_state.value, ValueChangeType.SET) }
        updateValidity()
    }

    fun setValue(value: ToggleableState) {
        _state.value = _state.value.copy(parent = value)
        _valueChanges.update { ValueChange(_state.value, ValueChangeType.SET) }
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
        _valueChanges.update { ValueChange(_state.value, ValueChangeType.RESET) }
        super.reset()
    }
}
