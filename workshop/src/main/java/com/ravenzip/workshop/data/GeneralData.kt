package com.ravenzip.workshop.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

class TextConfig(val value: String, val size: Int = 16, val color: Color? = null)

interface Icon {
    val value: ImageVector
    val description: String
    val size: Int
}

class IconConfig(
    override val value: ImageVector,
    override val description: String = "",
    override val size: Int = 25,
    val color: Color? = null
) : Icon

class Error(val value: Boolean = false, val text: String = "")
