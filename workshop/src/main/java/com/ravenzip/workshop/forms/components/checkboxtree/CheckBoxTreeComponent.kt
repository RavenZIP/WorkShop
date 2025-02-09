package com.ravenzip.workshop.forms.components.checkboxtree

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.state.ToggleableState
import com.ravenzip.workshop.forms.control.FormControlMulti

// TODO скорее всего для дерева нужен отдельный контрол (?), чтобы при получении value у нас было
// одновременно значение родителя и дочернего контрола
class CheckBoxTreeComponent<T>(
    val children: FormControlMulti<T>,
    val source: List<T>,
    val view: (T) -> String,
) {
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
            children.selectAll(source)
        } else {
            children.unselectAll()
        }
    }

    fun recalculateParentState() {
        val activeCheckboxCount =
            children.value.fold(initial = 0) { acc, item ->
                if (children.isSelected(item)) acc + 1 else acc
            }

        _parentState.value =
            when (activeCheckboxCount) {
                0 -> ToggleableState.Off
                source.count() -> ToggleableState.On
                else -> ToggleableState.Indeterminate
            }
    }
}
