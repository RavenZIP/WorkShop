package com.ravenzip.workshop.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.R
import com.ravenzip.workshop.data.Error
import com.ravenzip.workshop.data.icon.Icon
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.forms.state.DropDownTextFieldState
import com.ravenzip.workshop.forms.state.TextFieldState

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
    showLine: Boolean = true,
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
            },
        )
    }
}

/**
 * [SinglenessTextField] - Однострочное текстовое поле
 *
 * @param text вводимый текст
 * @param width ширина текстового поля
 * @param readOnly только для чтения
 * @param placeholder подсказка текстового поля
 * @param leadingIcon иконка слева
 * @param trailingIcon иконка справа
 * @param keyboardOptions опции для клавиатуры
 * @param shape радиус скругления
 * @param colors цвета текстового поля
 */
@Composable
@Deprecated("Переходить на SinglenessTextField с FormState")
fun SinglenessTextField(
    text: MutableState<String>,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    readOnly: Boolean = false,
    placeholder: String? = null,
    leadingIcon: Icon? = null,
    leadingIconConfig: IconConfig = IconConfig.Small,
    trailingIcon: Icon? = null,
    trailingIconConfig: IconConfig = IconConfig.Small,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors =
        TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
) {
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        modifier = Modifier.fillMaxWidth(width),
        readOnly = readOnly,
        placeholder = getText(placeholder),
        leadingIcon = getIcon(icon = leadingIcon, iconConfig = leadingIconConfig, colors = colors),
        trailingIcon =
            getIcon(icon = trailingIcon, iconConfig = trailingIconConfig, colors = colors),
        keyboardOptions = keyboardOptions,
        singleLine = true,
        shape = shape,
        colors = colors,
    )
}

/**
 * [SinglenessTextField] - Однострочное текстовое поле
 *
 * @param state Состояние текстового поля
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
@ExperimentalMaterial3Api
@Composable
fun SinglenessTextField(
    state: TextFieldState<String>,
    maxLength: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    leadingIcon: Icon? = null,
    leadingIconConfig: IconConfig = IconConfig.Small,
    trailingIcon: Icon? = null,
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
    Column(modifier = Modifier.fillMaxWidth(width)) {
        TextField(
            value = state.value,
            onValueChange = { value ->
                if (checkLength(value.length, maxLength)) state.setValue(value)
            },
            modifier =
                Modifier.fillMaxWidth()
                    .onFocusChanged { focusState -> state.changeFocusState(focusState.isFocused) }
                    .then(modifier),
            readOnly = state.isReadonly,
            placeholder = getText(placeholder),
            leadingIcon =
                getIcon(icon = leadingIcon, iconConfig = leadingIconConfig, colors = colors),
            trailingIcon =
                getIcon(icon = trailingIcon, iconConfig = trailingIconConfig, colors = colors),
            isError = state.isInvalid,
            visualTransformation =
                if (isHiddenText) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            shape = shape,
            colors = colors,
        )

        ErrorMessageWithSymbolsCounter(
            isInvalid = state.isInvalid,
            errorMessage = state.errorMessage,
            isFocused = state.isFocused,
            showTextLengthCounter = showTextLengthCounter,
            maxLength = maxLength,
            currentLength = state.value.length,
            colors = colors,
        )
    }
}

/**
 * [SinglenessOutlinedTextField] - Однострочное текстовое поле
 *
 * @param text Вводимый текст
 * @param maxLength Максимальная длина символов
 * @param width Ширина текстового поля
 * @param enabled Вкл\выкл текстового поля
 * @param modifier Кастомные модификаторы
 * @param readOnly Только для чтения
 * @param label Название текстового поля
 * @param leadingIcon Иконка слева
 * @param leadingIconConfig параметры иконки слева
 * @param trailingIcon Иконка справа
 * @param trailingIconConfig параметры иконки справа
 * @param error Отображение ошибки
 * @param isHiddenText Замена вводимых символов на точки
 * @param keyboardOptions Опции для клавиатуры
 * @param shape Радиус скругления
 * @param colors Цвета текстового поля
 * @param showTextLengthCounter Отображение счетчика введенных сообщений
 */
