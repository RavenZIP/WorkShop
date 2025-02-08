package com.ravenzip.workshop.forms

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.state.ToggleableState
import com.ravenzip.workshop.forms.control.FormControlMulti
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
class CheckBoxTreeFormState<T>(
    initialValue: List<T>,
    source: List<T>,
    comparableKey: (T) -> Any,
    sourceView: (T) -> String,
    validators: List<(List<T>) -> String?> = emptyList(),
    disable: Boolean = false,
) : FormControlMulti<T>(initialValue, source, comparableKey, sourceView, validators, disable) {
    private val _parentState = mutableStateOf(ToggleableState.Off)

    val parent: ToggleableState
        get() = _parentState.value

    fun changeParentState() {
        _parentState.value =
            when (_parentState.value) {
                ToggleableState.On -> ToggleableState.Off
                else -> ToggleableState.On
            }

        if (_parentState.value == ToggleableState.On) {
            super.selectAll()
        } else {
            super.unselectAll()
        }
    }

    override fun setValue(value: T) {
        super.setValue(value)
        recalculateParentState()
    }

    private fun recalculateParentState() {
        val activeCheckboxCount =
            super.value.fold(initial = 0) { acc, item ->
                if (super.isSelected(item)) acc + 1 else acc
            }

        _parentState.value =
            when (activeCheckboxCount) {
                0 -> ToggleableState.Off
                super.items.count() -> ToggleableState.On
                else -> ToggleableState.Indeterminate
            }
    }
}
