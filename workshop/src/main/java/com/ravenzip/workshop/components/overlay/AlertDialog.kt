package com.ravenzip.workshop.components.overlay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ravenzip.workshop.components.button.Button
import com.ravenzip.workshop.components.icon.Icon
import com.ravenzip.workshop.model.TextConfig
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData

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
    icon: IconData? = null,
    iconConfig: IconConfig = IconConfig.Default,
    title: String,
    titleConfig: TextConfig = TextConfig.H1,
    text: String,
    textConfig: TextConfig = TextConfig.Small,
    onDismissText: String,
    onDismissTextConfig: TextConfig = TextConfig.SmallCenteredMedium,
    onConfirmationText: String,
    onConfirmationTextConfig: TextConfig = TextConfig.SmallCenteredMedium,
    containerColors: CardColors = CardDefaults.cardColors(),
    onDismiss: () -> Unit,
    onConfirmation: () -> Unit,
) {
    val titleColor = remember { titleConfig.color ?: Color.Unspecified }
    val textColor = remember { textConfig.color ?: Color.Unspecified }

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(shape = RoundedCornerShape(10.dp), colors = containerColors) {
            Column(
                modifier =
                    Modifier.padding(start = 20.dp, end = 20.dp, top = 25.dp, bottom = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                if (icon !== null && iconConfig.size > 0) {
                    Icon(
                        icon = icon,
                        iconConfig = iconConfig,
                        defaultColor = containerColors.contentColor,
                    )
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                }

                Text(
                    text = title,
                    color = titleColor,
                    fontSize = titleConfig.size,
                    fontWeight = titleConfig.weight,
                    letterSpacing = titleConfig.letterSpacing,
                )

                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(
                    text = text,
                    color = textColor,
                    fontSize = textConfig.size,
                    fontWeight = textConfig.weight,
                    letterSpacing = textConfig.letterSpacing,
                )

                Spacer(modifier = Modifier.padding(top = 20.dp))
                Row {
                    Button(
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

                    Button(
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
