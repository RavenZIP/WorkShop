package com.ravenzip.workshop.components

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Простое текстовое поле
 *
 * Параметры:
 * 1) text - вводимый текст (обязательный)
 * 2) width - ширина текстового поля (по умолчанию 0.9f, не обязательный)
 * 3) textSize - размер вводимого текста и текста плейсхолдера (обязательный)
 * 4) placeholder - временный текст (обязательный)
 * 5) interactionSource - поток взаимодействий для поля ввода ( по умолчанию
 *    MutableInteractionSource(), не обязательный)
 * 6) colors - цвета текстового поля (по умолчанию берутся из темы приложения, не обязательный)
 * 7) showLine - отображать линию снизу текста (по умолчанию true, не обязательный)
 */
@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField(
    text: MutableState<String>,
    width: Float = 0.9f,
    textSize: Int,
    placeholder: String,
    interactionSource: InteractionSource = MutableInteractionSource(),
    colors: TextFieldColors = TextFieldDefaults.colors(),
    showLine: Boolean = true
) {
    BasicTextField(
        value = text.value,
        onValueChange = { text.value = it },
        textStyle = TextStyle(fontSize = textSize.sp),
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
 * Однострочное текстовое поле
 *
 * Параметры:
 * 1) text - вводимый текст (обязательный)
 * 2) maxLength - максимальная длина символов (по умолчанию 0, не обязательный)
 * 3) width - ширина текстового поля (по умолчанию 0.9f, не обязательный)
 * 4) pattern - паттерн вводимого текста (по умолчанию не задан, не обязательный)
 * 5) enabled - вкл\выкл текстового поля (по умолчанию true, не обязательный)
 * 6) readOnly - только для чтения (по умолчанию false, не обязательный)
 * 7) label - название текстового поля (по умолчанию не задан, не обязательный)
 * 8) leadingIcon - иконка слева (по умолчанию не задан, не обязательный)
 * 9) leadingIconDescription - описание для иконки (по умолчанию не задан, не обязательный)
 * 10) trailingIcon - иконка справа (по умолчанию не задан, не обязательный)
 * 11) trailingIconDescription - описание для иконки (по умолчанию не задан, не обязательный)
 * 12) isError - статус "Ошибка" (по умолчанию false, не обязательный)
 * 13) textError - текстовое сообщение об ошикбе (по умолчанию не задан, не обязательный)
 * 14) isPassword - замена вводимых символов на точки (по умолчанию falseБ не обязательный)
 * 15) keyboardOptions - опции для клавиатуры (по умолчанию стандартная клавиатура, не обязательный)
 * 16) shape - радиус скругления (по умолчанию 10.dp, не обязательный)
 * 17) colors - цвета текстового поля (по умолчанию берутся из темы приложения, не обязательный)
 * 18) showTextLengthCounter - отображение счетчика введенных сообщений (по умолчанию false, не
 *     обязательный)
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun SinglenessTextField(
    text: MutableState<String>,
    maxLength: Int = 0,
    width: Float = 0.9f,
    pattern: Regex? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String = "",
    leadingIcon: ImageVector? = null,
    leadingIconDescription: String = "",
    trailingIcon: ImageVector? = null,
    trailingIconDescription: String = "",
    isError: Boolean = false,
    textError: String = "",
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = false,
) {
    val isFocused = mutableStateOf(false)
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            getText(input = it, output = text, pattern = pattern, maxLength = maxLength)
        },
        modifier = Modifier.fillMaxWidth(width).onFocusChanged { isFocused.value = it.isFocused },
        enabled = enabled,
        readOnly = readOnly,
        label = { Text(text = label) },
        leadingIcon = getIcon(icon = leadingIcon, description = leadingIconDescription),
        trailingIcon = getIcon(icon = trailingIcon, description = trailingIconDescription),
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
        isFocused,
        colors,
        showTextLengthCounter
    )
}

/**
 * Многострочное текстовое поле
 *
 * Параметры:
 * 1) text - вводимый текст (обязательный)
 * 2) maxLength - максимальная длина символов (по умолчанию 0, не обязательный)
 * 3) width - ширина текстового поля (по умолчанию 0.9f, не обязательный)
 * 4) enabled - вкл\выкл текстового поля (по умолчанию true, не обязательный)
 * 5) readOnly - только для чтения (по умолчанию false, не обязательный)
 * 6) label - название текстового поля (по умолчанию не задан, не обязательный)
 * 7) isError - статус "Ошибка" (по умолчанию false, не обязательный)
 * 8) textError - текстовое сообщение об ошикбе (по умолчанию не задан, не обязательный)
 * 9) keyboardOptions - опции для клавиатуры (по умолчанию стандартная клавиатура, не обязательный)
 * 10) maxLines - минимальное число строк (по умолчанию 1, не обязательный)
 * 11) minLines - максимальное число строк (по умолчанию Int.MAX_VALUE, не обязательный)
 * 12) shape - радиус скругления (по умолчанию 10.dp, не обязательный)
 * 13) colors - цвета текстового поля (по умолчанию берутся из темы приложения, не обязательный)
 * 14) showTextLengthCounter - отображение счетчика введенных сообщений (по умолчанию false, не
 *     обязательный)
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun MultilineTextField(
    text: MutableState<String>,
    maxLength: Int = 0,
    width: Float = 0.9f,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String = "",
    isError: Boolean = false,
    textError: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = false
) {
    val isFocused = mutableStateOf(false)
    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        modifier = Modifier.fillMaxWidth(width).onFocusChanged { isFocused.value = it.isFocused },
        enabled = enabled,
        readOnly = readOnly,
        label = { Text(text = label) },
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
    isError: Boolean,
    textError: String,
    maxLength: Int,
    isFocused: MutableState<Boolean>,
    colors: TextFieldColors,
    showTextLengthCounter: Boolean,
) {
    Row(modifier = Modifier.fillMaxWidth(width)) {
        if (isError && textError != "") {
            Text(
                text = textError,
                modifier = Modifier.fillMaxWidth().weight(1f).padding(start = 10.dp),
                color = colors.errorLabelColor,
                fontSize = 12.sp
            )
        }
        if (showTextLengthCounter) {
            Text(
                text = if (maxLength > 0) "${text.length}/${maxLength}" else "${text.length}",
                modifier = Modifier.fillMaxWidth().weight(1f).padding(end = 5.dp),
                color =
                    if (isError) colors.errorLabelColor
                    else if (isFocused.value) colors.focusedLabelColor
                    else colors.unfocusedLabelColor,
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

/** Проверка на наличие паттерна и соответствие введенной строки патеррну */
private fun checkPattern(text: String, pattern: Regex?): Boolean {
    return pattern != null && text.matches(pattern)
}

/**
 * Проверка на то, что максимальная длина не задана или если задана, то проверка на соответствие
 * длины введенных символов заданному ограничению
 */
private fun checkLength(textLength: Int, maxLength: Int): Boolean {
    return maxLength == 0 || (maxLength > 0 && textLength <= maxLength)
}

private fun getText(input: String, output: MutableState<String>, pattern: Regex?, maxLength: Int) {
    if (
        checkPattern(input, pattern) && checkLength(input.length, maxLength) ||
            pattern == null && checkLength(input.length, maxLength)
    )
        output.value = input
    else {}
}

private fun getIcon(icon: ImageVector?, description: String): @Composable (() -> Unit)? {
    return if (icon != null) {
        { Icon(imageVector = icon, contentDescription = description) }
    } else null
}
