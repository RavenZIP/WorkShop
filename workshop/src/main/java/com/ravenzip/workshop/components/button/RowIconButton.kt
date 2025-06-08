package com.ravenzip.workshop.components.button

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData
import com.ravenzip.workshop.fillMaxWidthWithValue
import com.ravenzip.workshop.fillMaxWidthWithoutValue

/**
 * [RowIconButton] - Кнопка с иконкой и текстом, расположенными по горизонтали
 *
 * @param width ширина кнопки
 * @param text текст
 * @param textConfig параметры текста
 * @param icon иконка
 * @param iconConfig параметры иконки
 * @param iconPositionIsLeft расположить иконку слева
 * @param colors цвета кнопки
 * @param shape радиус скругления кнопки
 * @param onClick действие при нажатии
 */
@Composable
fun RowIconButton(
    @FloatRange(from = 0.0, to = 1.0) width: Float? = 0.9f,
    text: String,
    textConfig: TextConfig = TextConfig.H3,
    icon: IconData,
    iconConfig: IconConfig = IconConfig.Default,
    iconPositionIsLeft: Boolean = true,
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
        Row(
            modifier = Modifier.fillMaxWidthWithoutValue(width),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (iconPositionIsLeft) {
                Icon(icon = icon, iconConfig = iconConfig, defaultColor = colors.contentColor)

                Spacer(modifier = Modifier.width(15.dp))

                Text(
                    text = text,
                    color = textConfig.color ?: colors.contentColor,
                    fontSize = textConfig.size,
                    fontWeight = textConfig.weight,
                    letterSpacing = textConfig.letterSpacing,
                )
            } else {
                Text(
                    text = text,
                    color = textConfig.color ?: colors.contentColor,
                    fontSize = textConfig.size,
                    fontWeight = textConfig.weight,
                    letterSpacing = textConfig.letterSpacing,
                )

                Spacer(modifier = Modifier.weight(weight = 1f, fill = width !== null).width(15.dp))

                Icon(icon = icon, iconConfig = iconConfig, defaultColor = colors.contentColor)
            }
        }
    }
}
