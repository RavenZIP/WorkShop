package com.ravenzip.workshop.components

import android.util.Log
import androidx.annotation.FloatRange
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.data.ChipViewOptions
import com.ravenzip.workshop.data.Equatable
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.control.FormControlMulti
import com.ravenzip.workshop.forms.control.FormControlTree
import com.ravenzip.workshop.forms.control.extension.changeParentState
import com.ravenzip.workshop.forms.control.extension.selectAll
import com.ravenzip.workshop.forms.control.extension.unselectAll
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import org.jetbrains.annotations.ApiStatus.Experimental

/**
 * [Switch] - Переключатель
 *
 * @param control контрол
 * @param width ширина
 * @param title заголовок
 * @param titleConfig параметры заголовка
 * @param text описание
 * @param textConfig параметры описания
 * @param colors цвета свича
 */
@Experimental
@Composable
fun Switch(
    control: FormControl<Boolean>,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    title: String,
    titleConfig: TextConfig,
    text: String,
    textConfig: TextConfig,
    colors: SwitchColors = SwitchDefaults.colors(),
) {
    val titleColor = remember { titleConfig.color ?: Color.Unspecified }
    val textColor = remember { textConfig.color ?: Color.Unspecified }

    Row(
        modifier =
            Modifier.fillMaxWidth(width)
                .clip(RoundedCornerShape(10.dp))
                .clickable { control.setValue(!control.value) }
                .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = title,
                color = titleColor,
                fontSize = titleConfig.size,
                fontWeight = titleConfig.weight,
                letterSpacing = titleConfig.letterSpacing,
            )

            Text(
                text = text,
                color = textColor,
                fontSize = textConfig.size,
                fontWeight = textConfig.weight,
                letterSpacing = textConfig.letterSpacing,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Switch(
            checked = control.value,
            onCheckedChange = { control.setValue(!control.value) },
            enabled = control.isEnabled,
            colors = colors,
        )
    }
}

/**
 * [RadioGroup] - Радиокнопки
 *
 * @param state состояние радиогруппы
 * @param source источник данных
 * @param view отображаемый текст для радиокнопок
 * @param comparableKey ключ для сравнения
 * @param width ширина
 * @param textSize размер текста
 * @param contentPadding вертикальный отступ между радиокнопками
 * @param colors цвета радиокнопок
 */
@Experimental
@Composable
fun <T> RadioGroup(
    state: FormControl<T>,
    source: List<T>,
    view: (T) -> String,
    comparableKey: (T) -> Any,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    textSize: Int = 18,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    Column(modifier = Modifier.fillMaxWidth(width), verticalArrangement = contentPadding) {
        source.forEach { item ->
            Row(
                modifier =
                    Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { state.setValue(item) }
                        .padding(top = 5.dp, bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = comparableKey(state.value) == comparableKey(item),
                    onClick = { state.setValue(item) },
                    enabled = state.isEnabled,
                    colors = colors,
                )

                Text(text = view(item), fontSize = textSize.sp)
            }
        }
    }
}

/**
 * [ChipRadioGroup] - Радиогруппа с чипами
 *
 * @param state состояние радиогруппы
 * @param source источник данных
 * @param viewOptions параметры отображения чипов
 * @param comparableKey ключ для сравнения
 * @param width ширина
 * @param containerPadding отступ для контейнера
 * @param contentPadding отступ между радиокнопками
 */
@Experimental
@Composable
fun <T> ChipRadioGroup(
    state: FormControl<T>,
    source: List<T>,
    viewOptions: Map<Any, ChipViewOptions>,
    comparableKey: (T) -> Any,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 1f,
    containerPadding: PaddingValues = PaddingValues(horizontal = 10.dp),
    contentPadding: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(10.dp),
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(width),
        horizontalArrangement = contentPadding,
        contentPadding = containerPadding,
    ) {
        items(source, key = { item -> comparableKey(item) }, contentType = { it }) { item ->
            val itemKey = comparableKey(item)
            val chipOptions = viewOptions[itemKey]

            if (chipOptions != null) {
                SelectableChip(
                    isSelected = comparableKey(state.value) == itemKey,
                    text = chipOptions.text,
                    textConfig = chipOptions.textConfig,
                    icon = chipOptions.icon,
                    iconConfig = chipOptions.iconConfig,
                ) {
                    state.setValue(item)
                }
            } else {
                Log.e("ChipRadioGroup", "Не найдено настроек для чипа с ключом: $itemKey")
            }
        }
    }
}

