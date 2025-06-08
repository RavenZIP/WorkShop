package com.ravenzip.workshop.components.textField

import androidx.annotation.FloatRange
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.R
import com.ravenzip.workshop.components.icon.Icon
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.state.TextFieldState

/**
 * [SearchTextField] - Поисковое текстовое поле
 *
 * @param control Контрол элемента
 * @param state Состояния текстового поля
 * @param width Ширина текстового поля
 * @param placeholder Подсказка текстового поля
 * @param onSearch Действие при поиске
 * @param shape Радиус скругления
 * @param colors Цвета текстового поля
 */
@Composable
fun SearchTextField(
    control: FormControl<String>,
    state: TextFieldState = TextFieldState(),
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    placeholder: String? = null,
    onSearch: (KeyboardActionScope.() -> Unit)?,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors,
) {
    TextFieldWrapper(control, state) {
        TextField(
            value = control.value,
            onValueChange = { value -> control.setValue(value) },
            readOnly = state.isReadonly,
            modifier = Modifier.fillMaxWidth(width),
            placeholder = getText(placeholder),
            leadingIcon = {
                Icon(
                    icon =
                        IconData.ImageVectorIcon(ImageVector.vectorResource(R.drawable.i_search)),
                    iconConfig = IconConfig.Big,
                )
            },
            trailingIcon =
                getClearButton(
                    text = control.value,
                    color = colors.cursorColor,
                    onClear = { control.setValue("") },
                ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = onSearch),
            singleLine = true,
            shape = shape,
            colors = colors,
        )
    }
}

private fun getClearButton(
    text: String,
    color: Color,
    onClear: () -> Unit,
): @Composable (() -> Unit)? {
    return if (text.isNotEmpty()) {
        {
            androidx.compose.material3.Icon(
                imageVector = ImageVector.vectorResource(R.drawable.i_close),
                contentDescription = "Close",
                modifier =
                    Modifier.clip(RoundedCornerShape(10.dp))
                        .clickable { onClear() }
                        .padding(8.dp)
                        .size(22.dp),
                tint = color,
            )
        }
    } else null
}
