package com.ravenzip.workshop.components.overlay

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import com.ravenzip.workshop.components.icon.Icon
import com.ravenzip.workshop.components.overlay.model.SnackBarVisualsConfig

/**
 * [SnackBar] - Уведомления
 *
 * @param snackBarHostState состояние
 * @param colors цвета контейнера и контента
 * @param elevation тень контейнера
 */
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SnackBar(
    snackBarHostState: SnackbarHostState,
    colors: CardColors =
        CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
    elevation: CardElevation = CardDefaults.cardElevation(5.dp),
) {
    SnackbarHost(hostState = snackBarHostState) { snackBarData ->
        val visuals = snackBarData.visuals as SnackBarVisualsConfig
        val progress = remember { Animatable(1f) }

        LaunchedEffect(
            key1 = Unit,
            block = {
                progress.animateTo(
                    targetValue = 0f,
                    animationSpec =
                        tween(
                            durationMillis = snackBarData.visuals.duration.getMs().toInt(),
                            easing = FastOutLinearInEasing,
                        ),
                )
            },
        )

        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            colors = colors,
            elevation = elevation,
        ) {
            Row(
                modifier = Modifier.padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Icon(
                    icon = visuals.icon,
                    iconConfig = visuals.iconConfig,
                    defaultColor = colors.contentColor,
                )
                Text(text = visuals.message, modifier = Modifier.padding(start = 10.dp))
            }

            LinearProgressIndicator(
                progress = { progress.value },
                color = visuals.iconConfig.color ?: colors.contentColor,
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
