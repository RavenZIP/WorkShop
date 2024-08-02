package com.ravenzip.workshop.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.data.SnackBarVisualsExtended

/**
 * [SnackBar] - Уведомления
 *
 * @param snackBarHostState состояние
 * @param containerColors цвета контейнера
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
        val visuals = it.visuals as SnackBarVisualsExtended
        val progress = remember { Animatable(1f) }

        LaunchedEffect(
            key1 = Unit,
            block = {
                progress.animateTo(
                    targetValue = 0f,
                    animationSpec =
                        tween(
                            durationMillis = it.visuals.duration.getMs().toInt(),
                            easing = FastOutLinearInEasing))
            })

        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            colors = containerColors) {
                Row(
                    modifier = Modifier.padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        Icon(
                            imageVector = visuals.icon.value,
                            contentDescription = visuals.icon.description,
                            modifier = Modifier.size(visuals.icon.size.dp),
                            tint = visuals.icon.color ?: containerColors.contentColor)
                        Text(text = visuals.message, modifier = Modifier.padding(start = 10.dp))
                    }

                LinearProgressIndicator(
                    progress = { progress.value },
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
