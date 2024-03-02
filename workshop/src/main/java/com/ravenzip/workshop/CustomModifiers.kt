package com.ravenzip.workshop

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/** Задать только верхний отступ */
internal fun Modifier.paddingTop(value: Dp) = this then (this.padding(top = value))

/** Задать только нижний отступ */
internal fun Modifier.paddingEnd(value: Dp) = this then (this.padding(end = value))

internal fun Modifier.getWidthWithPadding(width: Float?) =
    this.then(
        if (width != null) {
            Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp)
        } else {
            Modifier.padding(top = 10.dp, bottom = 10.dp)
        }
    )