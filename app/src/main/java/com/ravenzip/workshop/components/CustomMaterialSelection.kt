package com.ravenzip.workshop.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.ravenzip.workshop.data.RootParameters
import com.ravenzip.workshop.data.SelectionParameters
import com.ravenzip.workshop.data.TextParameters

/**
 * Switch
 *
 * Параметры:
 * 1) width - ширина (по умолчанию 0.9f, не обязательный)
 * 2) isChecked - состояние свича (обязательный)
 * 3) title: заголовок (обязательный)
 * 4) text: описание (обязательный)
 * 5) enabled - вкл\выкл свича (по умолчанию false, не обязательный)
 * 6) colors - цвета свича (по умолчанию берутся из темы приложения, не обязательный)
 */
@Composable
fun Switch(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    isChecked: MutableState<Boolean>,
    title: TextParameters,
    text: TextParameters,
    enabled: Boolean = true,
    colors: SwitchColors = SwitchDefaults.colors()
) {
    Row(
        modifier =
            Modifier.fillMaxWidth(width)
                .clip(RoundedCornerShape(10.dp))
                .clickable { isChecked.value = !isChecked.value }
                .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = title.value, fontSize = title.size.sp)
            Text(text = text.value, fontSize = text.size.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it },
            enabled = enabled,
            colors = colors
        )
    }
}

/**
 * RadioGroup
 *
 * Параметры:
 * 1) width - ширина (по умолчанию 0.9f, не обязательный)
 * 2) list - список радиокнопок (обязательный)
 * 3) textSize: размер текста (по умолчанию 18, не обязательный)
 * 4) enabled - вкл\выкл радиокнопок (по умолчанию false, не обязательный)
 * 5) colors - цвета радиокнопок (по умолчанию берутся из темы приложения, не обязательный)
 */
@Composable
fun RadioGroup(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    list: SnapshotStateList<SelectionParameters>,
    textSize: Int = 18,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors()
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = item.isSelected,
                    onClick = { list.replaceAll { it.copy(isSelected = it.text == item.text) } },
                    enabled = enabled,
                    colors = colors
                )
                Text(text = item.text, fontSize = textSize.sp)
            }
        }
    }
}

/**
 * CheckBoxes
 *
 * Параметры:
 * 1) width - ширина (по умолчанию 0.9f, не обязательный)
 * 2) list - список чекбоксов (обязательный)
 * 3) textSize: размер текста (по умолчанию 18, не обязательный)
 * 4) enabled - вкл\выкл чекбоксов (по умолчанию false, не обязательный)
 * 5) colors - цвета чекбоксов (по умолчанию берутся из темы приложения, не обязательный)
 */
@Composable
fun CheckBoxes(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    list: SnapshotStateList<SelectionParameters>,
    textSize: Int = 18,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors()
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        list.forEachIndexed { index, item ->
            GetCheckBox(item = item, enabled = enabled, colors = colors, textSize = textSize) {
                list[index] = item.copy(isSelected = !item.isSelected)
            }
        }
    }
}

/**
 * CheckBoxesTree
 *
 * Параметры:
 * 1) width - ширина (по умолчанию 0.9f, не обязательный)
 * 2) root - главный чекбокс (обязательный)
 * 3) textSize: размер текста (по умолчанию 18, не обязательный)
 * 4) list - список чекбоксов (обязательный)
 * 5) enabled - вкл\выкл чекбоксов (по умолчанию false, не обязательный)
 * 6) colors - цвета чекбоксов (по умолчанию берутся из темы приложения, не обязательный)
 */
@Composable
fun CheckBoxesTree(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    root: RootParameters,
    textSize: Int = 18,
    list: SnapshotStateList<SelectionParameters>,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors()
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { rootClick(list, root.state) }
                    .padding(top = 5.dp, bottom = 5.dp),
        ) {
            TriStateCheckbox(
                state = root.state.value,
                onClick = { rootClick(list, root.state) },
                enabled = enabled,
                colors = root.colors
            )
            Text(text = root.text, fontSize = textSize.sp)
        }
        list.forEachIndexed { index, item ->
            GetCheckBox(
                item = item,
                textSize = textSize,
                colors = colors,
                enabled = enabled,
                isTree = true
            ) {
                list[index] = item.copy(isSelected = !item.isSelected)
                getTriState(list, root.state)
            }
        }
    }
}

@Composable
private fun GetCheckBox(
    item: SelectionParameters,
    textSize: Int,
    enabled: Boolean,
    colors: CheckboxColors,
    isTree: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier =
            Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable { onClick() }
                .padding(start = if (isTree) 20.dp else 0.dp, top = 5.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = item.isSelected,
            onCheckedChange = { onClick() },
            enabled = enabled,
            colors = colors
        )
        Text(text = item.text, fontSize = textSize.sp)
    }
}

private fun getTriState(
    list: SnapshotStateList<SelectionParameters>,
    rootState: MutableState<ToggleableState>
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

private fun rootClick(
    list: SnapshotStateList<SelectionParameters>,
    rootState: MutableState<ToggleableState>
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
