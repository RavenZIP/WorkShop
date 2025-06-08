package com.ravenzip.workshop.components.textField

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.state.TextFieldState

/**
 * [MultilineTextField] - Многострочное текстовое поле
 *
 * @param control Контрол элемента
 * @param state Состояния текстового поля
 * @param maxLength Максимальная длина символов
 * @param width Ширина текстового поля
 * @param modifier Кастомные модификаторы
 * @param label Название текстового поля
 * @param keyboardOptions Опции для клавиатуры
 * @param maxLines Минимальное число строк
 * @param minLines Максимальное число строк
 * @param shape Радиус скругления
 * @param colors Цвета текстового поля
 * @param showTextLengthCounter Отображение счетчика введенных сообщений
 */
@Composable
fun MultilineTextField(
    modifier: Modifier = Modifier,
    control: FormControl<String>,
    state: TextFieldState = TextFieldState(),
    maxLength: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    label: String = "Многострочное текстовое поле",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = false,
) {
    TextFieldWrapper(control, state) {
        Column(modifier = Modifier.fillMaxWidth(width)) {
            OutlinedTextField(
                value = control.value,
                onValueChange = { value -> control.setValue(value) },
                modifier =
                    Modifier.fillMaxWidth()
                        .onFocusChanged { focusState ->
                            state.changeFocusState(focusState.isFocused)
                        }
                        .then(modifier),
                enabled = control.isEnabled,
                readOnly = state.isReadonly,
                label = { Text(text = label) },
                isError = control.isInvalid,
                keyboardOptions = keyboardOptions,
                maxLines = maxLines,
                minLines = minLines,
                shape = shape,
                colors = colors,
            )

            ErrorMessageWithSymbolsCounter(
                isInvalid = control.isInvalid,
                errorMessage = control.errorMessage,
                isFocused = state.isFocused,
                showTextLengthCounter = showTextLengthCounter,
                maxLength = maxLength,
                currentLength = control.value.length,
                colors = colors,
            )
        }
    }
}
