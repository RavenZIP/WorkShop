package com.ravenzip.workshop.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ravenzip.workshop.data.IconConfig
import com.ravenzip.workshop.data.TextConfig

/**
 * [AlertDialog] - Диалоговое окно
 *
 * @param icon иконка
 * @param iconConfig параметры иконки
 * @param title заголовок
 * @param titleConfig параметры заголовка
 * @param text описание
 * @param textConfig параметры описания
 * @param onDismissText текст кнопки отмены
 * @param onDismissTextConfig параметры кнопки отмены
 * @param onConfirmationText текст кнопки подтверждения
 * @param onConfirmationTextConfig параметры кнопки подтверждения
 * @param containerColors цвета контейнера
 * @param onDismiss действие при отмене
 * @param onConfirmation действие при подтверждении
 */
@Composable
fun AlertDialog(
    icon: ImageVector? = null,
    iconConfig: IconConfig? = null,
    title: String,
    titleConfig: TextConfig = TextConfig.H1,
    text: String,
    textConfig: TextConfig = TextConfig.Small,
    onDismissText: String,
    onDismissTextConfig: TextConfig = TextConfig.SmallMedium,
    onConfirmationText: String,
    onConfirmationTextConfig: TextConfig = TextConfig.SmallMedium,
    containerColors: CardColors = CardDefaults.cardColors(),
    onDismiss: () -> Unit,
    onConfirmation: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(shape = RoundedCornerShape(10.dp), colors = containerColors) {
            Column(
                modifier =
                    Modifier.padding(start = 20.dp, end = 20.dp, top = 25.dp, bottom = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                if (icon !== null && iconConfig != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = iconConfig.description,
                        modifier = Modifier.size(iconConfig.size.dp),
                        tint = iconConfig.color ?: containerColors.contentColor,
                    )
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                }

                Text(text = title, fontSize = titleConfig.size.sp, fontWeight = titleConfig.weight)

                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(text = text, fontSize = textConfig.size.sp)

                Spacer(modifier = Modifier.padding(top = 20.dp))
                Row {
                    SimpleButton(
                        width = 0.5f,
                        text = onDismissText,
                        textConfig = onDismissTextConfig,
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = containerColors.containerColor,
                                contentColor = MaterialTheme.colorScheme.primary,
                            ),
                        contentPadding = PaddingValues(0.dp),
                    ) {
                        onDismiss()
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    SimpleButton(
                        text = onConfirmationText,
                        textConfig = onConfirmationTextConfig,
                        contentPadding = PaddingValues(0.dp),
                    ) {
                        onConfirmation()
                    }
                }
            }
        }
    }
}