@Composable
@Deprecated("Переходить на SinglenessOutlinedTextField с FormState")
fun SinglenessOutlinedTextField(
    text: MutableState<String>,
    maxLength: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String = "Простое текстовое поле",
    leadingIcon: Icon? = null,
    leadingIconConfig: IconConfig = IconConfig.Small,
    trailingIcon: Icon? = null,
    trailingIconConfig: IconConfig = IconConfig.Small,
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
        modifier =
            Modifier.fillMaxWidth(width).onFocusChanged { isFocused = it.isFocused }.then(modifier),
        enabled = enabled,
        readOnly = readOnly,
        label = { Text(text = label) },
        leadingIcon =
            getIcon(
                icon = leadingIcon,
                iconConfig = leadingIconConfig,
                colors = colors,
                isError = error.value,
                isFocused = isFocused,
            ),
        trailingIcon =
            getIcon(
                icon = trailingIcon,
                iconConfig = trailingIconConfig,
                colors = colors,
                isError = error.value,
                isFocused = isFocused,
            ),
        isError = error.value,
        visualTransformation =
            if (isHiddenText) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        shape = shape,
        colors = colors,
    )
    ErrorMessageAndSymbolsCounter(
        text.value,
        width,
        error,
        maxLength,
        isFocused,
        colors,
        showTextLengthCounter,
    )
}

/**
 * [SinglenessOutlinedTextField] - Однострочное текстовое поле
 *
 * @param state Состояние текстового поля
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
 * @sample com.ravenzip.workshop.samples.view.SinglenessOutlinedTextFieldWithFormStateSample
 */
@ExperimentalMaterial3Api
@Composable
fun SinglenessOutlinedTextField(
    state: TextFieldState<String>,
    maxLength: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    modifier: Modifier = Modifier,
    label: String = "Простое текстовое поле",
    leadingIcon: Icon? = null,
    leadingIconConfig: IconConfig = IconConfig.Small,
    trailingIcon: Icon? = null,
    trailingIconConfig: IconConfig = IconConfig.Small,
    isHiddenText: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = false,
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        OutlinedTextField(
            value = state.value,
            onValueChange = { if (checkLength(it.length, maxLength)) state.setValue(it) },
            modifier =
                Modifier.fillMaxWidth()
                    .onFocusChanged { focusState -> state.changeFocusState(focusState.isFocused) }
                    .then(modifier),
            enabled = state.isEnabled,
            readOnly = state.isReadonly,
            label = { Text(text = label) },
            leadingIcon =
                getIcon(
                    icon = leadingIcon,
                    iconConfig = leadingIconConfig,
                    colors = colors,
                    isError = state.isInvalid,
                    isFocused = state.isFocused,
                ),
            trailingIcon =
                getIcon(
                    icon = trailingIcon,
                    iconConfig = trailingIconConfig,
                    colors = colors,
                    isError = state.isInvalid,
                    isFocused = state.isFocused,
                ),
            isError = state.isInvalid,
            visualTransformation =
                if (isHiddenText) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            shape = shape,
            colors = colors,
        )

        ErrorMessageWithSymbolsCounter(
            isInvalid = state.isInvalid,
            errorMessage = state.errorMessage,
            isFocused = state.isFocused,
            showTextLengthCounter = showTextLengthCounter,
            maxLength = maxLength,
            currentLength = state.value.length,
            colors = colors,
        )
    }
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
@Deprecated("Переходить на MultilineTextField с FormState")
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
    showTextLengthCounter: Boolean = false,
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
        colors = colors,
    )
    ErrorMessageAndSymbolsCounter(
        text.value,
        width,
        error,
        maxLength,
        isFocused,
        colors,
        showTextLengthCounter,
    )
}

