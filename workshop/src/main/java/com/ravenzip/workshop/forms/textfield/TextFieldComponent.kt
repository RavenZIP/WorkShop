package com.ravenzip.workshop.forms.textfield

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.ravenzip.workshop.enums.ValueChangeType
import com.ravenzip.workshop.forms.control.FormControl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class TextFieldComponent<T>(
    val control: FormControl<T>,
    val state: TextFieldState = TextFieldState(),
    scope: CoroutineScope,
) {
    init {
        scope.launch {
            control.valueChanges
                .filter { valueChanges -> valueChanges.typeChanges === ValueChangeType.RESET }
                .collect { _ -> state.setReadonly(state.readonly) }
        }
    }

    companion object {
        @Composable
        fun <T> create(
            control: FormControl<T>,
            state: TextFieldState = TextFieldState(),
            scope: CoroutineScope,
        ): TextFieldComponent<T> where T : Any {
            return remember { TextFieldComponent(control, state, scope) }
        }
    }
}
