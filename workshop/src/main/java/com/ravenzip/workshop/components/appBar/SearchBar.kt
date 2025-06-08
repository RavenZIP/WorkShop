package com.ravenzip.workshop.components.appBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.components.textField.SearchTextField
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.state.TextFieldState

/**
 * [SearchBar] - Верхняя панель с поиском
 *
 * @param control контрол элемента
 * @param state состояния текстового поля
 * @param placeholder временный текст
 * @param onSearch действие при нажатии на кнопку поиска
 * @param backgroundColor фоновый цвет верхней панели,
 * @param textFieldColors цвета текстового поля
 */
@Composable
fun SearchBar(
    control: FormControl<String>,
    state: TextFieldState = TextFieldState(),
    placeholder: String?,
    onSearch: (KeyboardActionScope.() -> Unit)?,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    textFieldColors: TextFieldColors =
        TextFieldDefaults.colors(
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
) {
    Box(
        modifier = Modifier.fillMaxWidth().background(backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Spacer(modifier = Modifier.padding(top = 10.dp))

            SearchTextField(
                control = control,
                state = state,
                placeholder = placeholder,
                onSearch = onSearch,
                colors = textFieldColors,
            )

            Spacer(modifier = Modifier.padding(top = 10.dp))
        }
    }
}
