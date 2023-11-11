package com.ravenzip.workshop.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun Spinner(
    isLoading: Boolean,
    isIndeterminate: Boolean = true,
    containerColors: CardColors = CardDefaults.cardColors()
) {
    if (isLoading) {
        Dialog(onDismissRequest = {}) {
            Card(
                modifier = Modifier.size(100.dp),
                shape = RoundedCornerShape(10.dp),
                colors = containerColors
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }

    @Composable
    fun DeterminateSpinner(progressValue: Float) {
        val infiniteTransition = rememberInfiniteTransition(label = "")
        val progressAnimationValue by
            infiniteTransition.animateFloat(
                initialValue = 0.0f,
                targetValue = progressValue,
                animationSpec = infiniteRepeatable(animation = tween(900)),
                label = ""
            )
        if ()
        CircularProgressIndicator(
            progress = { progressAnimationValue },
        )
    }

    /// AlertDialog(onDismissRequest = { /*TODO*/}, properties = DialogProperties()) { Text(text =
    // "АБОБАА") }
}
