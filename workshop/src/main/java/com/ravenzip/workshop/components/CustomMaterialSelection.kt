package com.ravenzip.workshop.components

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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.selection.RootSelectionConfig
import com.ravenzip.workshop.data.selection.SelectableChipConfig
import com.ravenzip.workshop.data.selection.SelectableItemConfig
import com.ravenzip.workshop.forms.FormState
import com.ravenzip.workshop.forms.state.CheckBoxTreeFormState
import com.ravenzip.workshop.forms.state.MultiSelectFormState
import com.ravenzip.workshop.forms.state.RadioButtonState

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
 * @param onCheckedChanged действие при нажатии на [Switch]
 */
@Composable
@Deprecated("Не использовать, переходить на Switch с FormState")
fun Switch(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    isChecked: MutableState<Boolean>,
    title: String,
    titleConfig: TextConfig,
    text: String,
    textConfig: TextConfig,
    enabled: Boolean = true,
    colors: SwitchColors = SwitchDefaults.colors(),
    onCheckedChanged: () -> Unit = { isChecked.value = !isChecked.value },
) {
    val titleColor = remember { titleConfig.color ?: Color.Unspecified }
    val textColor = remember { textConfig.color ?: Color.Unspecified }

    Row(
        modifier =
            Modifier.fillMaxWidth(width)
                .clip(RoundedCornerShape(10.dp))
                .clickable { onCheckedChanged() }
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
            checked = isChecked.value,
            onCheckedChange = { onCheckedChanged() },
            enabled = enabled,
            colors = colors,
        )
    }
}

/**
 * [Switch] - Переключатель
 *
 * @param state состояние свича
 * @param width ширина
 * @param title заголовок
 * @param titleConfig параметры заголовка
 * @param text описание
 * @param textConfig параметры описания
 * @param colors цвета свича
 */
@Composable
@ExperimentalMaterial3Api
fun Switch(
    state: FormState<Boolean>,
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
                .clickable { state.setValue(!state.value) }
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
            checked = state.value,
            onCheckedChange = { state.setValue(!state.value) },
            enabled = state.isEnabled,
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
 * @param onClick действие при нажатии на радиокнопку
 */
@Composable
@Deprecated("Не использовать, переходить на RadioGroup с FormState")
fun RadioGroup(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    list: SnapshotStateList<SelectableItemConfig>,
    textSize: Int = 18,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
    onClick: (item: SelectableItemConfig) -> Unit = { item ->
        list.replaceAll { it.copy(isSelected = it.text == item.text) }
    },
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        list.forEach { item ->
            Row(
                modifier =
                    Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { onClick(item) }
                        .padding(top = 5.dp, bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = item.isSelected,
                    onClick = { onClick(item) },
                    enabled = enabled,
                    colors = colors,
                )

                Text(text = item.text, fontSize = textSize.sp)
            }
        }
    }
}

/**
 * [RadioGroup] - Радиокнопки
 *
 * @param state состояние радиокнопки
 * @param width ширина
 * @param textSize размер текста
 * @param contentPadding вертикальный отступ между радиокнопками
 * @param colors цвета радиокнопок
 */
@Composable
@ExperimentalMaterial3Api
fun <T> RadioGroup(
    state: RadioButtonState<T>,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    textSize: Int = 18,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    Column(modifier = Modifier.fillMaxWidth(width), verticalArrangement = contentPadding) {
        state.items.forEach { item ->
            Row(
                modifier =
                    Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { state.setValue(item) }
                        .padding(top = 5.dp, bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = state.isSelected(item),
                    onClick = { state.setValue(item) },
                    enabled = state.isEnabled,
                    colors = colors,
                )

                Text(text = state.view(item), fontSize = textSize.sp)
            }
        }
    }
}

/**
 * [ChipRadioGroup] - Радиогруппа с чипами
 *
 * @param width ширина
 * @param list список радиокнопок
 * @param containerPadding отступ для контейнера
 * @param contentPadding отступ между радиокнопками
 * @param onClick действие при нажатии на чип
 */
@Composable
fun ChipRadioGroup(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 1f,
    list: SnapshotStateList<SelectableChipConfig>,
    containerPadding: PaddingValues = PaddingValues(horizontal = 10.dp),
    contentPadding: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(10.dp),
    onClick: (item: SelectableChipConfig) -> Unit = { item ->
        list.replaceAll { it.copy(isSelected = it.text == item.text) }
    },
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(width),
        horizontalArrangement = contentPadding,
        contentPadding = containerPadding,
    ) {
        items(list, key = { it.text }, contentType = { it }) { item ->
            SelectableChip(
                isSelected = item.isSelected,
                text = item.text,
                textConfig = item.textConfig,
                icon = item.icon,
                iconConfig = item.iconConfig,
            ) {
                onClick(item)
            }
        }
    }
}

/**
 * [Checkbox] - чекбокс
 *
 * @param isSelected флаг выбора чекбокса
 * @param text текст чекбокса
 * @param textConfig параметры текста
 * @param enabled вкл\выкл чекбоксов
 * @param colors цвета чекбоксов
 * @param onClick действие при нажатии
 */
@Composable
@ExperimentalMaterial3Api
fun Checkbox(
    isSelected: Boolean,
    text: String,
    textConfig: TextConfig,
    enabled: Boolean,
    colors: CheckboxColors,
    onClick: () -> Unit,
) {
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
 * @param width ширина
 * @param list список чекбоксов
 * @param textSize размер текста
 * @param enabled вкл\выкл чекбоксов
 * @param colors цвета чекбоксов
 * @param onClick действие при нажатии
 */
@Composable
@Deprecated("Не использовать, переходить на CheckBoxGroup с FormState")
fun CheckBoxGroup(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    list: SnapshotStateList<SelectableItemConfig>,
    textSize: Int = 18,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    onClick: (Int, SelectableItemConfig) -> Unit = { index, item ->
        list[index] = item.copy(isSelected = !item.isSelected)
    },
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        list.forEachIndexed { index, item ->
            Checkbox(item = item, enabled = enabled, colors = colors, textSize = textSize) {
                onClick(index, item)
            }
        }
    }
}

