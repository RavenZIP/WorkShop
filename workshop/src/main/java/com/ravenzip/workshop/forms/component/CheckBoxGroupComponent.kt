package com.ravenzip.workshop.forms.component

import androidx.compose.runtime.Immutable
import com.ravenzip.workshop.data.Equatable
import com.ravenzip.workshop.forms.control.FormControlMulti
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
@Immutable
class CheckBoxGroupComponent<T : Equatable>(
    val control: FormControlMulti<T>,
    val source: List<T>,
    val view: (T) -> String,
) {
    fun selectAll() {
        source.filter { item -> item !in control.value }.forEach { item -> control.setValue(item) }
    }

    fun unselectAll() {
        source.filter { item -> item in control.value }.forEach { item -> control.setValue(item) }
    }
}
