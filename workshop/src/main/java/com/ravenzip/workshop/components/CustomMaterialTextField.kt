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
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData
import com.ravenzip.workshop.forms.component.DropDownTextFieldComponent
import com.ravenzip.workshop.forms.component.TextFieldComponent

/**
 * [SimpleTextField] - Простое текстовое поле
 *
 * @param component Компонент (контрол + состояние)
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
    component: TextFieldComponent<String>,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    modifier: Modifier = Modifier,
    textSize: Int = 16,
    placeholder: String = "",
    interactionSource: InteractionSource = MutableInteractionSource(),
    colors: TextFieldColors = TextFieldDefaults.colors(),
    showLine: Boolean = true,
) {
    BasicTextField(
        value = component.control.value,
        onValueChange = { value -> component.control.setValue(value) },
        textStyle = TextStyle(color = colors.unfocusedTextColor, fontSize = textSize.sp),
        modifier = Modifier.fillMaxWidth(width).then(modifier),
    ) {
        TextFieldDefaults.DecorationBox(
            value = component.control.value,
            innerTextField = it,
            enabled = component.control.isEnabled,
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
 * @param component Компонент (контрол + состояние)
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
    component: TextFieldComponent<String>,
    maxLength: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    modifier: Modifier = Modifier,
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
    Column(modifier = Modifier.fillMaxWidth(width)) {
        TextField(
            value = component.control.value,
            onValueChange = { value ->
                if (checkLength(value.length, maxLength)) component.control.setValue(value)
            },
            modifier =
                Modifier.fillMaxWidth()
                    .onFocusChanged { focusState ->
                        component.state.changeFocusState(focusState.isFocused)
                    }
                    .then(modifier),
            readOnly = component.state.isReadonly,
            placeholder = getText(placeholder),
            leadingIcon =
                getIcon(icon = leadingIcon, iconConfig = leadingIconConfig, colors = colors),
            trailingIcon =
                getIcon(icon = trailingIcon, iconConfig = trailingIconConfig, colors = colors),
            isError = component.control.isInvalid,
            visualTransformation =
                if (isHiddenText) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            shape = shape,
            colors = colors,
        )

        ErrorMessageWithSymbolsCounter(
            isInvalid = component.control.isInvalid,
            errorMessage = component.control.errorMessage,
            isFocused = component.state.isFocused,
            showTextLengthCounter = showTextLengthCounter,
            maxLength = maxLength,
            currentLength = component.control.value.length,
            colors = colors,
        )
    }
}

/**
 * [SinglenessOutlinedTextField] - Однострочное текстовое поле
 *
 * @param component Компонент (контрол + состояние)
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
 * @sample com.ravenzip.workshop.samples.components.SinglenessOutlinedTextFieldWithFormStateSample
 */
@Composable
fun SinglenessOutlinedTextField(
    component: TextFieldComponent<String>,
    maxLength: Int = 0,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    modifier: Modifier = Modifier,
    label: String = "Простое текстовое поле",
    leadingIcon: IconData? = null,
    leadingIconConfig: IconConfig = IconConfig.Small,
    trailingIcon: IconData? = null,
    trailingIconConfig: IconConfig = IconConfig.Small,
    isHiddenText: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    showTextLengthCounter: Boolean = false,
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        OutlinedTextField(
            value = component.control.value,
            onValueChange = {
                if (checkLength(it.length, maxLength)) component.control.setValue(it)
            },
            modifier =
                Modifier.fillMaxWidth()
                    .onFocusChanged { focusState ->
                        component.state.changeFocusState(focusState.isFocused)
                    }
                    .then(modifier),
            enabled = component.control.isEnabled,
            readOnly = component.state.isReadonly,
            label = { Text(text = label) },
            leadingIcon =
                getIcon(
                    icon = leadingIcon,
                    iconConfig = leadingIconConfig,
                    colors = colors,
                    isError = component.control.isInvalid,
                    isFocused = component.state.isFocused,
                ),
            trailingIcon =
                getIcon(
                    icon = trailingIcon,
                    iconConfig = trailingIconConfig,
                    colors = colors,
                    isError = component.control.isInvalid,
                    isFocused = component.state.isFocused,
                ),
            isError = component.control.isInvalid,
            visualTransformation =
                if (isHiddenText) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            shape = shape,
            colors = colors,
        )

        ErrorMessageWithSymbolsCounter(
            isInvalid = component.control.isInvalid,
            errorMessage = component.control.errorMessage,
            isFocused = component.state.isFocused,
            showTextLengthCounter = showTextLengthCounter,
            maxLength = maxLength,
            currentLength = component.control.value.length,
            colors = colors,
        )
    }
}

