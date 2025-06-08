package com.ravenzip.workshop.components.textField

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.state.TextFieldState

/**
 * [SinglenessTextField] - Однострочное текстовое поле
 *
 * @param control Контрол элемента
 * @param state Состояния текстового поля
 * @param maxLength Максимальная длина символов
 * @param width Ширина текстового поля
 * @param modifier Кастомные модификаторы
 * @param placeholder Подсказка текстового поля
 * @param leadingIcon Иконка слева
 * @param leadingIconConfig Параметры иконки слева
 * @param trailingIcon Иконка справа
 * @param trailingIconConfig параметры иконки справа
 * @param isHiddenText Замена вводимых символов на точки
 * @param keyboardOptions Опции для клавиатуры
 * @param shape Радиус скругления
 * @param colors Цвета текстового поля
 * @param showTextLengthCounter Отображение счетчика введенных сообщений
 */
@Composable
fun SinglenessTextField(
    modifier: Modifier = Modifier,
    control: FormControl<String>,
    state: TextFieldState = TextFieldState(),
    maxLength: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    placeholder: String? = null,
    leadingIcon: IconData? = null,
    leadingIconConfig: IconConfig = IconConfig.Small,
    trailingIcon: IconData? = null,
    trailingIconConfig: IconConfig = IconConfig.Small,
    isHiddenText: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors =
        TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
    showTextLengthCounter: Boolean = false,
) {
    TextFieldWrapper(control, state) {
        Column(modifier = Modifier.fillMaxWidth(width)) {
            TextField(
                value = control.value,
                onValueChange = { value ->
                    if (checkLength(value.length, maxLength)) control.setValue(value)
                },
                modifier =
                    Modifier.fillMaxWidth()
                        .onFocusChanged { focusState ->
                            state.changeFocusState(focusState.isFocused)
                        }
                        .then(modifier),
                readOnly = state.isReadonly,
                placeholder = getText(placeholder),
                leadingIcon =
                    getIcon(
                        icon = leadingIcon,
                        iconConfig = leadingIconConfig,
                        colorSelector = { colors.cursorColor },
                    ),
                trailingIcon =
                    getIcon(
                        icon = trailingIcon,
                        iconConfig = trailingIconConfig,
                        colorSelector = { colors.cursorColor },
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
