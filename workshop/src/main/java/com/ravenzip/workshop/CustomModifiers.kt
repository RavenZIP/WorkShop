package com.ravenzip.workshop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/** Задать только верхний отступ */
internal fun Modifier.paddingTop(value: Dp) = this then (this.padding(top = value))

/** Задать только нижний отступ */
internal fun Modifier.paddingEnd(value: Dp) = this then (this.padding(end = value))

/** Получить модификатор максимальной ширины с указанным значением */
internal fun Modifier.fillMaxWidthWithValue(value: Float?) =
    this.then(if (value == null) this else this.fillMaxWidth(value))

/** Получить модификатор максимальной ширины */
internal fun Modifier.fillMaxWidthWithoutValue(value: Float?) =
    this.then(if (value == null) this else this.fillMaxWidth())

internal fun Modifier.clickable(value: (() -> Unit)?) =
    this then (if (value == null) this else this.clickable { value() })
