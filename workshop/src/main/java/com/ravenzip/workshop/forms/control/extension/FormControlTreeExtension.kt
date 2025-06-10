package com.ravenzip.workshop.forms.control.extension

import androidx.compose.ui.state.ToggleableState
import com.ravenzip.workshop.forms.control.FormControlTree

// TODO аналогичные вопросы\обсуждение как и для FormControlMulti

fun <T> FormControlTree<T>.changeParentState() {
    val calculatedParentValue =
        when (this.value.parent) {
            ToggleableState.On -> ToggleableState.Off
            else -> ToggleableState.On
        }

    this.setValue(calculatedParentValue)
}

fun <T> FormControlTree<T>.selectAll(source: List<T>) {
    val unselectedItems = source.filter { x -> !this.isSelected(x) }
    this.setValue(unselectedItems)
}

fun <T> FormControlTree<T>.unselectAll(source: List<T>) {
    val selectedItems = source.filter { x -> this.isSelected(x) }
    this.setValue(selectedItems)
}
