package com.ravenzip.workshop

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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
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
 */
@Composable
fun SimpleTextField(
    text: MutableState<String>,
    maxLength: Int = 0,
    width: Float = 0.9f,
    pattern: Regex? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String,
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true,
    maxLines: Int = 1,
    shape: Shape = RoundedCornerShape(15),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors()
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
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        maxLines = if (!singleLine && maxLines > 0) maxLines else 1,
        shape = shape,
        colors = colors
    )
    if (maxLength > 0) {
        Text(
            text = "${text.value.length}/${maxLength}",
            modifier = Modifier.fillMaxWidth(width).padding(end = 5.dp),
            color = colors.focusedIndicatorColor,
            fontSize = 12.sp,
            textAlign = TextAlign.End
        )
    }
}

private fun checkPattern(text: String, pattern: Regex?): Boolean {
    return pattern != null && text.matches(pattern)
}

private fun checkLength(textLength: Int, maxLength: Int): Boolean {
    return maxLength == 0 || (maxLength > 0 && textLength <= maxLength)
}
