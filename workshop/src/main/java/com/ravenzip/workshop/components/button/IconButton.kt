package com.ravenzip.workshop.components.button

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.components.icon.Icon
import com.ravenzip.workshop.components.layout.RoundedBox
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData

/**
 * [IconButton] - Кнопка с иконкой
 *
 * @param icon иконка
 * @param iconConfig параметры иконки
 * @param backgroundColor цвет контейнера
 * @param shape радиус скругления контейнера
 * @param onClick действие при нажатии
 */
@Composable
fun IconButton(
    icon: IconData,
    iconConfig: IconConfig = IconConfig.Small,
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy(0.05f),
    shape: Shape = RoundedCornerShape(10.dp),
    onClick: () -> Unit,
) {
    RoundedBox(onClick = onClick, backgroundColor = backgroundColor, shape = shape) {
        Icon(
            modifier = Modifier.padding(10.dp),
            icon = icon,
            iconConfig = iconConfig,
            defaultColor = LocalContentColor.current,
        )
    }
}
