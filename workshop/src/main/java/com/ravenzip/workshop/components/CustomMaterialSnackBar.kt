package com.ravenzip.workshop.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.data.SnackBarVisualsExtended
import kotlinx.coroutines.launch

/**
 * Уведомления
 *
 * @param snackBarHostState состояние (обязательный)
 * @param containerColors цвета контейнера (по умолчанию берутся из темы приложения, не
 *   обязательный)
 */
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SnackBar(
    snackBarHostState: SnackbarHostState,
    containerColors: CardColors = CardDefaults.cardColors(),
) {
    SnackbarHost(
        hostState = snackBarHostState,
    ) {
        val scope = rememberCoroutineScope()
        var progress by remember { mutableFloatStateOf(1f) }
        val visuals = it.visuals as SnackBarVisualsExtended
        val animateProgress by
            animateFloatAsState(
                targetValue = progress,
                animationSpec =
                    tween(
                        durationMillis = it.visuals.duration.getMs().toInt(),
                        easing = LinearEasing
                    ),
                label = "progress"
            )
        scope.launch { progress = 0f }
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            colors = containerColors
        ) {
            Row(
                modifier = Modifier.padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Icon(
                    imageVector = visuals.icon.value,
                    contentDescription = visuals.icon.description,
                    modifier = Modifier.size(visuals.icon.size.dp),
                    tint = visuals.icon.color ?: containerColors.contentColor
                )
                Text(text = visuals.message, modifier = Modifier.padding(start = 10.dp))
            }
            LinearProgressIndicator(
                progress = { animateProgress },
                color = visuals.icon.color ?: containerColors.contentColor,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

private fun SnackbarDuration.getMs(): Long {
    return when (this) {
        SnackbarDuration.Indefinite -> Long.MAX_VALUE
        SnackbarDuration.Long -> 10000L
        SnackbarDuration.Short -> 4000L
    }
}
