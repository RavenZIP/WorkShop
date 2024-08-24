package com.ravenzip.workshop.data

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Immutable
class TextConfig(
    val size: Int = 16,
    val color: Color? = null,
    val align: TextAlign = TextAlign.Unspecified,
    val weight: FontWeight = FontWeight.Normal,
) {
    companion object {
        /**
         * size - 22
         *
         * color - null
         *
         * align - TextAlign.Unspecified
         *
         * weight - FontWeight.Medium
         */
        val H1 = TextConfig(size = 22, weight = FontWeight.Medium)

        /**
         * size - 18
         *
         * color - null
         *
         * align - TextAlign.Unspecified
         *
         * weight - FontWeight.Medium
         */
        val H2 = TextConfig(size = 18, weight = FontWeight.Medium)

        /**
         * size - 16
         *
         * color - null
         *
         * align - TextAlign.Unspecified
         *
         * weight - FontWeight.Normal
         */
        val Default: TextConfig = TextConfig()

        /**
         * size - 16
         *
         * color - null
         *
         * align - TextAlign.Center
         *
         * weight - FontWeight.Medium
         */
        val CenteredMedium = TextConfig(align = TextAlign.Center, weight = FontWeight.Medium)

        /**
         * size - 14
         *
         * color - null
         *
         * align - TextAlign.Unspecified
         *
         * weight - FontWeight.Normal
         */
        val Small = TextConfig(size = 14)

        /**
         * size - 14
         *
         * color - null
         *
         * align - TextAlign.Unspecified
         *
         * weight - FontWeight.Medium
         */
        val SmallMedium = TextConfig(size = 14, weight = FontWeight.Medium)

        /**
         * size - 14
         *
         * color - null
         *
         * align - TextAlign.Center
         *
         * weight - FontWeight.Medium
         */
        val SmallCenteredMedium =
            TextConfig(size = 14, align = TextAlign.Center, weight = FontWeight.Medium)
    }
}

@Immutable
class IconConfig(val description: String = "", val size: Int = 25, val color: Color? = null) {
    companion object {
        val Big = IconConfig(size = 22)

        val Default = IconConfig()

        val Small = IconConfig(size = 20)
    }
}

@Immutable class IconWithConfig(val icon: ImageVector, val config: IconConfig)

data class Error(val value: Boolean = false, val text: String = "")