/**
 * [MultilineTextField] - Многострочное текстовое поле
 *
 * @param component Компонент (контрол + состояние)
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
@Composable
fun MultilineTextField(
    component: TextFieldComponent<String>,
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
            value = component.control.value,
            onValueChange = { value -> component.control.setValue(value) },
            modifier =
                Modifier.fillMaxWidth()
                    .onFocusChanged { focusState ->
                        component.state.changeFocusState(focusState.isFocused)
                    }
                    .then(modifier),
            enabled = component.control.isEnabled,
            readOnly = component.state.isReadonly,
            label = { Text(text = label) },
            isError = component.control.isInvalid,
            keyboardOptions = keyboardOptions,
            maxLines = maxLines,
            minLines = minLines,
            shape = shape,
            colors = colors,
        )

        ErrorMessageWithSymbolsCounter(
            isInvalid = component.control.isInvalid,
            errorMessage = component.control.errorMessage,
            isFocused = component.state.isFocused,
            showTextLengthCounter = showTextLengthCounter,
            maxLength = maxLength,
            currentLength = component.control.value.length,
            colors = colors,
        )
    }
}

/**
 * [DropDownTextField] - Текстовое поле с выпадающим списком
 *
 * @param component Компонент (контрол + состояние)
 * @param width Ширина текстового поля
 * @param modifier - Кастомные модификаторы
 * @param label Название текстового поля
 * @param dropDownIcon Иконка выпадающего меню
 * @param dropDownIconConfig параметры иконки выпадающего списка
 * @param shape Радиус скругления
 * @param colors Цвета текстового поля
 * @sample com.ravenzip.workshop.samples.components.DropDownTextFieldWithFormStateSample
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownTextField(
    component: DropDownTextFieldComponent<T>,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    modifier: Modifier = Modifier,
    label: String = "Поле с выпадающим списком",
    dropDownIcon: IconData = IconData.ImageVectorIcon(Icons.Outlined.ArrowDropDown),
    dropDownIconConfig: IconConfig = IconConfig.Small,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        ExposedDropdownMenuBox(
            expanded = component.state.expanded,
            onExpandedChange = { component.state.setExpanded(!component.state.expanded) },
        ) {
            OutlinedTextField(
                value = component.state.text,
                onValueChange = { value -> component.setText(value) },
                modifier =
                    Modifier.fillMaxWidth()
                        .onFocusChanged { component.state.changeFocusState(it.isFocused) }
                        .menuAnchor(
                            if (component.state.isReadonly) MenuAnchorType.PrimaryNotEditable
                            else MenuAnchorType.PrimaryEditable
                        )
                        .then(modifier),
                enabled = component.control.isEnabled,
                readOnly = component.state.isReadonly,
                label = { Text(text = label) },
                trailingIcon = {
                    Icon(
                        icon = dropDownIcon,
                        iconConfig = dropDownIconConfig,
                        defaultColor = colors.cursorColor,
                    )
                },
                isError = component.control.isInvalid,
                singleLine = true,
                shape = shape,
                colors = colors,
            )

            ExposedDropdownMenu(
                expanded = component.state.expanded,
                onDismissRequest = { component.state.setExpanded(false) },
                containerColor = MaterialTheme.colorScheme.surface,
            ) {
                if (component.state.results.isNotEmpty()) {
                    component.state.results.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = component.state.view(item)) },
                            onClick = { component.selectItem(item) },
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
            isInvalid = component.control.isInvalid,
            errorMessage = component.control.errorMessage,
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
                icon = IconData.ImageVectorIcon(ImageVector.vectorResource(R.drawable.i_search)),
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
private fun SymbolsCounter(
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
    icon: IconData?,
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
    icon: IconData?,
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
