package com.ravenzip.workshop.components.swtich

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.forms.control.FormControlMulti
import com.ravenzip.workshop.model.TextConfig

/**
 * [SwitchGroup] - Группа элементов Switch
 *
 * @param control контрол элемента
 * @param source источник данных
 * @param labelProvider содержимое заголовка
 * @param labelConfig параметры заголовка
 * @param width ширина
 * @param descriptionProvider содержимое описания
 * @param descriptionConfig параметры описания
 * @param contentPadding отступ между чекбоксами
 * @param colors цвета чекбоксов
 */
@Composable
fun <T> SwitchGroup(
    control: FormControlMulti<T>,
    source: List<T>,
    labelProvider: ((T) -> String)? = null,
    labelConfig: TextConfig? = null,
    descriptionProvider: (T) -> String,
    descriptionConfig: TextConfig = TextConfig.S18,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    contentPadding: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(10.dp),
    colors: SwitchColors = SwitchDefaults.colors(),
) {
    Column(modifier = Modifier.fillMaxWidth(width), verticalArrangement = contentPadding) {
        source.forEach { item ->
            Switch(
                isSelected = control.isSelected(item),
                isEnabled = control.isEnabled,
                width = 1f,
                label = labelProvider?.invoke(item),
                labelConfig = labelConfig,
                description = descriptionProvider(item),
                descriptionConfig = descriptionConfig,
                colors = colors,
            ) {
                control.setValue(item)
            }
        }
    }
}