/**
 * [Checkbox] - чекбокс
 *
 * @param isSelectedSelector селектор выбора чекбокса
 * @param text текст чекбокса
 * @param textConfig параметры текста
 * @param enabled вкл\выкл чекбоксов
 * @param colors цвета чекбоксов
 * @param onClick действие при нажатии
 */
@Experimental
@Composable
fun Checkbox(
    isSelectedSelector: () -> Boolean,
    text: String,
    textConfig: TextConfig,
    enabled: Boolean,
    colors: CheckboxColors,
    onClick: () -> Unit,
) {
    val isSelected by remember { derivedStateOf { isSelectedSelector() } }

    Row(
        modifier =
            Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable { onClick() }
                .padding(top = 5.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onClick() },
            enabled = enabled,
            colors = colors,
        )
        Text(
            text = text,
            fontSize = textConfig.size,
            fontWeight = textConfig.weight,
            letterSpacing = textConfig.letterSpacing,
        )
    }
}

/**
 * [CheckBoxGroup] - Чекбоксы
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
fun <T : Equatable> CheckBoxGroup(
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

/**
 * [CheckboxTree] - Дерево чекбоксов
 *
 * @param control контрол элемента
 * @param source источник данных
 * @param view как будут отображены элементы
 * @param width ширина
 * @param parentText текст для главного чебокса
 * @param parentTextConfig параметры текста для главного чекбокса
 * @param parentPadding отступ между главным чекбоксом и группой дочерних
 * @param childPadding отступ между дочерними чекбоксами
 * @param parentColors цвета для главного чекбокса
 * @param childColors цвета для дочерних чекбоксов
 */
@Experimental
@Composable
fun <T : Equatable> CheckboxTree(
    control: FormControlTree<T>,
    source: List<T>,
    view: (T) -> String,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    parentText: String,
    parentTextConfig: TextConfig = TextConfig.S18,
    childTextConfig: TextConfig = TextConfig.S18,
    parentPadding: Arrangement.Vertical = Arrangement.spacedBy(5.dp),
    childPadding: Arrangement.Vertical = Arrangement.spacedBy(5.dp),
    parentColors: CheckboxColors = CheckboxDefaults.colors(),
    childColors: CheckboxColors = CheckboxDefaults.colors(),
) {
    LaunchedEffect(control) {
        control.valueChanges
            .distinctUntilChanged { previousChanges, currentChanges ->
                previousChanges.parent == currentChanges.parent
            }
            .onEach { value ->
                when (value.parent) {
                    ToggleableState.On -> control.selectAll(source)
                    ToggleableState.Off -> control.unselectAll(source)
                    else -> {
                        // ничего не делаем, потому что в случае indereterminate невозможно
                        // определить какие значения добавить, а какие нет
                    }
                }
            }
            .collect {}

        control.valueChanges
            .distinctUntilChanged { previousChanges, currentChanges ->
                previousChanges.children == currentChanges.children
            }
            .onEach { value ->
                val calculatedParentValue =
                    when (value.children.count()) {
                        0 -> ToggleableState.Off
                        source.count() -> ToggleableState.On
                        else -> ToggleableState.Indeterminate
                    }

                control.setValue(calculatedParentValue)
            }
            .collect {}
    }

    Column(modifier = Modifier.fillMaxWidth(width), verticalArrangement = parentPadding) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { control.changeParentState() }
                    .padding(top = 5.dp, bottom = 5.dp),
        ) {
            TriStateCheckbox(
                state = control.value.parent,
                onClick = { control.changeParentState() },
                enabled = control.isEnabled,
                colors = parentColors,
            )
            Text(
                text = parentText,
                fontSize = parentTextConfig.size,
                fontWeight = parentTextConfig.weight,
                letterSpacing = parentTextConfig.letterSpacing,
            )
        }

        Column(modifier = Modifier.padding(start = 15.dp), verticalArrangement = childPadding) {
            source.forEach { item ->
                Checkbox(
                    isSelectedSelector = { item in control.value.children },
                    text = view(item),
                    textConfig = childTextConfig,
                    enabled = control.isEnabled,
                    colors = childColors,
                ) {
                    control.setValue(item)
                }
            }
        }
    }
}
