package com.ravenzip.workshop.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.data.Error
import com.ravenzip.workshop.data.IconParameters

/**
 * [SimpleTextField] - Простое текстовое поле
 *
 * @param text Вводимый текст
 * @param width Ширина текстового поля
 * @param textSize Размер вводимого текста и текста плейсхолдера
 * @param placeholder Временный текст
 * @param interactionSource Поток взаимодействий для поля ввода
 * @param colors Цвета текстового поля
 * @param showLine Отображать линию снизу текста
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField(
    text: MutableState<String>,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    textSize: Int = 16,
    placeholder: String = "",
    interactionSource: InteractionSource = MutableInteractionSource(),
    colors: TextFieldColors = TextFieldDefaults.colors(),
    showLine: Boolean = true
) {
    BasicTextField(
        value = text.value,
        onValueChange = { text.value = it },
        textStyle = TextStyle(color = colors.unfocusedTextColor, fontSize = textSize.sp),
        modifier = Modifier.fillMaxWidth(width),
    ) {
        TextFieldDefaults.DecorationBox(
            value = text.value,
            innerTextField = it,
            enabled = true,
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            placeholder = { Text(text = placeholder, fontSize = textSize.sp) },
            colors = colors,
            contentPadding = PaddingValues(3.dp),
            container = {
                if (showLine) {
                    Line(interactionSource = interactionSource, colors = colors)
                }
            }
        )
    }
}

/**
 * [SinglenessTextField] - Однострочное текстовое поле
 *
 * @param text Вводимый текст
 * @param maxLength Максимальная длина символов
 * @param width Ширина текстового поля
 * @param enabled Вкл\выкл текстового поля
 * @param readOnly Только для чтения
 * @param label Название текстового поля
 * @param leadingIcon Иконка слева
 * @param trailingIcon Иконка справа
 * @param error Отображение ошибки
 * @param isHiddenText Замена вводимых символов на точки
 * @param keyboardOptions Опции для клавиатуры
 * @param shape Радиус скругления
 * @param colors Цвета текстового поля
 * @param showTextLengthCounter Отображение счетчика введенных сообщений
 */
@Composable
fun SinglenessTextField(
    text: MutableState<String>,
    maxLength: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String = "Простое текстовое поле",
    leadingIcon: IconParameters? = null,
    trailingIcon: IconParameters? = null,
    error: Error = Error(),
    isHiddenText: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = false,
) {
    var isFocused by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = text.value,
        onValueChange = { if (checkLength(it.length, maxLength)) text.value = it },
        modifier = Modifier.fillMaxWidth(width).onFocusChanged { isFocused = it.isFocused },
        enabled = enabled,
        readOnly = readOnly,
        label = { Text(text = label) },
        leadingIcon =
            getIcon(
                icon = leadingIcon,
                colors = colors,
                isError = error.value,
                isFocused = isFocused
            ),
        trailingIcon =
            getIcon(
                icon = trailingIcon,
                colors = colors,
                isError = error.value,
                isFocused = isFocused
            ),
        isError = error.value,
        visualTransformation =
            if (isHiddenText) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        shape = shape,
        colors = colors
    )
    ErrorMessageAndSymbolsCounter(
        text.value,
        width,
        error,
        maxLength,
        isFocused,
        colors,
        showTextLengthCounter
    )
}

/**
 * [MultilineTextField] - Многострочное текстовое поле
 *
 * @param text Вводимый текст
 * @param maxLength Максимальная длина символов
 * @param width Ширина текстового поля
 * @param enabled Вкл\выкл текстового поля
 * @param readOnly Только для чтения
 * @param label Название текстового поля
 * @param error Отображение ошибки
 * @param keyboardOptions Опции для клавиатуры
 * @param maxLines Минимальное число строк
 * @param minLines Максимальное число строк
 * @param shape Радиус скругления
 * @param colors Цвета текстового поля
 * @param showTextLengthCounter Отображение счетчика введенных сообщений
 */
@Composable
fun MultilineTextField(
    text: MutableState<String>,
    maxLength: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String = "Многострочное текстовое поле",
    error: Error = Error(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = false
) {
    var isFocused by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        modifier = Modifier.fillMaxWidth(width).onFocusChanged { isFocused = it.isFocused },
        enabled = enabled,
        readOnly = readOnly,
        label = { Text(text = label) },
        isError = error.value,
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        minLines = minLines,
        shape = shape,
        colors = colors
    )
    ErrorMessageAndSymbolsCounter(
        text.value,
        width,
        error,
        maxLength,
        isFocused,
        colors,
        showTextLengthCounter
    )
}

// TODO RichTextField
@Composable fun RichTexField() {}

/** Элемент вывода сообщения об ошибке при вводе и счетчик введенных символов */
@Composable
private fun ErrorMessageAndSymbolsCounter(
    text: String,
    width: Float,
    error: Error,
    maxLength: Int,
    isFocused: Boolean,
    colors: TextFieldColors,
    showTextLengthCounter: Boolean,
) {
    Row(modifier = Modifier.fillMaxWidth(width)) {
        if (error.value && error.text != "") {
            Text(
                text = error.text,
                modifier = Modifier.fillMaxWidth().weight(1f).padding(start = 10.dp),
                color = colors.errorLabelColor,
                fontSize = 12.sp
            )
        }
        if (showTextLengthCounter) {
            Text(
                text = if (maxLength > 0) "${text.length} / $maxLength" else "${text.length}",
                modifier = Modifier.fillMaxWidth().weight(1f).padding(end = 5.dp),
                color = getColor(colors, error.value, isFocused),
                fontSize = 12.sp,
                textAlign = TextAlign.End
            )
        }
    }
}

/** Линия под текстом */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Line(interactionSource: InteractionSource, colors: TextFieldColors) {
    Box(
        Modifier.indicatorLine(
            enabled = true,
            isError = false,
            interactionSource = interactionSource,
            colors = colors
        )
    )
}

/**
 * Проверка на то, что максимальная длина не задана или если задана, то проверка на соответствие
 * длины введенных символов заданному ограничению
 */
private fun checkLength(textLength: Int, maxLength: Int): Boolean {
    return maxLength == 0 || (maxLength > 0 && textLength <= maxLength)
}

private fun getIcon(
    icon: IconParameters?,
    colors: TextFieldColors,
    isError: Boolean,
    isFocused: Boolean
): @Composable (() -> Unit)? {
    return if (icon != null) {
        {
            Icon(
                imageVector = icon.value,
                contentDescription = icon.description,
                modifier = Modifier.size(icon.size.dp),
                tint = getColor(colors = colors, isError = isError, isFocused = isFocused)
            )
        }
    } else null
}

private fun getColor(colors: TextFieldColors, isError: Boolean, isFocused: Boolean): Color {
    return if (isError) colors.errorLabelColor
    else if (isFocused) colors.focusedLabelColor else colors.unfocusedLabelColor
}
