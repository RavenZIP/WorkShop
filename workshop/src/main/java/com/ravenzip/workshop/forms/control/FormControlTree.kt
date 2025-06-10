package com.ravenzip.workshop.forms.control

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.state.ToggleableState
import com.ravenzip.workshop.forms.control.base.AbstractFormControl
import com.ravenzip.workshop.forms.control.model.TreeValue
import com.ravenzip.workshop.forms.control.model.ValueChange
import com.ravenzip.workshop.forms.control.model.ValueChangeType
import kotlin.collections.any
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

@Stable
class FormControlTree<T>(
    initialValue: List<T>,
    private val equals: (T, T) -> Boolean,
    private val validators: List<(TreeValue<T>) -> String?> = emptyList(),
    disable: Boolean = false,
) : AbstractFormControl(disable) {
    private val resetValue = initialValue

    private val _state: MutableState<TreeValue<T>> =
        mutableStateOf(TreeValue(parent = ToggleableState.Off, children = initialValue))

    private val _valueChanges: MutableStateFlow<ValueChange<TreeValue<T>>> =
        MutableStateFlow(ValueChange(_state.value, ValueChangeType.Initialize))
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
        _valueChanges.update { ValueChange(_state.value, ValueChangeType.Set) }
        updateValidity()
    }

    fun setValue(vararg value: T) {
        _state.value = _state.value.copy(children = recalculateChildren(value.toList()))
        _valueChanges.update { ValueChange(_state.value, ValueChangeType.Set) }
        updateValidity()
    }

    fun setValue(value: ToggleableState) {
        _state.value = _state.value.copy(parent = value)
        _valueChanges.update { ValueChange(_state.value, ValueChangeType.Set) }
        updateValidity()
    }

    private fun recalculateChildren(value: List<T>): List<T> {
        val currentChildrenValue = _state.value.children.toMutableList()

        value.forEach { item ->
            if (isSelected(item)) {
                currentChildrenValue.remove(item)
            } else {
                currentChildrenValue.add(item)
            }
        }

        return currentChildrenValue
    }

    internal fun isSelected(item: T): Boolean {
        return _state.value.children.any { x -> equals(x, item) }
    }

    private fun updateValidity() {
        val error = validators.firstNotNullOfOrNull { validator -> validator(value) } ?: ""
        super.setError(error)
    }

    override fun reset() = resetStateTo(resetValue)

    fun reset(vararg value: T) = resetStateTo(value.toList())

    fun reset(value: List<T>) = resetStateTo(value)

    private fun resetStateTo(value: List<T>) {
        _state.value = _state.value.copy(children = recalculateChildren(value))
        _valueChanges.update { ValueChange(_state.value, ValueChangeType.Reset) }
        super.reset()
    }
}
