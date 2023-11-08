package com.ravenzip.workshop.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CustomCardColors(
    val backgroundColor: Color,
    val iconColor: Color,
    val titleColor: Color,
    val textColor: Color
)

/**
 * Информационная карточка
 *
 * Параметры:
 * 1) width - ширина кнопки (по умолчанию 0.9f, не обязательный)
 * 2) icon - иконка (обязательный)
 * 3) title - заголовок (обязательный)
 * 4) text - текст (обязательный)
 * 5) shape - радиус скругления (по умолчанию 10.dp, не обязательный)
 * 6) colors - цвета карточки (обязательный)
 */
@Composable
fun InfoCard(
    width: Float = 0.9f,
    icon: ImageVector,
    title: String,
    text: String,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: CustomCardColors
) {
    Card(
        modifier = Modifier.fillMaxWidth(width),
        colors = CardDefaults.cardColors(containerColor = colors.backgroundColor),
        shape = shape,
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = colors.iconColor,
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                color = colors.titleColor,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.9f),
                text = text,
                color = colors.textColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400
            )
        }
    }
}
