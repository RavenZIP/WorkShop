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
 * Опционально:
 * 1) Ширина кнопки
 * 2) Общие цвета для кнопки
 * 3) Радиус скругления кнопки
 */
@Composable
fun SimpleButton(
    width: Float = 0.9f,
    text: String,
    textSize: Int,
    textAlign: TextAlign,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(15),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(width),
        colors = colors,
        shape = shape
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp),
            fontSize = textSize.sp,
            fontWeight = FontWeight.Medium,
            textAlign = textAlign
        )
    }
}

/**
 * Кнопка с заголовком и описанием.
 *
 * Опционально:
 * 1) Ширина кнопки
 * 2) Цвет заголовка
 * 3) Цвет текста
 * 4) Расположение текста внутри кнопки
 * 5) Описание иконки
 * 6) Общие цвета для кнопки
 * 7) Радиус скругления кнопки
 */
@Composable
fun SimpleButtonWithTitleAndIcon(
    width: Float = 0.9f,
    title: String,
    titleColor: Color = MaterialTheme.colorScheme.onPrimary,
    titleSize: Int,
    text: String,
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
    textSize: Int,
    textAlign: Alignment.Horizontal = Alignment.Start,
    icon: ImageVector?,
    contentDescription: String = "",
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(15),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(width),
        colors = colors,
        shape = shape
    ) {
        if (icon == null) {
            PrepareTextContent(
                title = title,
                titleColor = titleColor,
                titleSize = titleSize,
                text = text,
                textColor = textColor,
                textSize = textSize,
                textAlign = textAlign
            )
        } else {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                    modifier = Modifier.padding(end = 10.dp),
                    tint = iconColor
                )
                PrepareTextContent(
                    title = title,
                    titleColor = titleColor,
                    titleSize = titleSize,
                    text = text,
                    textColor = textColor,
                    textSize = textSize,
                    textAlign = textAlign
                )
            }
        }
    }
}

/** Текстовый контент для SimpleButtonWithTitleAndIcon */
@Composable
private fun PrepareTextContent(
    title: String,
    titleColor: Color,
    titleSize: Int,
    text: String,
    textColor: Color,
    textSize: Int,
    textAlign: Alignment.Horizontal
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp),
        horizontalAlignment = textAlign
    ) {
        Text(
            text = title,
            color = titleColor,
            fontSize = titleSize.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = text, color = textColor, fontSize = textSize.sp, fontWeight = FontWeight.Medium)
    }
}

/**
 * Кнопка с иконкой и текстом
 *
 * Опционально:
 * 1) Ширина кнопки
 * 2) Описание иконки
 * 3) Цвет иконки
 * 4) Расположение иконки относительно текста
 * 5) Расположение иконки справа (близко к тексту или с краю кнопки)
 * 6) Общие цвета для кнопки
 * 7) Радиус скругления кнопки
 */
@Composable
fun SimpleButtonWithIcon(
    width: Float = 0.9f,
    text: String,
    textSize: Int,
    icon: ImageVector,
    contentDescription: String = "",
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
    iconPositionIsLeft: Boolean = true,
    iconRightAtTheEnd: Boolean = false,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(15),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(width),
        colors = colors,
        shape = shape
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (iconPositionIsLeft) {
                Icon(imageVector = icon, contentDescription = contentDescription)
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = textSize.sp,
                    fontWeight = FontWeight.Medium
                )
            } else {
                Text(
                    text = text,
                    modifier =
                        if (iconRightAtTheEnd) Modifier.fillMaxWidth(0.9f).padding(end = 10.dp)
                        else Modifier.padding(end = 10.dp),
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
 * Опицонально:
 * 1) Цвет текста
 * 2) Описание иконки
 * 3) Цвет иконки
 * 4) Общие цвета для кнопки
 * 5) Скругление кнопки
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
