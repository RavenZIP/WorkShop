package com.ravenzip.workshop.components.swtich

import androidx.annotation.FloatRange
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.model.TextConfig

/**
 * [Switch] - Переключатель
 *
 * @param control контрол элемента
 * @param width ширина
 * @param label заголовок
 * @param labelConfig параметры заголовка
 * @param description описание
 * @param descriptionConfig параметры описания
 * @param colors цвета свича
 */
@Composable
fun Switch(
    control: FormControl<Boolean>,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    label: String? = null,
    labelConfig: TextConfig? = null,
    description: String = "Описание",
    descriptionConfig: TextConfig = TextConfig.Small,
    colors: SwitchColors = SwitchDefaults.colors(),
) {
    val textColor = remember { descriptionConfig.color ?: Color.Unspecified }

    Row(
        modifier =
            Modifier.fillMaxWidth(width)
                .clip(RoundedCornerShape(10.dp))
                .clickable { control.setValue(!control.value) }
                .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (label != null && labelConfig !== null) {
            val titleColor = remember { labelConfig.color ?: Color.Unspecified }

            Column {
                Text(
                    text = label,
                    color = titleColor,
                    fontSize = labelConfig.size,
                    fontWeight = labelConfig.weight,
                    letterSpacing = labelConfig.letterSpacing,
                )

                Text(
                    text = description,
                    color = textColor,
                    fontSize = descriptionConfig.size,
                    fontWeight = descriptionConfig.weight,
                    letterSpacing = descriptionConfig.letterSpacing,
                )
            }
        } else {
            Text(
                text = description,
                color = textColor,
                fontSize = descriptionConfig.size,
                fontWeight = descriptionConfig.weight,
                letterSpacing = descriptionConfig.letterSpacing,
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
 * [Switch] - Переключатель
 *
 * @param isSelected выбран
 * @param isEnabled включен
 * @param width ширина
 * @param label заголовок
 * @param labelConfig параметры заголовка
 * @param description описание
 * @param descriptionConfig параметры описания
 * @param colors цвета свича
 */
@Composable
fun Switch(
    isSelected: Boolean,
    isEnabled: Boolean = true,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    label: String? = null,
    labelConfig: TextConfig? = null,
    description: String = "Описание",
    descriptionConfig: TextConfig = TextConfig.Small,
    colors: SwitchColors = SwitchDefaults.colors(),
    onClick: () -> Unit,
) {
    val textColor = remember { descriptionConfig.color ?: Color.Unspecified }

    Row(
        modifier =
            Modifier.fillMaxWidth(width)
                .clip(RoundedCornerShape(10.dp))
                .clickable { onClick() }
                .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (label != null && labelConfig !== null) {
            val titleColor = remember { labelConfig.color ?: Color.Unspecified }

            Column {
                Text(
                    text = label,
                    color = titleColor,
                    fontSize = labelConfig.size,
                    fontWeight = labelConfig.weight,
                    letterSpacing = labelConfig.letterSpacing,
                )

                Text(
                    text = description,
                    color = textColor,
                    fontSize = descriptionConfig.size,
                    fontWeight = descriptionConfig.weight,
                    letterSpacing = descriptionConfig.letterSpacing,
                )
            }
        } else {
            Text(
                text = description,
                color = textColor,
                fontSize = descriptionConfig.size,
                fontWeight = descriptionConfig.weight,
                letterSpacing = descriptionConfig.letterSpacing,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Switch(
            checked = isSelected,
            onCheckedChange = { onClick() },
            enabled = isEnabled,
            colors = colors,
        )
    }
}
