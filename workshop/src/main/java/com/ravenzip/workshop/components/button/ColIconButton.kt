package com.ravenzip.workshop.components.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.components.icon.Icon
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.icon.IconData

/**
 * [ColIconButton] - Кнопка с иконкой и текстом, расположенными по вертикали
 *
 * @param text текст
 * @param textConfig параметры текста
 * @param icon иконка
 * @param iconConfig параметры иконки
 * @param colors цвета кнопки
 * @param shape радиус скругления кнопки
 * @param contentPadding внутренние отступы
 * @param onClick действие при нажатии
 */
@Composable
fun ColIconButton(
    text: String,
    textConfig: TextConfig = TextConfig.H3,
    icon: IconData,
    iconConfig: IconConfig = IconConfig.Default,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(10.dp),
    contentPadding: PaddingValues = ButtonDefaults.TextButtonContentPadding,
    onClick: () -> Unit = {},
) {
    TextButton(
        onClick = { onClick() },
        shape = shape,
        colors = colors,
        contentPadding = contentPadding,
    ) {
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(icon = icon, iconConfig = iconConfig, defaultColor = colors.contentColor)

            if (text.isNotEmpty()) {
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = text,
                    color = textConfig.color ?: colors.contentColor,
                    fontSize = textConfig.size,
                    fontWeight = textConfig.weight,
                    letterSpacing = textConfig.letterSpacing,
                )
            }
        }
    }
}
