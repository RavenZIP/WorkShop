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
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.forms.control.FormControl

/**
 * [Switch] - Переключатель
 *
 * @param control контрол элемента
 * @param width ширина
 * @param title заголовок
 * @param titleConfig параметры заголовка
 * @param text описание
 * @param textConfig параметры описания
 * @param colors цвета свича
 */
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
