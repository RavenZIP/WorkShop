package com.ravenzip.workshop.components.checkbox

import androidx.annotation.FloatRange
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.data.Equatable
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.forms.control.FormControlTree
import com.ravenzip.workshop.forms.control.extension.changeParentState
import com.ravenzip.workshop.forms.control.extension.selectAll
import com.ravenzip.workshop.forms.control.extension.unselectAll
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.jetbrains.annotations.ApiStatus.Experimental

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
        launch {
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
        }

        launch {
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
