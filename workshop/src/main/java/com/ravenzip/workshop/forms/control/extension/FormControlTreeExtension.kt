package com.ravenzip.workshop.forms.control.extension

import androidx.compose.ui.state.ToggleableState
import com.ravenzip.workshop.model.Equatable
import com.ravenzip.workshop.forms.control.FormControlTree

// TODO аналогичные вопросы\обсуждение как и для FormControlMulti

fun <T : Equatable> FormControlTree<T>.changeParentState() {
    val calculatedParentValue =
        when (this.value.parent) {
            ToggleableState.On -> ToggleableState.Off
            else -> ToggleableState.On
        }

    this.setValue(calculatedParentValue)
}

fun <T : Equatable> FormControlTree<T>.selectAll(source: List<T>) {
    val unselectedItems = source.filter { item -> item !in this.value.children }
    this.setValue(unselectedItems)
}

fun <T : Equatable> FormControlTree<T>.unselectAll(source: List<T>) {
    val selectedItems = source.filter { item -> item in this.value.children }
    this.setValue(selectedItems)
}
