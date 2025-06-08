package com.ravenzip.workshop.components.textField

import androidx.annotation.FloatRange
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.indicatorLine
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.state.TextFieldState

/**
 * [SimpleTextField] - Простое текстовое поле
 *
 * @param control Контрол элемента
 * @param state Состояния текстового поля
 * @param width Ширина текстового поля
 * @param modifier Кастомные модификаторы
 * @param textSize Размер вводимого текста и текста плейсхолдера
 * @param placeholder Временный текст
 * @param interactionSource Поток взаимодействий для поля ввода
 * @param colors Цвета текстового поля
 * @param showLine Отображать линию снизу текста
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField(
    modifier: Modifier = Modifier,
    control: FormControl<String>,
    state: TextFieldState = TextFieldState(),
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    textSize: Int = 16,
    placeholder: String = "",
    interactionSource: InteractionSource = MutableInteractionSource(),
    colors: TextFieldColors = TextFieldDefaults.colors(),
    showLine: Boolean = true,
) {
    TextFieldWrapper(control, state) {
        BasicTextField(
            value = control.value,
            onValueChange = { value -> control.setValue(value) },
            readOnly = state.isReadonly,
            textStyle = TextStyle(color = colors.unfocusedTextColor, fontSize = textSize.sp),
            modifier = Modifier.fillMaxWidth(width).then(modifier),
        ) {
            TextFieldDefaults.DecorationBox(
                value = control.value,
                innerTextField = it,
                enabled = control.isEnabled,
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
                },
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
            colors = colors,
        )
    )
}
