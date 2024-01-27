package com.ravenzip.workshop.components

import androidx.annotation.FloatRange
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
 * @param text вводимый текст (обязательный)
 * @param width ширина текстового поля (по умолчанию 0.9f, не обязательный)
 * @param textSize размер вводимого текста и текста плейсхолдера (обязательный)
 * @param placeholder временный текст (обязательный)
 * @param interactionSource поток взаимодействий для поля ввода ( по умолчанию
 *   MutableInteractionSource(), не обязательный)
 * @param colors цвета текстового поля (по умолчанию берутся из темы приложения, не обязательный)
 * @param showLine отображать линию снизу текста (по умолчанию true, не обязательный)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField(
    text: MutableState<String>,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    textSize: Int,
    placeholder: String,
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
 * @param text вводимый текст (обязательный)
 * @param maxLength максимальная длина символов (по умолчанию 0, не обязательный)
 * @param width ширина текстового поля (по умолчанию 0.9f, не обязательный)
 * @param enabled вкл\выкл текстового поля (по умолчанию true, не обязательный)
 * @param readOnly только для чтения (по умолчанию false, не обязательный)
 * @param label название текстового поля (по умолчанию не задан, не обязательный)
 * @param leadingIcon иконка слева (по умолчанию не задан, не обязательный)
 * @param trailingIcon иконка справа (по умолчанию не задан, не обязательный)
 * @param error отображение ошибки (не обязательный)
 * @param isPassword замена вводимых символов на точки (по умолчанию false, не обязательный)
 * @param keyboardOptions опции для клавиатуры (по умолчанию стандартная клавиатура, не
 *   обязательный)
 * @param shape радиус скругления (по умолчанию 10.dp, не обязательный)
 * @param colors цвета текстового поля (по умолчанию берутся из темы приложения, не обязательный)
 * @param showTextLengthCounter отображение счетчика введенных сообщений (по умолчанию false, не
 *   обязательный)
 */
@Composable
fun SinglenessTextField(
    text: MutableState<String>,
    maxLength: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String = "",
    leadingIcon: IconParameters? = null,
    trailingIcon: IconParameters? = null,
    error: Error = Error(),
    isPassword: Boolean = false,
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
        leadingIcon = getIcon(icon = leadingIcon),
        trailingIcon = getIcon(icon = trailingIcon),
        isError = error.value,
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
 * @param text вводимый текст (обязательный)
 * @param maxLength максимальная длина символов (по умолчанию 0, не обязательный)
 * @param width ширина текстового поля (по умолчанию 0.9f, не обязательный)
 * @param enabled вкл\выкл текстового поля (по умолчанию true, не обязательный)
 * @param readOnly только для чтения (по умолчанию false, не обязательный)
 * @param label название текстового поля (по умолчанию не задан, не обязательный)
 * @param error отображение ошибки (не обязательный)
 * @param keyboardOptions опции для клавиатуры (по умолчанию стандартная клавиатура, не
 *   обязательный)
 * @param maxLines минимальное число строк (по умолчанию 1, не обязательный)
 * @param minLines максимальное число строк (по умолчанию Int.MAX_VALUE, не обязательный)
 * @param shape радиус скругления (по умолчанию 10.dp, не обязательный)
 * @param colors цвета текстового поля (по умолчанию берутся из темы приложения, не обязательный)
 * @param showTextLengthCounter отображение счетчика введенных сообщений (по умолчанию false, не
 *   обязательный)
 */
@Composable
fun MultilineTextField(
    text: MutableState<String>,
    maxLength: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String = "",
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

private fun getIcon(icon: IconParameters?): @Composable (() -> Unit)? {
    return if (icon != null) {
        { Icon(imageVector = icon.value, contentDescription = icon.description) }
    } else null
}

private fun getColor(colors: TextFieldColors, isError: Boolean, isFocused: Boolean): Color {
    return if (isError) colors.errorLabelColor
    else if (isFocused) colors.focusedLabelColor else colors.unfocusedLabelColor
}
