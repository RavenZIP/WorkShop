package com.ravenzip.workshop.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.data.IconParameters
import com.ravenzip.workshop.data.TextParameters

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
    onClick: () -> Unit
) {
    Box(modifier = Modifier.clip(shape).background(backgroundColor).clickable { onClick() }) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.W500,
            letterSpacing = 0.sp)
    }
}

/**
 * [BoxedChip] - Чип c иконкой и скругленным квадратным контейнером
 *
 * @param icon иконка
 * @param shape радиус скругления
 * @param backgroundColor цвет контейнера
 */
@Composable
fun BoxedChip(
    icon: IconParameters,
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy(0.05f),
    shape: Shape = RoundedCornerShape(10.dp)
) {
    Box(modifier = Modifier.clip(shape).background(backgroundColor)) {
        Icon(
            imageVector = icon.value,
            contentDescription = icon.description,
            modifier = Modifier.padding(10.dp).size(icon.size.dp),
            tint = icon.color ?: MaterialTheme.colorScheme.primary)
    }
}

/**
 * [BoxedChipGroup] - Групповой контейнер для [BoxedChip] с кнопкой
 *
 * Кнопка предназначена для реализации отображения дополнительной информации о чипах
 *
 * @param items список иконок для чипов
 * @param buttonText текст для кнопки
 * @param buttonIcon иконка для кнопки
 * @param buttonColors цвета для кнопки
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BoxedChipGroup(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    items: List<IconParameters>,
    buttonText: TextParameters? = null,
    buttonIcon: IconParameters? = null,
    buttonColors: ButtonColors =
        ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(0.08f),
            contentColor = MaterialTheme.colorScheme.primary)
) {
    if (buttonText != null && buttonIcon !== null){
        Column(Modifier.fillMaxWidth(width)) {
            FlowRow(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                items.forEach { item -> BoxedChip(icon = item) }
            }

            Spacer(modifier = Modifier.padding(top = 10.dp))

            RowIconButton(
                width = 1f,
                text = buttonText,
                icon = buttonIcon,
                iconPositionIsLeft = false,
                colors = buttonColors,
                contentPadding = PaddingValues(10.dp)) {}
        }
    } else {
        FlowRow(
            modifier = Modifier.fillMaxWidth(width), horizontalArrangement = Arrangement.SpaceBetween) {
            items.forEach { item -> BoxedChip(icon = item) }
        }
    }
}
