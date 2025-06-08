package com.ravenzip.workshop.components.chip

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.components.icon.Icon
import com.ravenzip.workshop.components.layout.RoundedBox
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData

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
