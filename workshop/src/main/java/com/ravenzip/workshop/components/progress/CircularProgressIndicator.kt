package com.ravenzip.workshop.components.progress

import android.annotation.SuppressLint
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ravenzip.workshop.model.TextConfig

/**
 * [CircularProgressIndicator] - Круговой прогресс бар
 *
 * @param progressValue прогресс
 * @param text текст
 * @param textConfig параметры текста
 * @param showProgressPercentages отображать проценты прогресса
 * @param containerColors цвета контейнера
 */
@SuppressLint("DefaultLocale")
@Composable
fun CircularProgressIndicator(
    progressValue: Float,
    text: String,
    textConfig: TextConfig = TextConfig.CenteredMedium,
    showProgressPercentages: Boolean = false,
    containerColors: CardColors = CardDefaults.cardColors(),
) {
    Dialog(onDismissRequest = {}) {
        Card(shape = RoundedCornerShape(10.dp), colors = containerColors) {
            Column(
                modifier = Modifier.padding(20.dp).widthIn(0.dp, 250.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(55.dp),
                    progress = { progressValue },
                    trackColor = ProgressIndicatorDefaults.circularColor.copy(0.3f),
                )

                if (text.isNotEmpty()) {
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(
                        text = text,
                        fontSize = textConfig.size,
                        fontWeight = textConfig.weight,
                        textAlign = textConfig.align,
                        letterSpacing = textConfig.letterSpacing,
                    )

                    if (showProgressPercentages)
                        Text(
                            text = "${String.format("%.2f", progressValue * 100)}%",
                            fontSize = textConfig.size,
                            fontWeight = textConfig.weight,
                            letterSpacing = textConfig.letterSpacing,
                        )
                }
            }
        }
    }
}
