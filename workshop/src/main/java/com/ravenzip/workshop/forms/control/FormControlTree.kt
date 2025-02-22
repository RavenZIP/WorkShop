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
import kotlinx.coroutines.flow.update

@Immutable
// TODO подумать над реализацией, возможно потребуются изменения внутри FormControlMulti
// TODO необходимо обеспечить интеграцию с FormGroup
class FormControlTree<T : Equatable>(
    initialValue: List<T>,
    private val totalItemsCount: Int,
    private val resetValue: List<T> = emptyList(),
    private val validators: List<(CheckBoxTreeValue<T>) -> String?> = emptyList(),
    disable: Boolean,
) : BaseControl(disable) {
    private val _state: MutableState<CheckBoxTreeValue<T>> =
        mutableStateOf(CheckBoxTreeValue(parent = ToggleableState.Off, children = initialValue))

    private val _valueChanges: MutableStateFlow<ValueChanges<CheckBoxTreeValue<T>>> =
        MutableStateFlow(ValueChanges(_state.value, ValueChangeType.INITIALIZE))
    val valueChanges = _valueChanges.asSharedFlow()

    @Stable
    val value
        get() = _state.value

    fun setValue(vararg value: T) {
        val currentChildrenValue = _state.value.children.toMutableList()

        value.forEach { item ->
            if (item in _state.value.children) {
                currentChildrenValue.remove(item)
            } else {
                currentChildrenValue.add(item)
            }
        }

        _state.value =
            _state.value.copy(parent = recalculateParentState(), children = currentChildrenValue)

        _valueChanges.update { ValueChanges(_state.value, ValueChangeType.SET) }
        updateValidity()
    }

    private fun updateValidity() {
        val error = validators.firstNotNullOfOrNull { validator -> validator(value) } ?: ""
        super.setError(error)
    }

    //    override fun reset() {
    //        _state.value = resetValue
    //        _valueChanges.update { ValueChanges(resetValue, ValueChangeType.RESET) }
    //        super.reset()
    //    }

    //    fun changeParentState(source: List<T>) {
    //        parent.value =
    //            when (parent.value) {
    //                ToggleableState.On -> ToggleableState.Off
    //                else -> ToggleableState.On
    //            }
    //
    //        if (parent.value == ToggleableState.On) {
    //            children.selectAll(source)
    //        } else {
    //            children.unselectAll()
    //        }
    //    }

    private fun recalculateParentState(): ToggleableState {
        return when (_state.value.children.count()) {
            0 -> ToggleableState.Off
            totalItemsCount -> ToggleableState.On
            else -> ToggleableState.Indeterminate
        }
    }
}
