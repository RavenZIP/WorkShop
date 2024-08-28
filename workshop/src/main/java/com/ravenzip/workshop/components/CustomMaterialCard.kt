package com.ravenzip.workshop.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.paddingEnd
import com.ravenzip.workshop.paddingTop

/**
 * [InfoCard] - Информационная карточка (с иконкой и заголовком)
 *
 * @param width ширина карточки
 * @param icon иконка
 * @param iconConfig параметры иконки
 * @param title заголовок
 * @param titleConfig параметры заголовка
 * @param text текст
 * @param textConfig параметры текста
 * @param titleUnderIcon расположить заголовок снизу иконки
 * @param shape радиус скругления
 * @param colors цвета карточки
 */
@Composable
fun InfoCard(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    icon: ImageVector,
    iconConfig: IconConfig = IconConfig.PrimaryBig,
    title: String,
    titleConfig: TextConfig = TextConfig.H2,
    text: String,
    textConfig: TextConfig = TextConfig.Small,
    titleUnderIcon: Boolean = false,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: CardColors = CardDefaults.cardColors(),
) {
    Card(
        modifier = Modifier.fillMaxWidth(width),
        colors = colors,
        shape = shape,
        elevation = CardDefaults.cardElevation(0.dp),
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            if (titleUnderIcon) {
                TitleWithIcon(
                    icon = icon,
                    iconConfig = iconConfig,
                    title = title,
                    titleConfig = titleConfig,
                    isPaddingTop = true,
                    colors = colors,
                )
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    TitleWithIcon(
                        icon = icon,
                        iconConfig = iconConfig,
                        title = title,
                        titleConfig = titleConfig,
                        isPaddingTop = false,
                        colors = colors,
                    )
                }
            }

            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.9f),
                text = text,
                color = textConfig.color ?: colors.contentColor,
                fontSize = textConfig.size.sp,
                fontWeight = textConfig.weight,
            )
        }
    }
}

/**
 * [InfoCard] - Информационная карточка (с чипом)
 *
 * @param width ширина карточки
 * @param chipText текст в чипе
 * @param cardText текст в карточке
 * @param cardTextConfig параметры текста в карточке
 * @param contentPadding отступы для контента внутри карточки (для чипа и текста)
 * @param shape радиус скругления
 * @param colors цвета карточки
 * @param content содержимое карточки (по дефолту текст)
 */
@Composable
fun InfoCard(
    @FloatRange(from = 0.0, to = 1.0) width: Float = 0.9f,
    chipText: String,
    cardText: String = "",
    cardTextConfig: TextConfig = TextConfig.Small,
    contentPadding: PaddingValues = PaddingValues(10.dp),
    shape: Shape = RoundedCornerShape(10.dp),
    colors: CardColors = CardDefaults.cardColors(),
    content: @Composable ColumnScope.() -> Unit = {
        Text(
            text = cardText,
            color = cardTextConfig.color ?: colors.contentColor,
            fontSize = cardTextConfig.size.sp,
            fontWeight = cardTextConfig.weight,
        )
    },
) {
    Card(modifier = Modifier.fillMaxWidth(width), shape = shape, colors = colors) {
        Column(modifier = Modifier.fillMaxWidth().padding(contentPadding)) {
            Chip(text = chipText)
            Spacer(modifier = Modifier.height(10.dp))
            content()
        }
    }
}

@Composable
private fun TitleWithIcon(
    icon: ImageVector,
    iconConfig: IconConfig,
    title: String,
    titleConfig: TextConfig,
    isPaddingTop: Boolean,
    colors: CardColors,
) {
    Icon(
        imageVector = icon,
        contentDescription = iconConfig.description,
        tint = iconConfig.color ?: colors.contentColor,
        modifier = Modifier.size(iconConfig.size.dp),
    )
    Spacer(modifier = if (isPaddingTop) Modifier.paddingTop(10.dp) else Modifier.paddingEnd(10.dp))
    Text(
        text = title,
        color = titleConfig.color ?: colors.contentColor,
        fontSize = titleConfig.size.sp,
        fontWeight = titleConfig.weight,
    )
}

// TODO ExpandableInfoCard