/**
 * [CheckBoxGroup] - Чекбоксы
 *
 * @param state состояние группы чекбоксов
 * @param width ширина
 * @param textConfig параметры текста
 * @param contentPadding отступ между чекбоксами
 * @param colors цвета чекбоксов
 */
@Composable
@ExperimentalMaterial3Api
fun <T> CheckBoxGroup(
    state: MultiSelectFormState<T>,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    textConfig: TextConfig = TextConfig.S18,
    contentPadding: Arrangement.HorizontalOrVertical = Arrangement.spacedBy(10.dp),
    colors: CheckboxColors = CheckboxDefaults.colors(),
) {
    Column(modifier = Modifier.fillMaxWidth(width), verticalArrangement = contentPadding) {
        state.items.forEach { item ->
            Checkbox(
                isSelected = state.isSelected(item),
                text = state.view(item),
                textConfig = textConfig,
                enabled = state.isEnabled,
                colors = colors,
            ) {
                state.setValue(item)
            }
        }
    }
}

/**
 * [CheckboxTree] - Дерево чекбоксов
 *
 * @param width ширина
 * @param root главный чекбокс
 * @param textSize размер текста
 * @param list список чекбоксов
 * @param enabled вкл\выкл чекбоксов
 * @param colors цвета чекбоксов
 * @param onClickToRoot действие при нажатии на главный чекбокс
 * @param onClickToChild действие при нажатии на дочерние чекбоксы
 */
@Composable
@Deprecated("Не использовать, переходить на CheckBoxTree с FormState")
fun CheckboxTree(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    root: RootSelectionConfig,
    textSize: Int = 18,
    list: SnapshotStateList<SelectableItemConfig>,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    onClickToRoot: () -> Unit = { changeRootState(list, root.state) },
    onClickToChild: (Int, SelectableItemConfig) -> Unit = { index, item ->
        list[index] = item.copy(isSelected = !item.isSelected)
        recalculateTreeState(list, root.state)
    },
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { onClickToRoot() }
                    .padding(top = 5.dp, bottom = 5.dp),
        ) {
            TriStateCheckbox(
                state = root.state.value,
                onClick = { onClickToRoot() },
                enabled = enabled,
                colors = root.colors,
            )
            Text(text = root.text, fontSize = textSize.sp)
        }

        list.forEachIndexed { index, item ->
            Checkbox(
                item = item,
                textSize = textSize,
                colors = colors,
                enabled = enabled,
                isTree = true,
            ) {
                onClickToChild(index, item)
            }
        }
    }
}

/**
 * [CheckboxTree] - Дерево чекбоксов
 *
 * @param state состояние дерева
 * @param width ширина
 * @param parentText текст для главного чебокса
 * @param parentTextConfig параметры текста для главного чекбокса
 * @param parentPadding отступ между главным чекбоксом и группой дочерних
 * @param childPadding отступ между дочерними чекбоксами
 * @param parentColors цвета для главного чекбокса
 * @param childColors цвета для дочерних чекбоксов
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> CheckboxTree(
    state: CheckBoxTreeFormState<T>,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    parentText: String,
    parentTextConfig: TextConfig = TextConfig.S18,
    childTextConfig: TextConfig = TextConfig.S18,
    parentPadding: Arrangement.Vertical = Arrangement.spacedBy(5.dp),
    childPadding: Arrangement.Vertical = Arrangement.spacedBy(5.dp),
    parentColors: CheckboxColors = CheckboxDefaults.colors(),
    childColors: CheckboxColors = CheckboxDefaults.colors(),
) {
    Column(modifier = Modifier.fillMaxWidth(width), verticalArrangement = parentPadding) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { state.changeParentState() }
                    .padding(top = 5.dp, bottom = 5.dp),
        ) {
            TriStateCheckbox(
                state = state.parent,
                onClick = { state.changeParentState() },
                enabled = state.isEnabled,
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
            state.items.forEach { item ->
                Checkbox(
                    isSelected = state.isSelected(item),
                    text = state.view(item),
                    textConfig = childTextConfig,
                    enabled = state.isEnabled,
                    colors = childColors,
                ) {
                    state.setValue(item)
                }
            }
        }
    }
}

/**
 * [Checkbox] - чекбокс
 *
 * @param item параметры чекбокса
 * @param textSize размер текста
 * @param enabled вкл\выкл чекбоксов
 * @param colors цвета чекбоксов
 * @param isTree является ли этот чекбокс частью дерева чекбоксов
 * @param onClick действие при нажатии
 */
@Composable
@Deprecated(
    "Не использовать, переходить на Checkbox, который используется для компонентов с FormState"
)
fun Checkbox(
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

private fun recalculateTreeState(
    list: SnapshotStateList<SelectableItemConfig>,
    rootState: MutableState<ToggleableState>,
) {
    val activeCheckboxCount =
        list.fold(initial = 0) { acc, item -> if (item.isSelected) acc + 1 else acc }

    rootState.value =
        when (activeCheckboxCount) {
            0 -> ToggleableState.Off
            list.count() -> ToggleableState.On
            else -> ToggleableState.Indeterminate
        }
}

private fun changeRootState(
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
