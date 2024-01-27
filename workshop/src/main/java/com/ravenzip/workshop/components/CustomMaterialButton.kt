package com.ravenzip.workshop.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.ravenzip.workshop.data.IconParameters
import com.ravenzip.workshop.data.TextParameters

/**
 * [SimpleButton] - Простая кнопка с текстом
 *
 * @param width ширина кнопки (по умолчанию 0.9f, не обязательный)
 * @param text текст на кнопке (обязательный)
 * @param textAlign расположение текста (обязательный)
 * @param colors цвета кнопки (по умолчанию берутся из темы приложения, не обязательный)
 * @param shape радиус скругления кнопки (по умолчанию 10.dp, не обязательный)
 * @param contentPadding внутренние отступы (по умолчанию ButtonDefaults.ContentPadding, не
 *   обязательный)
 * @param onClick действие при нажатии (обязательный)
 */
@Composable
fun SimpleButton(
    @FloatRange(from = 0.0, to = 1.0) width: Float? = 0.9f,
    text: TextParameters,
    textAlign: TextAlign,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(10.dp),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = if (width != null) Modifier.fillMaxWidth(width) else Modifier,
        colors = colors,
        shape = shape,
        contentPadding = contentPadding
    ) {
        Text(
            text = text.value,
            modifier = Modifier.getWidthWithPadding(width),
            fontSize = text.size.sp,
            fontWeight = FontWeight.Medium,
            textAlign = textAlign
        )
    }
}

/**
 * [CustomButton] - Кнопка с заголовком и описанием
 *
 * @param width ширина кнопки (по умолчанию 0.9f, не обязательный)
 * @param title заголовок (обязательный)
 * @param text описание (обязательный)
 * @param icon иконка (обязательный)
 * @param textContainerAlign выравнивание заголовка и описания (по умолчанию Alignment.Start, не
 *   обязательный)
 * @param colors цвета кнопки (по умолчанию берутся из темы приложения, не обязательный)
 * @param shape радиус скругления кнопки (по умолчанию 10.dp, не обязательный)
 * @param onClick действие при нажатии (обязательный)
 */
@Composable
fun CustomButton(
    @FloatRange(from = 0.0, to = 1.0) width: Float? = 0.9f,
    title: TextParameters,
    text: TextParameters,
    icon: IconParameters,
    textContainerAlign: Alignment.Horizontal = Alignment.Start,
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
                imageVector = icon.value,
                contentDescription = icon.description,
                modifier = Modifier.padding(end = 10.dp).size(icon.size.dp),
                tint = icon.color ?: colors.contentColor
            )
            Column(
                modifier = (if (width != null) Modifier.fillMaxWidth() else Modifier),
                horizontalAlignment = textContainerAlign
            ) {
                Text(
                    text = title.value,
                    color = title.color ?: colors.contentColor,
                    fontSize = title.size.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = text.value,
                    color = text.color ?: colors.contentColor,
                    fontSize = text.size.sp,
                    fontWeight = FontWeight.W400
                )
            }
        }
    }
}

/**
 * [RowIconButton] - Кнопка с иконкой и текстом, расположенными по горизонтали
 *
 * @param width ширина кнопки (по умолчанию 0.9f, не обязательный)
 * @param text текст (обязательный)
 * @param icon иконка (обязательный)
 * @param iconPositionIsLeft расположить иконку слева (по умолчанию true, не обязательный)
 * @param colors цвета кнопки (по умолчанию берутся из темы приложения, не обязательный)
 * @param shape радиус скругления кнопки (по умолчанию 10.dp, не обязательный)
 * @param onClick действие при нажатии (обязательный)
 */
@Composable
fun RowIconButton(
    @FloatRange(from = 0.0, to = 1.0) width: Float? = 0.9f,
    text: TextParameters,
    icon: IconParameters,
    iconPositionIsLeft: Boolean = true,
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
            modifier = Modifier.getWidthWithPadding(width),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (iconPositionIsLeft) {
                Icon(
                    imageVector = icon.value,
                    contentDescription = icon.description,
                    modifier = Modifier.size(icon.size.dp),
                    tint = icon.color ?: colors.contentColor
                )
                Text(
                    text = text.value,
                    color = text.color ?: colors.contentColor,
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = text.size.sp,
                    fontWeight = FontWeight.Medium
                )
            } else {
                Text(
                    text = text.value,
                    color = text.color ?: colors.contentColor,
                    modifier = Modifier.fillMaxWidth(0.9f).padding(end = 10.dp),
                    fontSize = text.size.sp,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = icon.value,
                    contentDescription = icon.description,
                    modifier = Modifier.size(icon.size.dp),
                    tint = icon.color ?: colors.contentColor
                )
            }
        }
    }
}

/**
 * [ColIconButton] - Кнопка с иконкой и текстом, расположенными по вертикали
 *
 * @param text текст (обязательный)
 * @param icon иконка (обязательный)
 * @param colors цвета кнопки (по умолчанию берутся из темы приложения, не обязательный)
 * @param shape радиус скругления кнопки (по умолчанию 10.dp, не обязательный) contentPadding
 * @param contentPadding внутренние отступы (по умолчанию ButtonDefaults.TextButtonContentPadding,
 *   не обязательный
 * @param onClick действие при нажатии (обязательный)
 */
@Composable
fun ColIconButton(
    text: TextParameters,
    icon: IconParameters,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(10.dp),
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    onClick: () -> Unit
) {
    TextButton(
        onClick = { onClick() },
        shape = shape,
        colors = colors,
        contentPadding = contentPadding
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon.value,
                contentDescription = icon.description,
                modifier = Modifier.size(icon.size.dp),
                tint = icon.color ?: colors.contentColor
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

private fun Modifier.getWidthWithPadding(width: Float?) =
    this.then(
        if (width != null) {
            Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp)
        } else {
            Modifier.padding(top = 10.dp, bottom = 10.dp)
        }
    )
