package com.ravenzip.workshop.components.button

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.components.icon.Icon
import com.ravenzip.workshop.fillMaxWidthWithValue
import com.ravenzip.workshop.fillMaxWidthWithoutValue
import com.ravenzip.workshop.model.TextConfig
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData

/**
 * [RichButton] - Кнопка с иконкой, заголовком и описанием
 *
 * @param width ширина кнопки
 * @param label текст заголовка
 * @param labelConfig параметры заголовка
 * @param description текст описания
 * @param descriptionConfig параметры описания
 * @param icon иконка
 * @param iconConfig параметры иконки
 * @param textAlign выравнивание заголовка и описания
 * @param colors цвета кнопки
 * @param shape радиус скругления кнопки
 * @param onClick действие при нажатии
 */
@Composable
fun RichButton(
    @FloatRange(from = 0.0, to = 1.0) width: Float? = 0.9f,
    label: String,
    labelConfig: TextConfig = TextConfig.H3,
    description: String,
    descriptionConfig: TextConfig = TextConfig.Small,
    icon: IconData,
    iconConfig: IconConfig = IconConfig.Default,
    textAlign: Alignment.Horizontal = Alignment.Start,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(10.dp),
    contentPadding: PaddingValues = PaddingValues(18.dp),
    onClick: () -> Unit = {},
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidthWithValue(width),
        colors = colors,
        shape = shape,
        contentPadding = contentPadding,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon = icon, iconConfig = iconConfig, defaultColor = colors.contentColor)

            Spacer(modifier = Modifier.width(15.dp))

            Column(
                modifier = Modifier.fillMaxWidthWithoutValue(width),
                horizontalAlignment = textAlign,
            ) {
                Text(
                    text = label,
                    color = labelConfig.color ?: colors.contentColor,
                    fontSize = labelConfig.size,
                    fontWeight = labelConfig.weight,
                    letterSpacing = labelConfig.letterSpacing,
                )

                Spacer(modifier = Modifier.padding(top = 2.5.dp))

                Text(
                    text = description,
                    color = descriptionConfig.color ?: colors.contentColor,
                    fontSize = descriptionConfig.size,
                    fontWeight = descriptionConfig.weight,
                    letterSpacing = descriptionConfig.letterSpacing,
                )
            }
        }
    }
}
