package com.ravenzip.workshop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Простая кнопка с текстом
 *
 * Параметры:
 * 1) width - ширина кнопки (по умолчанию 0.9f, не обязательный)
 * 2) text: текст на кнопке (обязательный)
 * 3) textSize - размер текста (обязательный)
 * 4) textAlign - расположение текста (обязательный)
 * 5) colors - цвета кнопки (по умолчанию берутся из темы приложения, не обязательный)
 * 6) shape - радиус скругления кнопки (по умолчанию 10.dp, не обязательный)
 * 7) onClick - действие при нажатии (обязательный)
 */
@Composable
fun SimpleButton(
    width: Float? = 0.9f,
    text: String,
    textSize: Int,
    textAlign: TextAlign,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(10.dp),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = if (width != null) Modifier.fillMaxWidth(width) else Modifier,
        colors = colors,
        shape = shape
    ) {
        Text(
            text = text,
            modifier = getModifier(width),
            fontSize = textSize.sp,
            fontWeight = FontWeight.Medium,
            textAlign = textAlign
        )
    }
}

/**
 * Кнопка с заголовком и описанием.
 *
 * Параметры:
 * 1) width - ширина кнопки (по умолчанию 0.9f, не обязательный)
 * 2) title - текст заголовка (обязательный)
 * 3) titleColor - цвет текста заголовка (по умолчанию берется из темы из темы приложения, не
 *    обязательный)
 * 4) titleSize - размер текста заголовка (обязательный)
 * 5) text - текст описания (по умолчанию не задан, не обязательный)
 * 6) textColor - цвет текста описания (по умолчанию берется из темы из темы приложения, не
 *    обязательный)
 * 7) textSize - размер текста описания (по умолчанию не задан, не обязательный)
 * 8) textContainerAlign - выравнивание заголовка и описания (по умолчанию Alignment.Start, не
 *    обязательный)
 * 9) icon - иконка (обязательный)
 * 10) iconSize - размер иконки (по умолчанию 25.dp, не обязательный)
 * 11) contentDescription - описание иконки (по умолчанию не задан, не обязательный)
 * 12) iconColor - цвет иконки (по умолчанию берется из темы приложения, не обязательный)
 * 13) colors - цвета кнопки (по умолчанию берутся из темы приложения, не обязательный)
 * 14) shape - радиус скругления кнопки (по умолчанию 10.dp, не обязательный)
 * 15) onClick - действие при нажатии (обязательный)
 */
@Composable
fun ButtonWithTitleAndIcon(
    width: Float? = 0.9f,
    title: String,
    titleColor: Color = MaterialTheme.colorScheme.onPrimary,
    titleSize: Int,
    text: String? = null,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textSize: Int = 0,
    textContainerAlign: Alignment.Horizontal = Alignment.Start,
    icon: ImageVector,
    iconSize: Int = 25,
    contentDescription: String = "",
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(10.dp),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = if (width != null) Modifier.fillMaxWidth(width) else Modifier,
        colors = colors,
        shape = shape
    ) {
        Row(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.padding(end = 10.dp).size(iconSize.dp),
                tint = iconColor
            )
            Column(
                modifier = (if (width != null) Modifier.fillMaxWidth() else Modifier),
                horizontalAlignment = textContainerAlign
            ) {
                Text(
                    text = title,
                    color = titleColor,
                    fontSize = titleSize.sp,
                    fontWeight = FontWeight.Medium
                )
                if (text != null && textSize > 0) {
                    Text(
                        text = text,
                        color = textColor,
                        fontSize = textSize.sp,
                        fontWeight = FontWeight.W400
                    )
                }
            }
        }
    }
}

/**
 * Кнопка с иконкой и текстом
 *
 * Параметры:
 * 1) width - ширина кнопки (по умолчанию 0.9f, не обязательный)
 * 2) text - текст на кнопке (обязательный)
 * 3) textSize - размер текста (обязательный)
 * 4) icon - иконка (обязательный)
 * 5) contentDescription - описание иконки (по умолчанию не задан, не обязательный)
 * 6) iconSize - размер иконки (по умолчанию 25.dp, не обязательный)
 * 7) iconColor - цвет иконки (по умолчанию берется из темы приложения, не обязательный)
 * 8) iconPositionLeft - расположить иконку слева (по умолчанию true, не обязательный)
 * 9) colors - цвета кнопки (по умолчанию берутся из темы приложения, не обязательный)
 * 10) shape - радиус скругления кнопки (по умолчанию 10.dp, не обязательный)
 * 11) onClick - действие при нажатии (обязательный)
 */
@Composable
fun ButtonWithIcon(
    width: Float? = 0.9f,
    text: String,
    textSize: Int,
    icon: ImageVector,
    contentDescription: String = "",
    iconSize: Int = 25,
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
    iconPositionLeft: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(10.dp),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = if (width != null) Modifier.fillMaxWidth(width) else Modifier,
        colors = colors,
        shape = shape
    ) {
        Row(modifier = getModifier(width = width), verticalAlignment = Alignment.CenterVertically) {
            if (iconPositionLeft) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    modifier = Modifier.size(iconSize.dp)
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = textSize.sp,
                    fontWeight = FontWeight.Medium
                )
            } else {
                Text(
                    text = text,
                    modifier = Modifier.fillMaxWidth(0.9f).padding(end = 10.dp),
                    fontSize = textSize.sp,
                    fontWeight = FontWeight.Medium
                )
                Icon(imageVector = icon, contentDescription = contentDescription, tint = iconColor)
            }
        }
    }
}

/**
 * Квадратная кнопка с иконкой и текстом по центру
 *
 * Параметры:
 * 1) text - текст на кнопке (обязательный)
 * 2) textColor - цвет текста (по умолчанию берется из темы приложения, не обязательный)
 * 3) textSize - размер текста (обязательный)
 * 4) icon - иконка (обязательный)
 * 5) contentDescription - описание иконки (по умолчанию не задан, не обязательный)
 * 6) iconSize - размер иконки (по умолчанию 25.dp, не обязательный)
 * 7) iconColor - цвет иконки (по умолчанию берется из темы приложения, не обязательный)
 * 8) colors - цвета кнопки (по умолчанию берутся из темы приложения, не обязательный)
 * 9) shape - радиус скругления кнопки (по умолчанию 10.dp, не обязательный)
 * 10) onClick - действие при нажатии (обязательный)
 */
@Composable
fun TextButtonWithIcon(
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textSize: Int,
    icon: ImageVector,
    contentDescription: String = "",
    iconSize: Int = 25,
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(15),
    onClick: () -> Unit
) {
    TextButton(onClick = { onClick() }, shape = shape, colors = colors) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.padding(bottom = 5.dp).size(iconSize.dp),
                tint = iconColor
            )
            Text(
                text = text,
                color = textColor,
                fontSize = textSize.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

// TODO: предстоит переделать, первая реализация
private fun getModifier(width: Float?): Modifier {
    var modifier: Modifier = Modifier
    if (width != null) modifier = modifier.fillMaxWidth()
    return modifier.padding(top = 10.dp, bottom = 10.dp)
}
