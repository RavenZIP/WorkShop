package com.ravenzip.workshop.forms.components.checkboxtree

import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.data.Equatable
import com.ravenzip.workshop.forms.control.FormControlTree

@Immutable
@Deprecated("Не использовать, пока что не работает", level = DeprecationLevel.ERROR)
class CheckBoxTreeComponent<T : Equatable>(
    val control: FormControlTree<T>,
    val source: List<T>,
    val view: (T) -> String,
) {
    fun selectAll() {
        source
            .filter { item -> item !in control.value.children }
            .forEach { item -> control.setValue(item) }
    }

    fun unselectAll() {
        source
            .filter { item -> item in control.value.children }
            .forEach { item -> control.setValue(item) }
    }

    // TODO не работает, старая логика
    //    fun changeParentState() {
    //        control.value.parent =
    //            when (_parentState.value) {
    //                ToggleableState.On -> ToggleableState.Off
    //                else -> ToggleableState.On
    //            }
    //
    //        if (_parentState.value == ToggleableState.On) {
    //            selectAll()
    //        } else {
    //            unselectAll()
    //        }
    //    }
}
