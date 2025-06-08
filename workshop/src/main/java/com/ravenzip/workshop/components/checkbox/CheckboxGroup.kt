package com.ravenzip.workshop.components.checkbox

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.data.Equatable
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.forms.control.FormControlMulti

/**
 * [CheckboxGroup] - Чекбоксы
 *
 * @param control контрол элемента
 * @param source источник данных
 * @param view как будут отображены элементы
 * @param width ширина
 * @param textConfig параметры текста
 * @param contentPadding отступ между чекбоксами
 * @param colors цвета чекбоксов
 */
@Composable
fun <T : Equatable> CheckboxGroup(
    control: FormControlMulti<T>,
    source: List<T>,
    view: (T) -> String,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    textConfig: TextConfig = TextConfig.S18,
    contentPadding: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(10.dp),
    colors: CheckboxColors = CheckboxDefaults.colors(),
) {
    Column(modifier = Modifier.fillMaxWidth(width), verticalArrangement = contentPadding) {
        source.forEach { item ->
            Checkbox(
                isSelectedSelector = { item in control.value },
                text = view(item),
                textConfig = textConfig,
                enabled = control.isEnabled,
                colors = colors,
            ) {
                control.setValue(item)
            }
        }
    }
}
