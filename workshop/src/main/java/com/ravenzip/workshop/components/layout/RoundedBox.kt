package com.ravenzip.workshop.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.clickable

/**
 * [RoundedBox] - Скругленный Box-контейнер
 *
 * @param onClick действие при нажатии
 * @param shape радиус скругления
 * @param backgroundColor цвет контейнера
 * @param content содержимое контейнера
 */
@Composable
fun RoundedBox(
    onClick: (() -> Unit)? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy(0.05f),
    shape: Shape = RoundedCornerShape(10.dp),
    content: @Composable BoxScope.() -> Unit,
) {
    Box(modifier = Modifier.clip(shape).background(backgroundColor).clickable(onClick)) {
        content()
    }
}
