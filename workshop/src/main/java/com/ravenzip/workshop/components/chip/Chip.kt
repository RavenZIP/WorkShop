package com.ravenzip.workshop.components.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.components.icon.Icon
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData

/**
 * [Chip] - Чип с текстом
 *
 * @param text текст внутри чипа
 * @param textConfig параметры текста
 * @param withCutText обрезать текст (в конце будет троеточие)
 * @param backgroundColor цвет контейнера
 * @param shape радиус скругления
 * @param onClick действие при нажатии
 */
@Composable
fun Chip(
    text: String,
    textConfig: TextConfig = TextConfig.Chip,
    withCutText: Boolean = false,
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy(0.08f),
    shape: Shape = RoundedCornerShape(10.dp),
    onClick: () -> Unit = {},
) {
    val color = remember { textConfig.color ?: Color.Unspecified }
    val overflow =
        remember(withCutText) { if (withCutText) TextOverflow.Ellipsis else TextOverflow.Clip }
    val maxLines = remember(withCutText) { if (withCutText) 1 else Int.MAX_VALUE }

    Box(modifier = Modifier.clip(shape).background(backgroundColor).clickable { onClick() }) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            color = color,
            fontSize = textConfig.size,
            fontWeight = textConfig.weight,
            letterSpacing = textConfig.letterSpacing,
            overflow = overflow,
            maxLines = maxLines,
        )
    }
}

/**
 * [Chip] - Чип с иконкой
 *
 * @param text текст внутри чипа
 * @param textConfig параметры текста
 * @param icon иконка
 * @param iconConfig параметры иконки
 * @param withCutText обрезать текст (в конце будет троеточие)
 * @param backgroundColor цвет контейнера
 * @param shape радиус скругления
 * @param onClick действие при нажатии
 */
@Composable
fun Chip(
    text: String,
    textConfig: TextConfig = TextConfig.Chip,
    icon: IconData,
    iconConfig: IconConfig = IconConfig.Small,
    withCutText: Boolean = false,
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy(0.08f),
    shape: Shape = RoundedCornerShape(10.dp),
    onClick: () -> Unit = {},
) {
    val color = remember { textConfig.color ?: Color.Unspecified }
    val overflow =
        remember(withCutText) { if (withCutText) TextOverflow.Ellipsis else TextOverflow.Clip }
    val maxLines = remember(withCutText) { if (withCutText) 1 else Int.MAX_VALUE }

    Box(
        modifier =
            Modifier.clip(shape)
                .background(backgroundColor)
                .clickable { onClick() }
                .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                icon = icon,
                iconConfig = iconConfig,
                defaultColor = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = text,
                color = color,
                fontSize = textConfig.size,
                fontWeight = textConfig.weight,
                letterSpacing = textConfig.letterSpacing,
                overflow = overflow,
                maxLines = maxLines,
            )
        }
    }
}
