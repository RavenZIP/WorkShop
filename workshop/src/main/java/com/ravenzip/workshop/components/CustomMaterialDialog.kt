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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ravenzip.workshop.data.IconParameters
import com.ravenzip.workshop.data.TextParameters

/**
 * [AlertDialog] - Диалоговое окно
 *
 * @param icon иконка
 * @param title заголовок
 * @param text описание
 * @param onDismissText текст кнопки отмены
 * @param onConfirmationText текст кнопки подтверждения
 * @param title заголовок
 * @param containerColors цвета контейнера
 * @param onDismiss действие при отмене
 * @param onConfirmation действие при подтверждении
 */
@Composable
fun AlertDialog(
    icon: IconParameters? = null,
    title: TextParameters,
    text: TextParameters,
    onDismissText: TextParameters,
    onConfirmationText: TextParameters,
    containerColors: CardColors = CardDefaults.cardColors(),
    onDismiss: () -> Unit,
    onConfirmation: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(shape = RoundedCornerShape(10.dp), colors = containerColors) {
            Column(
                modifier =
                    Modifier.padding(start = 20.dp, end = 20.dp, top = 25.dp, bottom = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (icon !== null && icon.size > 0) {
                    Icon(
                        imageVector = icon.value,
                        contentDescription = icon.description,
                        modifier = Modifier.size(icon.size.dp),
                        tint = icon.color ?: containerColors.contentColor
                    )
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                }

                Text(text = title.value, fontSize = title.size.sp, fontWeight = FontWeight.Medium)

                Spacer(modifier = Modifier.padding(top = 20.dp))
                Text(text = text.value, fontSize = text.size.sp)

                Spacer(modifier = Modifier.padding(top = 20.dp))
                Row {
                    SimpleButton(
                        width = 0.5f,
                        text =
                            TextParameters(value = onDismissText.value, size = onDismissText.size),
                        textAlign = TextAlign.Center,
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = containerColors.containerColor,
                                contentColor = MaterialTheme.colorScheme.primary
                            ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        onDismiss()
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    SimpleButton(
                        text =
                            TextParameters(
                                value = onConfirmationText.value,
                                size = onConfirmationText.size
                            ),
                        textAlign = TextAlign.Center,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        onConfirmation()
                    }
                }
            }
        }
    }
}
