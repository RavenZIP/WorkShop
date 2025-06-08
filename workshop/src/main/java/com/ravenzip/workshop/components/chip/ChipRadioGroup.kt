package com.ravenzip.workshop.components.chip

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.data.ChipViewOptions
import com.ravenzip.workshop.forms.control.FormControl

/**
 * [ChipRadioGroup] - Радиогруппа с чипами
 *
 * @param control контрол элемента
 * @param source источник данных
 * @param viewOptionsProvider параметры отображения чипов
 * @param keySelector ключ для сравнения
 * @param width ширина
 * @param containerPadding отступ для контейнера
 * @param contentPadding отступ между радиокнопками
 */
@Composable
fun <T, K : Any> ChipRadioGroup(
    control: FormControl<T>,
    source: List<T>,
    viewOptionsProvider: (T) -> ChipViewOptions,
    keySelector: (T) -> K,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 1f,
    containerPadding: PaddingValues = PaddingValues(horizontal = 10.dp),
    contentPadding: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(10.dp),
) {
    val selectedKey = remember(control.value) { keySelector(control.value) }

    LazyRow(
        modifier = Modifier.fillMaxWidth(width),
        horizontalArrangement = contentPadding,
        contentPadding = containerPadding,
    ) {
        items(source, key = { item -> keySelector(item) }, contentType = { it }) { item ->
            val chipOptions by remember(item) { derivedStateOf { viewOptionsProvider(item) } }

            SelectableChip(
                isSelected = selectedKey == keySelector(item),
                text = chipOptions.text,
                textConfig = chipOptions.textConfig,
                icon = chipOptions.icon,
                iconConfig = chipOptions.iconConfig,
            ) {
                control.setValue(item)
            }
        }
    }
}
