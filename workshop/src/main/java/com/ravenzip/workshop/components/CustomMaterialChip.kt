package com.ravenzip.workshop.components

import androidx.annotation.FloatRange
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.button.ButtonContainerConfig
import com.ravenzip.workshop.data.button.ButtonContentConfig
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData
import com.ravenzip.workshop.data.icon.IconWithConfig

/**
 * [Chip] - Чип с текстом
 *
 * @param text текст внутри чипа
 * @param textConfig параметры текста
 * @param backgroundColor цвет контейнера
 * @param shape радиус скругления
 * @param onClick действие при нажатии
 */
@Composable
fun Chip(
    text: String,
    textConfig: TextConfig = TextConfig.Chip,
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy(0.08f),
    shape: Shape = RoundedCornerShape(10.dp),
    onClick: () -> Unit = {},
) {
    val color = remember { textConfig.color ?: Color.Unspecified }

    Box(modifier = Modifier.clip(shape).background(backgroundColor).clickable { onClick() }) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            color = color,
            fontSize = textConfig.size,
            fontWeight = textConfig.weight,
            letterSpacing = textConfig.letterSpacing,
            overflow = textConfig.overflow
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
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy(0.08f),
    shape: Shape = RoundedCornerShape(10.dp),
    onClick: () -> Unit = {},
) {
    val color = remember { textConfig.color ?: Color.Unspecified }

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
                overflow = textConfig.overflow
            )
        }
    }
}

/**
 * [SelectableChip] - Выбираемый чип (аналог радиокнопки)
 *
 * @param isSelected флаг, отображающий текущий активный чип
 * @param text текст внутри чипа
 * @param textConfig параметры текста
 * @param icon иконка
 * @param iconConfig параметры иконки
 * @param backgroundColor цвет контейнера
 * @param shape радиус скругления
 * @param onClick действие при нажатии
 */
@Composable
fun SelectableChip(
    isSelected: Boolean,
    text: String,
    textConfig: TextConfig = TextConfig.Chip,
    icon: IconData,
    iconConfig: IconConfig = IconConfig.Small,
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy(0.08f),
    shape: Shape = RoundedCornerShape(10.dp),
    onClick: () -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val textColor = remember { textConfig.color ?: Color.Unspecified }
    val borderColor =
        animateColorAsState(
            targetValue = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
            animationSpec = tween(100, 0, LinearEasing),
            label = "",
        )

    Box(
        modifier =
            Modifier.clip(shape)
                .background(backgroundColor)
                .border(BorderStroke(2.dp, borderColor.value), shape)
                .selectable(
                    selected = isSelected,
                    onClick = onClick,
                    role = Role.RadioButton,
                    interactionSource = interactionSource,
                    indication = ripple(),
                )
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
                color = textColor,
                fontSize = textConfig.size,
                fontWeight = textConfig.weight,
                letterSpacing = textConfig.letterSpacing,
            )
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
    icon: IconData,
    iconConfig: IconConfig = IconConfig.Small,
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy(0.05f),
    shape: Shape = RoundedCornerShape(10.dp),
) {
    RoundedBox(backgroundColor = backgroundColor, shape = shape) {
        Icon(
            icon = icon,
            iconConfig = iconConfig,
            defaultColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(10.dp),
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
