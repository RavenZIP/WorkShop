package com.ravenzip.workshop.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.fillMaxWidthWithValue
import com.ravenzip.workshop.fillMaxWidthWithoutValue

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
            fontSize = textConfig.size.sp,
            fontWeight = textConfig.weight,
            textAlign = textConfig.align,
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
    titleConfig: TextConfig = TextConfig.H2,
    text: String,
    textConfig: TextConfig = TextConfig.Small,
    icon: ImageVector,
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
            Icon(
                imageVector = icon,
                contentDescription = iconConfig.description,
                modifier = Modifier.padding(end = 15.dp).size(iconConfig.size.dp),
                tint = iconConfig.color ?: colors.contentColor,
            )

            Column(
                modifier = Modifier.fillMaxWidthWithoutValue(width),
                horizontalAlignment = textAlign,
            ) {
                Text(
                    text = title,
                    color = titleConfig.color ?: colors.contentColor,
                    fontSize = titleConfig.size.sp,
                    fontWeight = titleConfig.weight,
                )
                Spacer(modifier = Modifier.padding(top = 2.5.dp))

                Text(
                    text = text,
                    color = textConfig.color ?: colors.contentColor,
                    fontSize = textConfig.size.sp,
                    fontWeight = textConfig.weight,
                )
            }
        }
    }
}

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
    textConfig: TextConfig = TextConfig.H2,
    icon: ImageVector,
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
                Icon(
                    imageVector = icon,
                    contentDescription = iconConfig.description,
                    modifier = Modifier.size(iconConfig.size.dp),
                    tint = iconConfig.color ?: colors.contentColor,
                )
                Text(
                    text = text,
                    color = textConfig.color ?: colors.contentColor,
                    modifier = Modifier.padding(start = 15.dp),
                    fontSize = textConfig.size.sp,
                    fontWeight = textConfig.weight,
                )
            } else {
                Text(
                    text = text,
                    color = textConfig.color ?: colors.contentColor,
                    modifier = Modifier.weight(1f).padding(end = 15.dp),
                    fontSize = textConfig.size.sp,
                    fontWeight = textConfig.weight,
                )
                Icon(
                    imageVector = icon,
                    contentDescription = iconConfig.description,
                    modifier = Modifier.size(iconConfig.size.dp),
                    tint = iconConfig.color ?: colors.contentColor,
                )
            }
        }
    }
}

/**
 * [ColIconButton] - Кнопка с иконкой и текстом, расположенными по вертикали
 *
 * @param text текст
 * @param textConfig параметры текста
 * @param icon иконка
 * @param iconConfig параметры иконки
 * @param colors цвета кнопки
 * @param shape радиус скругления кнопки
 * @param contentPadding внутренние отступы
 * @param onClick действие при нажатии
 */
@Composable
fun ColIconButton(
    text: String,
    textConfig: TextConfig = TextConfig.H2,
    icon: ImageVector,
    iconConfig: IconConfig = IconConfig.Default,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(10.dp),
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    onClick: () -> Unit = {},
) {
    TextButton(
        onClick = { onClick() },
        shape = shape,
        colors = colors,
        contentPadding = contentPadding,
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = iconConfig.description,
                modifier = Modifier.size(iconConfig.size.dp),
                tint = iconConfig.color ?: colors.contentColor,
            )
            if (text !== "" && textConfig.size > 0) {
                Spacer(modifier = Modifier.padding(bottom = 5.dp))
                Text(
                    text = text,
                    color = textConfig.color ?: colors.contentColor,
                    fontSize = textConfig.size.sp,
                    fontWeight = textConfig.weight,
                )
            }
        }
    }
}
