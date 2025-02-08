package com.ravenzip.workshop.forms.dropdown

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.ravenzip.workshop.forms.control.FormControl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch

class DropDownTextFieldComponent<T>(
    val control: FormControl<T>,
    val state: DropDownTextFieldState<T>,
    scope: CoroutineScope,
) {
    fun setText(value: String) {
        control.setValue(control.resetValue)
        state.setText(value)
        state.setExpanded(true)
    }

    fun selectItem(value: T) {
        control.setValue(value)
        state.setExpanded(false)
    }

    init {
        scope.launch {
            merge(
                    control.valueChanges
                        .map { valueChanges -> state.view(valueChanges.value) }
                        .filter { value -> value.isNotEmpty() },
                    state.expandedChanges
                        .filter { expanded -> !expanded && control.isInvalid }
                        .map { state.view(control.resetValue) },
                )
                .collect { value -> state.setText(value) }
        }
    }

    companion object {
        @Composable
        fun <T> create(
            control: FormControl<T>,
            state: DropDownTextFieldState<T>,
            scope: CoroutineScope,
        ): DropDownTextFieldComponent<T> where T : Any {
            return remember { DropDownTextFieldComponent(control, state, scope) }
        }
    }
}
