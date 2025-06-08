package com.ravenzip.workshop.components.chip

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.components.button.RowIconButton
import com.ravenzip.workshop.model.button.ButtonContainerConfig
import com.ravenzip.workshop.model.button.ButtonContentConfig
import com.ravenzip.workshop.model.icon.IconWithConfig

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