/**
 * [MultilineTextField] - Многострочное текстовое поле
 *
 * @param state Состояние текстового поля
 * @param maxLength Максимальная длина символов
 * @param width Ширина текстового поля
 * @param modifier Кастомные модификаторы
 * @param label Название текстового поля
 * @param keyboardOptions Опции для клавиатуры
 * @param maxLines Минимальное число строк
 * @param minLines Максимальное число строк
 * @param shape Радиус скругления
 * @param colors Цвета текстового поля
 * @param showTextLengthCounter Отображение счетчика введенных сообщений
 */
@ExperimentalMaterial3Api
@Composable
fun MultilineTextField(
    state: TextFieldState<String>,
    maxLength: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    modifier: Modifier = Modifier,
    label: String = "Многострочное текстовое поле",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = false,
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        OutlinedTextField(
            value = state.value,
            onValueChange = { value -> state.setValue(value) },
            modifier =
                Modifier.fillMaxWidth()
                    .onFocusChanged { focusState -> state.changeFocusState(focusState.isFocused) }
                    .then(modifier),
            enabled = state.isEnabled,
            readOnly = state.isReadonly,
            label = { Text(text = label) },
            isError = state.isInvalid,
            keyboardOptions = keyboardOptions,
            maxLines = maxLines,
            minLines = minLines,
            shape = shape,
            colors = colors,
        )

        ErrorMessageWithSymbolsCounter(
            isInvalid = state.isInvalid,
            errorMessage = state.errorMessage,
            isFocused = state.isFocused,
            showTextLengthCounter = showTextLengthCounter,
            maxLength = maxLength,
            currentLength = state.value.length,
            colors = colors,
        )
    }
}

/**
 * [DropDownTextField] - Текстовое поле с выпадающим списком
 *
 * @param state Выбранное значение
 * @param width Ширина текстового поля
 * @param modifier - Кастомные модификаторы
 * @param label Название текстового поля
 * @param dropDownIcon Иконка выпадающего меню
 * @param dropDownIconConfig параметры иконки выпадающего списка
 * @param shape Радиус скругления
 * @param colors Цвета текстового поля
 * @sample com.ravenzip.workshop.samples.view.DropDownTextFieldWithFormStateSample
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownTextField(
    state: DropDownTextFieldState<T>,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    modifier: Modifier = Modifier,
    label: String = "Поле с выпадающим списком",
    dropDownIcon: Icon = Icon.ImageVectorIcon(Icons.Outlined.ArrowDropDown),
    dropDownIconConfig: IconConfig = IconConfig.Small,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        ExposedDropdownMenuBox(
            expanded = state.expanded,
            onExpandedChange = { state.setExpanded(!state.expanded) },
        ) {
            OutlinedTextField(
                value = state.text,
                onValueChange = { state.setText(it) },
                modifier =
                    Modifier.fillMaxWidth()
                        .onFocusChanged { state.changeFocusState(it.isFocused) }
                        .menuAnchor(
                            if (state.isReadonly) MenuAnchorType.PrimaryNotEditable
                            else MenuAnchorType.PrimaryEditable
                        )
                        .then(modifier),
                enabled = state.isEnabled,
                readOnly = state.isReadonly,
                label = { Text(text = label) },
                trailingIcon = {
                    Icon(
                        icon = dropDownIcon,
                        iconConfig = dropDownIconConfig,
                        defaultColor = colors.cursorColor,
                    )
                },
                isError = state.isInvalid,
                singleLine = true,
                shape = shape,
                colors = colors,
            )

            ExposedDropdownMenu(
                expanded = state.expanded,
                onDismissRequest = { state.setExpanded(false) },
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                if (state.results.isNotEmpty()) {
                    state.results.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = state.view(item)) },
                            onClick = { state.setValue(item) },
                        )
                    }
                } else {
                    DropdownMenuItem(
                        text = { Text(text = "Нет результатов") },
                        onClick = {},
                        enabled = false,
                    )
                }
            }
        }

        ErrorMessage(
            isInvalid = state.isInvalid,
            errorMessage = state.errorMessage,
            colors = colors,
        )
    }
}

/** [SearchBarTextField] - Поисковое текстовое поле */
@Composable
internal fun SearchBarTextField(
    text: MutableState<String>,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    placeholder: String? = null,
    onSearch: (KeyboardActionScope.() -> Unit)?,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors,
) {
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        modifier = Modifier.fillMaxWidth(width),
        placeholder = getText(placeholder),
        leadingIcon = {
            Icon(
                icon = Icon.ImageVectorIcon(ImageVector.vectorResource(R.drawable.i_search)),
                iconConfig = IconConfig.Big,
            )
        },
        trailingIcon = getClearButton(text, colors.cursorColor),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = onSearch),
        singleLine = true,
        shape = shape,
        colors = colors,
    )
}

