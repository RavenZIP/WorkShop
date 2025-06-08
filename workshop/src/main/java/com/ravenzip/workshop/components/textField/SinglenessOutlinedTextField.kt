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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.state.TextFieldState

/**
 * [SinglenessOutlinedTextField] - Однострочное текстовое поле
 *
 * @param control Контрол элемента
 * @param state Состояния текстового поля
 * @param maxLength Максимальная длина символов
 * @param width Ширина текстового поля
 * @param modifier Кастомные модификаторы
 * @param label Название текстового поля
 * @param leadingIcon Иконка слева
 * @param leadingIconConfig параметры иконки слева
 * @param trailingIcon Иконка справа
 * @param trailingIconConfig параметры иконки справа
 * @param isHiddenText Замена вводимых символов на точки
 * @param keyboardOptions Опции для клавиатуры
 * @param shape Радиус скругления
 * @param colors Цвета текстового поля
 * @param showTextLengthCounter Отображение счетчика введенных сообщений
 * @sample com.ravenzip.workshop.components.textField.sample.SinglenessOutlinedTextFieldSample
 */
@Composable
fun SinglenessOutlinedTextField(
    modifier: Modifier = Modifier,
    control: FormControl<String>,
    state: TextFieldState = TextFieldState(),
    maxLength: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    label: String = "Простое текстовое поле",
    leadingIcon: IconData? = null,
    leadingIconConfig: IconConfig = IconConfig.Small,
    trailingIcon: IconData? = null,
    trailingIconConfig: IconConfig = IconConfig.Small,
    isHiddenText: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = false,
) {
    TextFieldWrapper(control, state) {
        Column(modifier = Modifier.fillMaxWidth(width)) {
            OutlinedTextField(
                value = control.value,
                onValueChange = { if (checkLength(it.length, maxLength)) control.setValue(it) },
                modifier =
                    Modifier.fillMaxWidth()
                        .onFocusChanged { focusState ->
                            state.changeFocusState(focusState.isFocused)
                        }
                        .then(modifier),
                enabled = control.isEnabled,
                readOnly = state.isReadonly,
                label = { Text(text = label) },
                leadingIcon =
                    getIcon(
                        icon = leadingIcon,
                        iconConfig = leadingIconConfig,
                        colorSelector = {
                            calculateColor(colors, control.isInvalid, state.isFocused)
                        },
                    ),
                trailingIcon =
                    getIcon(
                        icon = trailingIcon,
                        iconConfig = trailingIconConfig,
                        colorSelector = {
                            calculateColor(colors, control.isInvalid, state.isFocused)
                        },
                    ),
                isError = control.isInvalid,
                visualTransformation =
                    if (isHiddenText) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = keyboardOptions,
                singleLine = true,
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
