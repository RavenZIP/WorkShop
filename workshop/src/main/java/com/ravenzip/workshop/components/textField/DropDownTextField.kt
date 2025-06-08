package com.ravenzip.workshop.components.textField

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.components.icon.Icon
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.state.DropDownTextFieldState
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge

/**
 * [DropDownTextField] - Текстовое поле с выпадающим списком
 *
 * @param control Контрол элемента
 * @param state Состояния текстового поля
 * @param width Ширина текстового поля
 * @param modifier - Кастомные модификаторы
 * @param label Название текстового поля
 * @param dropDownIcon Иконка выпадающего меню
 * @param dropDownIconConfig параметры иконки выпадающего списка
 * @param shape Радиус скругления
 * @param colors Цвета текстового поля
 * @sample com.ravenzip.workshop.components.textField.sample.DropDownTextFieldSample
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropDownTextField(
    modifier: Modifier = Modifier,
    control: FormControl<T>,
    state: DropDownTextFieldState<T> = DropDownTextFieldState(),
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    label: String = "Поле с выпадающим списком",
    dropDownIcon: IconData = IconData.ImageVectorIcon(Icons.Outlined.ArrowDropDown),
    dropDownIconConfig: IconConfig = IconConfig.Small,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
) {
    LaunchedEffect(control, state) {
        merge(
                control.valueChanges
                    .map { value -> state.view(value) }
                    .filter { value -> value.isNotEmpty() },
                state.expandedChanges
                    .filter { expanded -> !expanded && control.isInvalid }
                    .map { state.view(control.resetValue) },
            )
            .collect { value -> state.setText(value) }
    }

    Column(modifier = Modifier.fillMaxWidth(width)) {
        ExposedDropdownMenuBox(
            expanded = state.expanded,
            onExpandedChange = { state.setExpanded(!state.expanded) },
        ) {
            OutlinedTextField(
                value = state.text,
                onValueChange = { value ->
                    control.setValue(control.resetValue)
                    state.setText(value)
                    state.setExpanded(true)
                },
                modifier =
                    Modifier.fillMaxWidth()
                        .onFocusChanged { state.changeFocusState(it.isFocused) }
                        .menuAnchor(
                            if (state.isReadonly) MenuAnchorType.PrimaryNotEditable
                            else MenuAnchorType.PrimaryEditable
                        )
                        .then(modifier),
                enabled = control.isEnabled,
                readOnly = state.isReadonly,
                label = { Text(text = label) },
                trailingIcon = {
                    Icon(
                        icon = dropDownIcon,
                        iconConfig = dropDownIconConfig,
                        defaultColor = colors.cursorColor,
                    )
                },
                isError = control.isInvalid,
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
                            onClick = {
                                control.setValue(item)
                                state.setExpanded(false)
                            },
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
            isInvalid = control.isInvalid,
            errorMessage = control.errorMessage,
            colors = colors,
        )
    }
}
