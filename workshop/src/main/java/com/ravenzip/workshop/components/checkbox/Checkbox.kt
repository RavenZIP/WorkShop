package com.ravenzip.workshop.components.checkbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.model.TextConfig
import org.jetbrains.annotations.ApiStatus.Experimental

/**
 * [Checkbox] - Чекбокс
 *
 * @param control контрол элемента
 * @param text текст чекбокса
 * @param textConfig параметры текста
 * @param colors цвета чекбоксов
 * @param onClick действие при нажатии
 */
@Experimental
@Composable
fun Checkbox(
    control: FormControl<Boolean>,
    text: String = "Текст Checkbox",
    textConfig: TextConfig = TextConfig.S18,
    colors: CheckboxColors = CheckboxDefaults.colors(),
    onClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier.fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable { control.setValue(!control.value) }
                .padding(top = 5.dp, bottom = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = control.value,
            onCheckedChange = { control.setValue(!control.value) },
            enabled = control.isEnabled,
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
 * [Checkbox] - чекбокс
 *
 * @param isSelected выбран
 * @param text текст чекбокса
 * @param textConfig параметры текста
 * @param enabled вкл\выкл чекбоксов
 * @param colors цвета чекбоксов
 * @param onClick действие при нажатии
 */
@Experimental
@Composable
fun Checkbox(
    isSelected: Boolean,
    text: String = "Текст Checkbox",
    textConfig: TextConfig = TextConfig.S18,
    enabled: Boolean = true,
    colors: CheckboxColors = CheckboxDefaults.colors(),
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
