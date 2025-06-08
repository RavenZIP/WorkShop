package com.ravenzip.workshop.components.radio

import androidx.annotation.FloatRange
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.forms.control.FormControl

/**
 * [RadioGroup] - Радиокнопки
 *
 * @param control контрол элемента
 * @param source источник данных
 * @param view отображаемый текст для радиокнопок
 * @param keySelector ключ для сравнения
 * @param width ширина
 * @param textSize размер текста
 * @param contentPadding вертикальный отступ между радиокнопками
 * @param colors цвета радиокнопок
 */
@Composable
fun <T, K : Any> RadioGroup(
    control: FormControl<T>,
    source: List<T>,
    view: (T) -> String,
    keySelector: (T) -> K,
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    textSize: Int = 18,
    contentPadding: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    val selectedKey = remember(control.value) { keySelector(control.value) }

    Column(modifier = Modifier.fillMaxWidth(width), verticalArrangement = contentPadding) {
        source.forEach { item ->
            Row(
                modifier =
                    Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { control.setValue(item) }
                        .padding(top = 5.dp, bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = selectedKey == keySelector(item),
                    onClick = { control.setValue(item) },
                    enabled = control.isEnabled,
                    colors = colors,
                )

                Text(text = view(item), fontSize = textSize.sp)
            }
        }
    }
}
