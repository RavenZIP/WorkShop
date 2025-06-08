package com.ravenzip.workshop.components.chip

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.components.icon.Icon
import com.ravenzip.workshop.model.TextConfig
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData

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
