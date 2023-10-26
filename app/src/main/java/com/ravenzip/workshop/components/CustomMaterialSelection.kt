package com.ravenzip.workshop.components

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

data class ComponentInfo(val isSelected: Boolean, val text: String) {}

@Composable
fun Switch(
    isChecked: MutableState<Boolean>,
    title: String,
    titleSize: Int = 18,
    text: String,
    textSize: Int = 14,
    enabled: Boolean = true,
    colors: SwitchColors = SwitchDefaults.colors()
) {
    Row(
        modifier =
            Modifier.fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(10.dp))
                .clickable { isChecked.value = !isChecked.value }
                .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = title, fontSize = titleSize.sp)
            Text(text = text, fontSize = textSize.sp)
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

@Composable
fun RadioGroup(
    width: Float = 0.9f,
    list: SnapshotStateList<ComponentInfo>,
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
                Text(text = item.text)
            }
        }
    }
}

@Composable
fun CheckBoxes(
    width: Float = 0.9f,
    list: SnapshotStateList<ComponentInfo>,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors()
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        list.forEachIndexed { index, item ->
            GetCheckBox(item = item, enabled = enabled, colors = colors) {
                list[index] = item.copy(isSelected = !item.isSelected)
            }
        }
    }
}

@Composable
fun CheckBoxesTree(
    width: Float = 0.9f,
    triState: MutableState<ToggleableState>,
    triText: String,
    list: SnapshotStateList<ComponentInfo>,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors()
) {
    Column(modifier = Modifier.fillMaxWidth(width)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { triStateClick(list, triState) }
                    .padding(top = 5.dp, bottom = 5.dp),
        ) {
            TriStateCheckbox(state = triState.value, onClick = { triStateClick(list, triState) })
            Text(text = triText)
        }
        list.forEachIndexed { index, item ->
            GetCheckBox(item = item, enabled = enabled, colors = colors) {
                list[index] = item.copy(isSelected = !item.isSelected)
                getTriState(list, triState)
            }
        }
    }
}

@Composable
private fun GetCheckBox(
    item: ComponentInfo,
    enabled: Boolean,
    colors: CheckboxColors,
    onClick: () -> Unit
) {
    Row(
        modifier =
            Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable { onClick() }
                .padding(top = 5.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = item.isSelected,
            onCheckedChange = { onClick() },
            enabled = enabled,
            colors = colors
        )
        Text(text = item.text)
    }
}

private fun getTriState(
    list: SnapshotStateList<ComponentInfo>,
    triState: MutableState<ToggleableState>
) {
    var activeCheckboxes = 0
    list.forEach { if (it.isSelected) activeCheckboxes += 1 }
    triState.value =
        when (activeCheckboxes) {
            0 -> ToggleableState.Off
            list.count() -> ToggleableState.On
            else -> ToggleableState.Indeterminate
        }
}

private fun triStateClick(
    list: SnapshotStateList<ComponentInfo>,
    triState: MutableState<ToggleableState>
) {
    triState.value =
        when (triState.value) {
            ToggleableState.On -> ToggleableState.Off
            else -> ToggleableState.On
        }
    list.indices.forEach { index ->
        list[index] = list[index].copy(isSelected = triState.value == ToggleableState.On)
    }
}
