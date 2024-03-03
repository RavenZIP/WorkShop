package com.ravenzip.workshop

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/** Задать только верхний отступ */
internal fun Modifier.paddingTop(value: Dp) = this then (this.padding(top = value))

/** Задать только нижний отступ */
internal fun Modifier.paddingEnd(value: Dp) = this then (this.padding(end = value))

/** Задать отступ сверху и снизу */
internal fun Modifier.paddingTopBottom(value: Dp) =
    this then (this.padding(top = value, bottom = value))

/** Получить модификатор максимальной ширины */
internal fun Modifier.getFillMaxWidth(width: Float?) =
    this.then(if (width != null) this.fillMaxWidth() else this)
