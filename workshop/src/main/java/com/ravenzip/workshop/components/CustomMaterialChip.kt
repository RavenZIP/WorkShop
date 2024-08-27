package com.ravenzip.workshop.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.data.button.ButtonContainerConfig
import com.ravenzip.workshop.data.button.ButtonContentConfig
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconWithConfig

/**
 * [Chip] - Чип с текстом
 *
 * @param text Текст внутри чипа
 * @param backgroundColor цвет контейнера
 * @param shape радиус скругления
 * @param onClick действие при нажатии
 */
@Composable
fun Chip(
    text: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy(0.08f),
    shape: Shape = RoundedCornerShape(10.dp),
    onClick: () -> Unit = {},
) {
    Box(modifier = Modifier.clip(shape).background(backgroundColor).clickable { onClick() }) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            letterSpacing = 0.sp,
        )
    }
}

/**
 * [Chip] - Чип с иконкой
 *
 * @param text Текст внутри чипа
 * @param icon иконка
 * @param iconConfig параметры иконки
 * @param backgroundColor цвет контейнера
 * @param shape радиус скругления
 * @param onClick действие при нажатии
 */
@Composable
fun Chip(
    text: String,
    icon: ImageVector,
    iconConfig: IconConfig = IconConfig.Small,
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy(0.08f),
    shape: Shape = RoundedCornerShape(10.dp),
    onClick: () -> Unit = {},
) {
    Box(
        modifier =
            Modifier.clip(shape)
                .background(backgroundColor)
                .clickable { onClick() }
                .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = iconConfig.description,
                modifier = Modifier.size(iconConfig.size.dp),
                tint = iconConfig.color ?: MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.W500, letterSpacing = 0.sp)
        }
    }
}

/**
 * [BoxedChip] - Чип c иконкой и скругленным квадратным контейнером
 *
 * @param icon иконка
 * @param iconConfig параметры иконки
 * @param shape радиус скругления
 * @param backgroundColor цвет контейнера
 */
@Composable
fun BoxedChip(
    icon: ImageVector,
    iconConfig: IconConfig = IconConfig.Small,
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy(0.05f),
    shape: Shape = RoundedCornerShape(10.dp),
) {
    RoundedBox(backgroundColor = backgroundColor, shape = shape) {
        Icon(
            imageVector = icon,
            contentDescription = iconConfig.description,
            modifier = Modifier.padding(10.dp).size(iconConfig.size.dp),
            tint = iconConfig.color ?: MaterialTheme.colorScheme.primary,
        )
    }
}

/**
 * [BoxedChipGroup] - Групповой контейнер для [BoxedChip] с кнопкой
 *
 * Кнопка предназначена для реализации отображения дополнительной информации о чипах
 *
 * @param items список иконок с параметрами для чипов
 * @param buttonContentConfig конфигурация контента для кнопки (текст, иконка, действие при нажатии)
 * @param buttonContainerConfig конфигурация контейнера для кнопки (ширина, цвета, радиус
 *   скругления, внутренние отступы)
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BoxedChipGroup(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    items: List<IconWithConfig>,
    buttonContentConfig: ButtonContentConfig? = null,
    buttonContainerConfig: ButtonContainerConfig? = null,
) {
    if (buttonContentConfig !== null) {
        val lightStyle = ButtonContainerConfig.lightStyle()
        val containerConfig =
            remember(buttonContainerConfig) {
                buttonContainerConfig ?: ButtonContainerConfig.smallButtonConfig(lightStyle)
            }

        Column(Modifier.fillMaxWidth(width)) {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                items.forEach { item -> BoxedChip(icon = item.icon, iconConfig = item.config) }
            }

            Spacer(modifier = Modifier.height(10.dp))

            RowIconButton(
                width = containerConfig.width,
                text = buttonContentConfig.text,
                textConfig = buttonContentConfig.textConfig,
                icon = buttonContentConfig.icon,
                iconConfig = buttonContentConfig.iconConfig,
                iconPositionIsLeft = false,
                colors = containerConfig.colors,
                shape = containerConfig.shape,
                contentPadding = containerConfig.contentPadding,
            ) {
                buttonContentConfig.onClick()
            }
        }
    } else {
        FlowRow(
            modifier = Modifier.fillMaxWidth(width),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            items.forEach { item -> BoxedChip(icon = item.icon, iconConfig = item.config) }
        }
    }
}
