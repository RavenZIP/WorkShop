package com.ravenzip.workshop.components

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
 * Простая кнопка с текстом
 *
 * Параметры:
 * 1) width - ширина кнопки (по умолчанию 0.9f, не обязательный)
 * 2) text: текст на кнопке (обязательный)
 * 3) textAlign - расположение текста (обязательный)
 * 4) colors - цвета кнопки (по умолчанию берутся из темы приложения, не обязательный)
 * 5) shape - радиус скругления кнопки (по умолчанию 10.dp, не обязательный)
 * 6) contentPadding - внутренние отступы (по умолчанию ButtonDefaults.ContentPadding, не
 *    обязательный)
 * 7) onClick - действие при нажатии (обязательный)
 */
@Composable
fun SimpleButton(
    width: Float? = 0.9f,
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
            modifier = getModifier(width),
            fontSize = text.size.sp,
            fontWeight = FontWeight.Medium,
            textAlign = textAlign
        )
    }
}

/**
 * Кнопка с заголовком и описанием
 *
 * Параметры:
 * 1) width - ширина кнопки (по умолчанию 0.9f, не обязательный)
 * 2) title - заголовок (обязательный)
 * 3) text - описание (обязательный)
 * 4) textContainerAlign - выравнивание заголовка и описания (по умолчанию Alignment.Start, не
 *    обязательный)
 * 5) icon - иконка (обязательный)
 * 6) colors - цвета кнопки (по умолчанию берутся из темы приложения, не обязательный)
 * 7) shape - радиус скругления кнопки (по умолчанию 10.dp, не обязательный)
 * 8) onClick - действие при нажатии (обязательный)
 */
@Composable
fun CustomButton(
    width: Float? = 0.9f,
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
 * Кнопка с иконкой и текстом, расположенными по горизонтали
 *
 * Параметры:
 * 1) width - ширина кнопки (по умолчанию 0.9f, не обязательный)
 * 2) text - текст (обязательный)
 * 3) icon - иконка (обязательный)
 * 4) iconPositionIsLeft - расположить иконку слева (по умолчанию true, не обязательный)
 * 5) colors - цвета кнопки (по умолчанию берутся из темы приложения, не обязательный)
 * 6) shape - радиус скругления кнопки (по умолчанию 10.dp, не обязательный)
 * 7) onClick - действие при нажатии (обязательный)
 */
@Composable
fun RowIconButton(
    width: Float? = 0.9f,
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
        Row(modifier = getModifier(width = width), verticalAlignment = Alignment.CenterVertically) {
            if (iconPositionIsLeft) {
                Icon(
                    imageVector = icon.value,
                    contentDescription = icon.description,
                    modifier = Modifier.size(icon.size.dp)
                )
                Text(
                    text = text.value,
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = text.size.sp,
                    fontWeight = FontWeight.Medium
                )
            } else {
                Text(
                    text = text.value,
                    modifier = Modifier.fillMaxWidth(0.9f).padding(end = 10.dp),
                    fontSize = text.size.sp,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = icon.value,
                    contentDescription = icon.description,
                    tint = icon.color ?: colors.contentColor
                )
            }
        }
    }
}

/**
 * Кнопка с иконкой и текстом, расположенными по вертикали
 *
 * Параметры:
 * 1) text - текст на кнопке (обязательный)
 * 2) icon - иконка (обязательный)
 * 3) colors - цвета кнопки (по умолчанию берутся из темы приложения, не обязательный)
 * 4) shape - радиус скругления кнопки (по умолчанию 10.dp, не обязательный)
 * 5) onClick - действие при нажатии (обязательный)
 */
@Composable
fun ColIconButton(
    text: TextParameters,
    icon: IconParameters,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(10.dp),
    onClick: () -> Unit
) {
    TextButton(onClick = { onClick() }, shape = shape, colors = colors) {
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

// TODO: предстоит переделать, первая реализация
private fun getModifier(width: Float?): Modifier {
    var modifier: Modifier = Modifier
    if (width != null) modifier = modifier.fillMaxWidth()
    return modifier.padding(top = 10.dp, bottom = 10.dp)
}
