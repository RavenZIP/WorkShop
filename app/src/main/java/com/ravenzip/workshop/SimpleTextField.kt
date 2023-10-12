package com.ravenzip.workshop

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation

/**
 * Текстовое поле
 *
 * Опционально:
 */
@OptIn(ExperimentalMaterial3Api::class)
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
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            if (
                pattern != null &&
                    it.matches(pattern) &&
                    (maxLength == 0 || (maxLength > 0 && it.length <= maxLength))
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
}
