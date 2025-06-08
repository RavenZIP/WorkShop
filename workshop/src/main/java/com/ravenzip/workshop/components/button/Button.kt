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
import com.ravenzip.workshop.model.TextConfig
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData
import com.ravenzip.workshop.fillMaxWidthWithValue
import com.ravenzip.workshop.fillMaxWidthWithoutValue

// TODO переименовать в Button, должны работать как перегрузки

/**
 * [SimpleButton] - Простая кнопка с текстом
 *
 * @param width ширина кнопки
 * @param text текст на кнопке
 * @param textConfig параметры текста
 * @param colors цвета кнопки
 * @param shape радиус скругления кнопки
 * @param contentPadding внутренние отступы
 * @param onClick действие при нажатии
 */
@Composable
fun SimpleButton(
    @FloatRange(from = 0.0, to = 1.0) width: Float? = 0.9f,
    text: String,
    textConfig: TextConfig = TextConfig.CenteredMedium,
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
        Text(
            text = text,
            modifier = Modifier.fillMaxWidthWithoutValue(width),
            fontSize = textConfig.size,
            fontWeight = textConfig.weight,
            textAlign = textConfig.align,
            letterSpacing = textConfig.letterSpacing,
        )
    }
}

/**
 * [CustomButton] - Кнопка с заголовком и описанием
 *
 * @param width ширина кнопки
 * @param title текст заголовка
 * @param titleConfig параметры заголовка
 * @param text текст описания
 * @param textConfig параметры описания
 * @param icon иконка
 * @param iconConfig параметры иконки
 * @param textAlign выравнивание заголовка и описания
 * @param colors цвета кнопки
 * @param shape радиус скругления кнопки
 * @param onClick действие при нажатии
 */
@Composable
fun CustomButton(
    @FloatRange(from = 0.0, to = 1.0) width: Float? = 0.9f,
    title: String,
    titleConfig: TextConfig = TextConfig.H3,
    text: String,
    textConfig: TextConfig = TextConfig.Small,
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
                    text = title,
                    color = titleConfig.color ?: colors.contentColor,
                    fontSize = titleConfig.size,
                    fontWeight = titleConfig.weight,
                    letterSpacing = titleConfig.letterSpacing,
                )

                Spacer(modifier = Modifier.padding(top = 2.5.dp))

                Text(
                    text = text,
                    color = textConfig.color ?: colors.contentColor,
                    fontSize = textConfig.size,
                    fontWeight = textConfig.weight,
                    letterSpacing = textConfig.letterSpacing,
                )
            }
        }
    }
}
