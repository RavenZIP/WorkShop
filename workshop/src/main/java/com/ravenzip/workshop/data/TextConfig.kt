package com.ravenzip.workshop.data

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Immutable
class TextConfig(
    val size: TextUnit = TextUnit.Unspecified,
    val color: Color? = null,
    val align: TextAlign = TextAlign.Unspecified,
    val weight: FontWeight = FontWeight.Normal,
    val letterString: TextUnit = TextUnit.Unspecified,
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
        val H1 = TextConfig(size = 22.sp, weight = FontWeight.Medium)

        /**
         * size - 18
         *
         * color - null
         *
         * align - TextAlign.Unspecified
         *
         * weight - FontWeight.Medium
         */
        val H2 = TextConfig(size = 18.sp, weight = FontWeight.Medium)

        /**
         * size - 18
         *
         * color - MaterialTheme.colorScheme.onSurface
         *
         * align - TextAlign.Unspecified
         *
         * weight - FontWeight.Medium
         */
        val onSurfaceH2: TextConfig
            @Composable
            get() =
                TextConfig(
                    size = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    weight = FontWeight.Medium,
                )

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
        val Small = TextConfig(size = 14.sp)

        /**
         * size - 14
         *
         * color - null
         *
         * align - TextAlign.Unspecified
         *
         * weight - FontWeight.Medium
         */
        val SmallMedium = TextConfig(size = 14.sp, weight = FontWeight.Medium)

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
            TextConfig(size = 14.sp, align = TextAlign.Center, weight = FontWeight.Medium)

        /**
         * size - 14
         *
         * color - MaterialTheme.colorScheme.onSurface с прозрачностью 85%
         *
         * align - TextAlign.Unspecified
         *
         * weight - FontWeight.Normal
         */
        val onSurface85Small: TextConfig
            @Composable
            get() = TextConfig(size = 14.sp, color = MaterialTheme.colorScheme.onSurface)
    }
}
