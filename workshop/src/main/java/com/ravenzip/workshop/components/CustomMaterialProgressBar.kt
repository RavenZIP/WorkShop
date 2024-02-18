package com.ravenzip.workshop.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.ravenzip.workshop.data.TextParameters

/**
 * [Spinner] - Спиннер
 *
 * @param text текст (обязательный)
 * @param containerColors цвета контейнера (по умолчанию берутся из темы приложения, не
 *   обязательный)
 */
@Composable
fun Spinner(text: TextParameters, containerColors: CardColors = CardDefaults.cardColors()) {
    Dialog(onDismissRequest = {}) {
        Card(shape = RoundedCornerShape(10.dp), colors = containerColors) {
            Column(
                modifier = Modifier.padding(20.dp).widthIn(0.dp, 250.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp))
                if (text.value !== "" && text.size > 0) {
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(
                        text = text.value,
                        fontSize = text.size.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

/**
 * [CircularProgressIndicator] - Круговой прогресс бар
 *
 * @param progressValue прогресс (обязательный)
 * @param text текст (обязательный)
 * @param showProgressPercentages отображать проценты прогресса (по умолчанию false, не
 *   обязательный)
 * @param containerColors цвета контейнера (по умолчанию берутся из темы приложения, не
 *   обязательный)
 */
@Composable
fun CircularProgressIndicator(
    progressValue: Float,
    text: TextParameters,
    showProgressPercentages: Boolean = false,
    containerColors: CardColors = CardDefaults.cardColors(),
) {
    Dialog(onDismissRequest = {}) {
        Card(shape = RoundedCornerShape(10.dp), colors = containerColors) {
            Column(
                modifier = Modifier.padding(20.dp).widthIn(0.dp, 250.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(55.dp),
                    progress = { progressValue },
                    trackColor = ProgressIndicatorDefaults.circularColor.copy(0.3f)
                )
                if (text.value !== "" && text.size > 0) {
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(
                        text = text.value,
                        fontSize = text.size.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )

                    if (showProgressPercentages)
                        Text(
                            text = "${String.format("%.2f", progressValue * 100)}%",
                            fontSize = text.size.sp,
                            fontWeight = FontWeight.Medium
                        )
                }
            }
        }
    }
}
