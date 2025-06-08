package com.ravenzip.workshop.components.textField

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.components.icon.Icon
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.state.TextFieldState
import com.ravenzip.workshop.forms.valueChange.ValueChangeType
import kotlinx.coroutines.flow.filter

/** Обертка над текстовыми полями */
@Composable
internal fun <T> TextFieldWrapper(
    control: FormControl<T>,
    state: TextFieldState = TextFieldState(),
    content: @Composable () -> Unit,
) {
    LaunchedEffect(control) {
        control.valueChangesWithTypeChanges
            .filter { valueChanges -> valueChanges.typeChanges is ValueChangeType.Reset }
            .collect { state.setReadonly(state.readonly) }
    }

    content()
}

/** Сообщение с описанием ошибки + счетчик введенных символов */
@Composable
internal fun ErrorMessageWithSymbolsCounter(
    isInvalid: Boolean,
    errorMessage: String,
    isFocused: Boolean,
    showTextLengthCounter: Boolean,
    maxLength: Int,
    currentLength: Int,
    colors: TextFieldColors,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        ErrorMessage(
            modifier = Modifier.weight(1f),
            isInvalid = isInvalid,
            errorMessage = errorMessage,
            colors = colors,
        )

        SymbolsCounter(
            modifier = Modifier.weight(1f),
            showTextLengthCounter = showTextLengthCounter,
            maxLength = maxLength,
            currentLength = currentLength,
            color = calculateColor(colors, isInvalid, isFocused),
        )
    }
}

/** Сообщение с описанием ошибки */
@Composable
internal fun ErrorMessage(
    modifier: Modifier = Modifier,
    isInvalid: Boolean,
    errorMessage: String,
    colors: TextFieldColors,
) {
    if (isInvalid) {
        Text(
            text = errorMessage,
            modifier = Modifier.fillMaxWidth().then(modifier).padding(start = 10.dp),
            color = colors.errorLabelColor,
            fontSize = 12.sp,
        )
    }
}

/** Счетчик введенных символов */
@Composable
internal fun SymbolsCounter(
    modifier: Modifier = Modifier,
    showTextLengthCounter: Boolean,
    maxLength: Int,
    currentLength: Int,
    color: Color,
) {
    if (showTextLengthCounter) {
        Text(
            text = if (maxLength > 0) "$currentLength / $maxLength" else "$currentLength",
            modifier = Modifier.fillMaxWidth().then(modifier).padding(end = 10.dp),
            color = color,
            fontSize = 12.sp,
            textAlign = TextAlign.End,
        )
    }
}

/**
 * Проверка на то, что максимальная длина не задана или если задана, то проверка на соответствие
 * длины введенных символов заданному ограничению
 */
internal fun checkLength(textLength: Int, maxLength: Int): Boolean {
    return maxLength == 0 || (maxLength > 0 && textLength <= maxLength)
}

internal fun getText(text: String?): @Composable (() -> Unit)? {
    return if (text != null) {
        { Text(text = text) }
    } else null
}

internal fun getIcon(
    icon: IconData?,
    iconConfig: IconConfig,
    colorSelector: () -> Color,
): @Composable (() -> Unit)? {
    return if (icon != null) {
        {
            val color by remember { derivedStateOf { colorSelector() } }

            Icon(icon = icon, iconConfig = iconConfig, defaultColor = color)
        }
    } else null
}

internal fun calculateColor(colors: TextFieldColors, isError: Boolean, isFocused: Boolean): Color {
    return if (isError) colors.errorLabelColor
    else if (isFocused) colors.focusedLabelColor else colors.unfocusedLabelColor
}
