package com.ravenzip.workshop.components.button

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.fillMaxWidthWithValue
import com.ravenzip.workshop.fillMaxWidthWithoutValue
import com.ravenzip.workshop.model.TextConfig

/**
 * [Button] - Простая кнопка с текстом
 *
 * @param width ширина кнопки
 * @param text текст на кнопке
 * @param textConfig параметры текста
 * @param colors цвета кнопки
 * @param shape радиус скругления кнопки
 * @param contentPadding внутренние отступы
 * @param onClick действие при нажатии
 */
@Composable
fun Button(
    @FloatRange(from = 0.0, to = 1.0) width: Float? = 0.9f,
    text: String,
    textConfig: TextConfig = TextConfig.CenteredMedium,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(10.dp),
    contentPadding: PaddingValues = PaddingValues(18.dp),
    onClick: () -> Unit = {},
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidthWithValue(width),
        colors = colors,
        shape = shape,
        contentPadding = contentPadding,
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidthWithoutValue(width),
            fontSize = textConfig.size,
            fontWeight = textConfig.weight,
            textAlign = textConfig.align,
            letterSpacing = textConfig.letterSpacing,
        )
    }
}