/** Элемент вывода сообщения об ошибке при вводе и счетчик введенных символов */
@Composable
@Deprecated("Не использовать")
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
        if (error.value && error.text.isNotEmpty()) {
            Text(
                text = error.text,
                modifier = Modifier.fillMaxWidth().weight(1f).padding(start = 10.dp),
                color = colors.errorLabelColor,
                fontSize = 12.sp,
            )
        }
        if (showTextLengthCounter) {
            Text(
                text = if (maxLength > 0) "${text.length} / $maxLength" else "${text.length}",
                modifier = Modifier.fillMaxWidth().weight(1f).padding(end = 5.dp),
                color = getColor(colors, error.value, isFocused),
                fontSize = 12.sp,
                textAlign = TextAlign.End,
            )
        }
    }
}

/** Сообщение с описанием ошибки + счетчик введенных символов */
@Composable
private fun ErrorMessageWithSymbolsCounter(
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
            color = getColor(colors, isInvalid, isFocused),
        )
    }
}

/** Сообщение с описанием ошибки */
@Composable
private fun ErrorMessage(
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
fun SymbolsCounter(
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

/**
 * Проверка на то, что максимальная длина не задана или если задана, то проверка на соответствие
 * длины введенных символов заданному ограничению
 */
private fun checkLength(textLength: Int, maxLength: Int): Boolean {
    return maxLength == 0 || (maxLength > 0 && textLength <= maxLength)
}

private fun getText(text: String?): @Composable (() -> Unit)? {
    return if (text != null) {
        { Text(text = text) }
    } else null
}

private fun getIcon(
    icon: Icon?,
    iconConfig: IconConfig,
    colors: TextFieldColors,
    isError: Boolean,
    isFocused: Boolean,
): @Composable (() -> Unit)? {
    return if (icon != null) {
        {
            Icon(
                icon = icon,
                iconConfig = iconConfig,
                defaultColor = getColor(colors = colors, isError = isError, isFocused = isFocused),
            )
        }
    } else null
}

private fun getIcon(
    icon: Icon?,
    iconConfig: IconConfig,
    colors: TextFieldColors,
): @Composable (() -> Unit)? {
    return if (icon != null) {
        { Icon(icon = icon, iconConfig = iconConfig, defaultColor = colors.cursorColor) }
    } else null
}

private fun getClearButton(text: MutableState<String>, color: Color): @Composable (() -> Unit)? {
    return if (text.value.isNotEmpty()) {
        {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.i_close),
                contentDescription = "Close",
                modifier =
                    Modifier.clip(RoundedCornerShape(10.dp))
                        .clickable { text.value = "" }
                        .padding(8.dp)
                        .size(22.dp),
                tint = color,
            )
        }
    } else null
}

private fun getColor(colors: TextFieldColors, isError: Boolean, isFocused: Boolean): Color {
    return if (isError) colors.errorLabelColor
    else if (isFocused) colors.focusedLabelColor else colors.unfocusedLabelColor
}
