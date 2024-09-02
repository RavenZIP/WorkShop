package com.ravenzip.workshop.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.data.icon.Icon
import com.ravenzip.workshop.data.icon.IconConfig

/**
 * [Icon] - Иконка
 *
 * @param modifier модификаторы
 * @param icon иконка
 * @param iconConfig параметры иконки
 * @param defaultColor дефолтный цвет в случае отсутствия цвета в [iconConfig]
 */
@Composable
fun Icon(
    modifier: Modifier = Modifier,
    icon: Icon,
    iconConfig: IconConfig,
    defaultColor: Color = MaterialTheme.colorScheme.primary,
) {
    when (icon) {
        is Icon.ImageVectorIcon -> {
            Icon(
                imageVector = icon.imageVector,
                contentDescription = iconConfig.description,
                modifier = Modifier.size(iconConfig.size.dp),
                tint = iconConfig.color ?: defaultColor,
            )
        }

        is Icon.ResourceIcon -> {
            Icon(
                imageVector = ImageVector.vectorResource(id = icon.id),
                contentDescription = iconConfig.description,
                modifier = modifier.size(iconConfig.size.dp),
                tint = iconConfig.color ?: defaultColor,
            )
        }
    }
}
