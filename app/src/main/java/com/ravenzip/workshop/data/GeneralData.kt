package com.ravenzip.workshop.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class TextParameters(val value: String, val color: Color? = null, val size: Int)

data class IconParameters(
    val value: ImageVector,
    val description: String = "",
    val color: Color? = null,
    val size: Int = 25
)
