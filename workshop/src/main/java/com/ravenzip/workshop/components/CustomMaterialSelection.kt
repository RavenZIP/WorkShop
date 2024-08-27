package com.ravenzip.workshop.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.selection.RootSelectionConfig
import com.ravenzip.workshop.data.selection.SelectableChipConfig
import com.ravenzip.workshop.data.selection.SelectableItemConfig

/**
 * [Switch] - Переключатель
 *
 * @param width ширина
 * @param isChecked состояние свича
 * @param title заголовок
 * @param titleConfig параметры заголовка
 * @param text описание
 * @param textConfig параметры описания
 * @param enabled вкл\выкл свича
 * @param colors цвета свича
 */
@Composable
fun Switch(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    isChecked: MutableState<Boolean>,
    title: String,
    titleConfig: TextConfig,
    text: String,
    textConfig: TextConfig,
    enabled: Boolean = true,
    colors: SwitchColors = SwitchDefaults.colors(),
) {
    Row(
        modifier =
            Modifier.fillMaxWidth(width)
                .clip(RoundedCornerShape(10.dp))
                .clickable { isChecked.value = !isChecked.value }
                .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(text = title, fontSize = titleConfig.size.sp)
            Text(text = text, fontSize = textConfig.size.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it },
            enabled = enabled,
            colors = colors,
        )
    }
}

/**
 * [RadioGroup] - Радиокнопки
 *
 * @param width ширина
 * @param list список радиокнопок
 * @param textSize размер текста
 * @param enabled вкл\выкл радиокнопок
 * @param colors цвета радиокнопок
 */
@Composable
fun RadioGroup(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    list: SnapshotStateList<SelectableItemConfig>,
    textSize: Int = 18,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        list.forEach { item ->
            Row(
                modifier =
                    Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            list.replaceAll { it.copy(isSelected = it.text == item.text) }
                        }
                        .padding(top = 5.dp, bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = item.isSelected,
                    onClick = { list.replaceAll { it.copy(isSelected = it.text == item.text) } },
                    enabled = enabled,
                    colors = colors,
                )
                Text(text = item.text, fontSize = textSize.sp)
            }
        }
    }
}

/**
 * [ChipRadioGroup] - Радиогруппа с чипами
 *
 * @param list список радиокнопок
 * @param contentPadding отступ между радиокнопками
 */
@Composable
fun ChipRadioGroup(
    list: SnapshotStateList<SelectableChipConfig>,
    contentPadding: PaddingValues = PaddingValues(horizontal = 10.dp),
) {
    LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = contentPadding) {
        items(list, key = { it.text }, contentType = { it }) { item ->
            Chip(text = item.text, icon = item.icon, iconConfig = item.iconConfig) {
                list.replaceAll { it.copy(isSelected = it.text == item.text) }
            }
        }
    }
}

/**
 * [Checkboxes] - Чекбоксы
 *
 * @param width ширина
 * @param list список чекбоксов
 * @param textSize размер текста
 * @param enabled вкл\выкл чекбоксов
 * @param colors цвета чекбоксов
 */
@Composable
fun Checkboxes(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    list: SnapshotStateList<SelectableItemConfig>,
    textSize: Int = 18,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors(),
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        list.forEachIndexed { index, item ->
            GetCheckbox(item = item, enabled = enabled, colors = colors, textSize = textSize) {
                list[index] = item.copy(isSelected = !item.isSelected)
            }
        }
    }
}

/**
 * [CheckboxesTree] - Дерево чекбоксов
 *
 * @param width ширина
 * @param root главный чекбокс
 * @param textSize размер текста
 * @param list список чекбоксов
 * @param enabled вкл\выкл чекбоксов
 * @param colors цвета чекбоксов
 */
@Composable
fun CheckboxesTree(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    root: RootSelectionConfig,
    textSize: Int = 18,
    list: SnapshotStateList<SelectableItemConfig>,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors(),
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { changeState(list, root.state) }
                    .padding(top = 5.dp, bottom = 5.dp),
        ) {
            TriStateCheckbox(
                state = root.state.value,
                onClick = { changeState(list, root.state) },
                enabled = enabled,
                colors = root.colors,
            )
            Text(text = root.text, fontSize = textSize.sp)
        }
        list.forEachIndexed { index, item ->
            GetCheckbox(
                item = item,
                textSize = textSize,
                colors = colors,
                enabled = enabled,
                isTree = true,
            ) {
                list[index] = item.copy(isSelected = !item.isSelected)
                getTriState(list, root.state)
            }
        }
    }
}

@Composable
private fun GetCheckbox(
    item: SelectableItemConfig,
    textSize: Int,
    enabled: Boolean,
    colors: CheckboxColors,
    isTree: Boolean = false,
    onClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable { onClick() }
                .padding(start = if (isTree) 20.dp else 0.dp, top = 5.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = item.isSelected,
            onCheckedChange = { onClick() },
            enabled = enabled,
            colors = colors,
        )
        Text(text = item.text, fontSize = textSize.sp)
    }
}

private fun getTriState(
    list: SnapshotStateList<SelectableItemConfig>,
    rootState: MutableState<ToggleableState>,
) {
    var activeCheckboxes = 0
    list.forEach { if (it.isSelected) activeCheckboxes += 1 }
    rootState.value =
        when (activeCheckboxes) {
            0 -> ToggleableState.Off
            list.count() -> ToggleableState.On
            else -> ToggleableState.Indeterminate
        }
}

private fun changeState(
    list: SnapshotStateList<SelectableItemConfig>,
    rootState: MutableState<ToggleableState>,
) {
    rootState.value =
        when (rootState.value) {
            ToggleableState.On -> ToggleableState.Off
            else -> ToggleableState.On
        }
    list.indices.forEach { index ->
        list[index] = list[index].copy(isSelected = rootState.value == ToggleableState.On)
    }
}
