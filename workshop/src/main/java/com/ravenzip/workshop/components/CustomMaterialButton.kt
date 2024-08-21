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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.data.IconConfig
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.fillMaxWidthWithValue
import com.ravenzip.workshop.fillMaxWidthWithoutValue

/**
 * [SimpleButton] - Простая кнопка с текстом
 *
 * @param width ширина кнопки
 * @param text текст на кнопке
 * @param textAlign расположение текста
 * @param colors цвета кнопки
 * @param shape радиус скругления кнопки
 * @param contentPadding внутренние отступы
 * @param onClick действие при нажатии
 */
@Composable
fun SimpleButton(
    @FloatRange(from = 0.0, to = 1.0) width: Float? = 0.9f,
    text: TextConfig,
    textAlign: TextAlign = TextAlign.Center,
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
            text = text.value,
            modifier = Modifier.fillMaxWidthWithoutValue(width),
            fontSize = text.size.sp,
            fontWeight = FontWeight.Medium,
            textAlign = textAlign,
        )
    }
}

/**
 * [CustomButton] - Кнопка с заголовком и описанием
 *
 * @param width ширина кнопки
 * @param title заголовок
 * @param text описание
 * @param icon иконка
 * @param textContainerAlign выравнивание заголовка и описания
 * @param colors цвета кнопки
 * @param shape радиус скругления кнопки
 * @param onClick действие при нажатии
 */
@Composable
fun CustomButton(
    @FloatRange(from = 0.0, to = 1.0) width: Float? = 0.9f,
    title: TextConfig,
    text: TextConfig,
    icon: IconConfig,
    textContainerAlign: Alignment.Horizontal = Alignment.Start,
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
                imageVector = icon.value,
                contentDescription = icon.description,
                modifier = Modifier.padding(end = 15.dp).size(icon.size.dp),
                tint = icon.color ?: colors.contentColor,
            )

            Column(
                modifier = Modifier.fillMaxWidthWithoutValue(width),
                horizontalAlignment = textContainerAlign,
            ) {
                Text(
                    text = title.value,
                    color = title.color ?: colors.contentColor,
                    fontSize = title.size.sp,
                    fontWeight = FontWeight.Medium,
                )
                Spacer(modifier = Modifier.padding(top = 2.5.dp))

                Text(
                    text = text.value,
                    color = text.color ?: colors.contentColor,
                    fontSize = text.size.sp,
                    fontWeight = FontWeight.W400,
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
 * @param icon иконка
 * @param iconPositionIsLeft расположить иконку слева
 * @param colors цвета кнопки
 * @param shape радиус скругления кнопки
 * @param onClick действие при нажатии
 */
@Composable
fun RowIconButton(
    @FloatRange(from = 0.0, to = 1.0) width: Float? = 0.9f,
    text: TextConfig,
    icon: IconConfig,
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
                    imageVector = icon.value,
                    contentDescription = icon.description,
                    modifier = Modifier.size(icon.size.dp),
                    tint = icon.color ?: colors.contentColor,
                )
                Text(
                    text = text.value,
                    color = text.color ?: colors.contentColor,
                    modifier = Modifier.padding(start = 15.dp),
                    fontSize = text.size.sp,
                    fontWeight = FontWeight.Medium,
                )
            } else {
                Text(
                    text = text.value,
                    color = text.color ?: colors.contentColor,
                    modifier = Modifier.weight(1f).padding(end = 15.dp),
                    fontSize = text.size.sp,
                    fontWeight = FontWeight.Medium,
                )
                Icon(
                    imageVector = icon.value,
                    contentDescription = icon.description,
                    modifier = Modifier.size(icon.size.dp),
                    tint = icon.color ?: colors.contentColor,
                )
            }
        }
    }
}

/**
 * [ColIconButton] - Кнопка с иконкой и текстом, расположенными по вертикали
 *
 * @param text текст
 * @param icon иконка
 * @param colors цвета кнопки
 * @param shape радиус скругления кнопки
 * @param contentPadding внутренние отступы
 * @param onClick действие при нажатии
 */
@Composable
fun ColIconButton(
    text: TextConfig,
    icon: IconConfig,
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
                imageVector = icon.value,
                contentDescription = icon.description,
                modifier = Modifier.size(icon.size.dp),
                tint = icon.color ?: colors.contentColor,
            )
            if (text.value !== "" && text.size > 0) {
                Spacer(modifier = Modifier.padding(bottom = 5.dp))
                Text(
                    text = text.value,
                    color = text.color ?: colors.contentColor,
                    fontSize = text.size.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}
