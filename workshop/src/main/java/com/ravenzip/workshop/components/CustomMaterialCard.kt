package com.ravenzip.workshop.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.data.IconConfig
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.paddingEnd
import com.ravenzip.workshop.paddingTop

/**
 * [InfoCard] - Информационная карточка
 *
 * @param width ширина карточки
 * @param icon иконка
 * @param title заголовок
 * @param text текст
 * @param isTitleUnderIcon расположить заголовок снизу иконки
 * @param shape радиус скругления
 * @param colors цвета карточки
 */
@Composable
fun InfoCard(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    icon: IconConfig,
    title: TextConfig,
    text: TextConfig,
    isTitleUnderIcon: Boolean = true,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: CardColors = CardDefaults.cardColors()
) {
    Card(
        modifier = Modifier.fillMaxWidth(width),
        colors = colors,
        shape = shape,
        elevation = CardDefaults.cardElevation(0.dp)) {
            Column(modifier = Modifier.padding(15.dp)) {
                if (isTitleUnderIcon) {
                    GetIconAndTitle(
                        icon = icon, title = title, isPaddingTop = true, colors = colors)
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        GetIconAndTitle(
                            icon = icon, title = title, isPaddingTop = false, colors = colors)
                    }
                }

                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    text = text.value,
                    color = text.color ?: colors.contentColor,
                    fontSize = text.size.sp,
                    fontWeight = FontWeight.W400)
            }
        }
}

@Composable
private fun GetIconAndTitle(
    icon: IconConfig,
    title: TextConfig,
    isPaddingTop: Boolean,
    colors: CardColors
) {
    Icon(
        imageVector = icon.value,
        contentDescription = icon.description,
        tint = icon.color ?: colors.contentColor,
        modifier = Modifier.size(icon.size.dp))
    Spacer(modifier = if (isPaddingTop) Modifier.paddingTop(10.dp) else Modifier.paddingEnd(10.dp))
    Text(
        text = title.value,
        color = title.color ?: colors.contentColor,
        fontSize = title.size.sp,
        fontWeight = FontWeight.Medium)
}

// TODO ExpandableInfoCard
