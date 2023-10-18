package com.ravenzip.workshop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Текстовое поле
 *
 * Опционально:
 * 1) Максимальное количество символов
 * 2) Ширина текстового поля
 * 3) Паттерн
 * 4) Состояние (вкл\выкл)
 * 5) Только для чтения
 * 6) Название поля
 * 7) Текст плейсхолдера
 * 8) Иконка слева
 * 9) Иконка справа
 * 10) Статус "Ошибка"
 * 11) Текст ошибки
 * 12) Изменение визуального отображения текста в поле
 * 13) Опции для клавиатуры
 * 14) Радиус скругления
 * 15) Цвета текстового поля (по умолчанию берутся из темы приложения)
 * 16) Отображать счетчик введенных сообщений (на данный момент работает относительно указанного
 *     максимального количества символов)
 */
@Composable
fun SimpleTextField(
    text: MutableState<String>,
    maxLength: Int = 0,
    width: Float = 0.9f,
    pattern: Regex? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String = "",
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    textError: String = "",
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = true
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            if (
                checkPattern(it, pattern) && checkLength(it.length, maxLength) ||
                    pattern == null && checkLength(it.length, maxLength)
            ) {
                text.value = it
            }
        },
        modifier = Modifier.fillMaxWidth(width),
        enabled = enabled,
        readOnly = readOnly,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        visualTransformation =
            if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        shape = shape,
        colors = colors
    )
    ErrorMessageAndSymbolsCounter(
        text.value,
        width,
        isError,
        textError,
        maxLength,
        colors,
        showTextLengthCounter
    )
}

/**
 * Многострочное текстовое поле
 *
 * Опционально:
 * 1) Максимальное количество символов
 * 2) Ширина текстового поля
 * 3) Состояние (вкл\выкл)
 * 4) Только для чтения
 * 5) Название поля
 * 6) Текст плейсхолдера
 * 7) Статус "Ошибка"
 * 8) Текст ошибки
 * 9) Опции для клавиатуры
 * 10) Максимальное число строк
 * 11) Минимальное число строк
 * 12) Опции для клавиатуры
 * 13) Цвета текстового поля (по умолчанию берутся из темы приложения)
 * 14) Отображать счетчик введенных сообщений (на данный момент работает относительно указанного
 *     максимального количества символов)
 */
@Composable
fun MultilineTextField(
    text: MutableState<String>,
    maxLength: Int = 0,
    width: Float = 0.9f,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String = "",
    placeholder: String = "",
    isError: Boolean = false,
    textError: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = true
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        modifier = Modifier.fillMaxWidth(width),
        enabled = enabled,
        readOnly = readOnly,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        isError = isError,
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        minLines = minLines,
        shape = shape,
        colors = colors
    )
    ErrorMessageAndSymbolsCounter(
        text.value,
        width,
        isError,
        textError,
        maxLength,
        colors,
        showTextLengthCounter
    )
}

@Composable
private fun ErrorMessageAndSymbolsCounter(
    text: String,
    width: Float,
    isError: Boolean,
    textError: String,
    maxLength: Int,
    colors: TextFieldColors,
    showTextLengthCounter: Boolean,
) {
    Row(modifier = Modifier.fillMaxWidth(width), horizontalArrangement = Arrangement.SpaceBetween) {
        if (isError) {
            Text(
                text = textError,
                modifier = Modifier.padding(start = 10.dp),
                color = colors.errorLabelColor,
                fontSize = 12.sp
            )
        }
        if (maxLength > 0 && showTextLengthCounter) {
            Text(
                text = "${text.length}/${maxLength}",
                modifier = Modifier.padding(end = 5.dp),
                color = colors.focusedIndicatorColor,
                fontSize = 12.sp
            )
        }
    }
}

private fun checkPattern(text: String, pattern: Regex?): Boolean {
    return pattern != null && text.matches(pattern)
}

private fun checkLength(textLength: Int, maxLength: Int): Boolean {
    return maxLength == 0 || (maxLength > 0 && textLength <= maxLength)
}