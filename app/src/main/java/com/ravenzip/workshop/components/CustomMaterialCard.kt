package com.ravenzip.workshop.components

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.data.IconParameters
import com.ravenzip.workshop.data.TextParameters

/**
 * Информационная карточка
 *
 * Параметры:
 * 1) width - ширина кнопки (по умолчанию 0.9f, не обязательный)
 * 2) icon - иконка (обязательный)
 * 3) title - заголовок (обязательный)
 * 4) text - текст (обязательный)
 * 5) shape - радиус скругления (по умолчанию 10.dp, не обязательный)
 * 6) colors - цвета карточки (по умолчанию берутся из темы приложения, не обязательный)
 */
@Composable
fun InfoCard(
    width: Float = 0.9f,
    icon: IconParameters,
    title: TextParameters,
    text: TextParameters,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: CardColors = CardDefaults.cardColors()
) {
    Card(
        modifier = Modifier.fillMaxWidth(width),
        colors = colors,
        shape = shape,
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Icon(
                imageVector = icon.value,
                contentDescription = icon.description,
                tint = icon.color ?: colors.contentColor,
                modifier = Modifier.size(icon.size.dp)
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                text = title.value,
                color = title.color ?: colors.contentColor,
                fontSize = title.size.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.9f),
                text = text.value,
                color = text.color ?: colors.contentColor,
                fontSize = text.size.sp,
                fontWeight = FontWeight.W400
            )
        }
    }
}
