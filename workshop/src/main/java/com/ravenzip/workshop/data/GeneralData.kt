package com.ravenzip.workshop.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

class TextParameters(val value: String, val color: Color? = null, val size: Int)

interface Icon {
    val value: ImageVector
    val description: String
    val size: Int
}

class IconParameters(
    override val value: ImageVector,
    override val description: String = "",
    override val size: Int = 25,
    val color: Color? = null
) : Icon

class Error(val value: Boolean = false, val text: String = "")
