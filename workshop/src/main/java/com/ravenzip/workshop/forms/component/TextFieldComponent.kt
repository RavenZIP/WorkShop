package com.ravenzip.workshop.forms.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.ravenzip.workshop.enums.ValueChangeType
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.state.TextFieldState
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
            control.valueChangesWithTypeChanges
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
