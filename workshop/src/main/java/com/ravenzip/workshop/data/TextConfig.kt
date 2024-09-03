package com.ravenzip.workshop.data

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
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
    val letterSpacing: TextUnit = TextUnit.Unspecified,
) {
    companion object {
        /**
         * [ExtraSmall]
         *
         * [size] - 12.sp
         *
         * [color] - null
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.Normal
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable val S12 = TextConfig(size = 12.sp)

        /**
         * [Small]
         *
         * [size] - 14.sp
         *
         * [color] - null
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.Normal
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable val S14 = TextConfig(size = 14.sp)

        /**
         * [Normal]
         *
         * [size] - 16.sp
         *
         * [color] - null
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.Normal
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable val S16 = TextConfig(size = 16.sp)

        /**
         * [MoreThanNormal]
         *
         * [size] - 18.sp
         *
         * [color] - null
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.Normal
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable val S18 = TextConfig(size = 18.sp)

        /**
         * [Big]
         *
         * [size] - 20.sp
         *
         * [color] - null
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.Normal
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable val S20 = TextConfig(size = 20.sp)

        /**
         * [ExtraBig]
         *
         * [size] - 22.sp
         *
         * [color] - null
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.Normal
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable val S22 = TextConfig(size = 22.sp)

        /** Псевдоним для [S12] */
        @Stable val ExtraSmall = S12

        /** Псевдоним для [S14] */
        @Stable val Small = S14

        /** Псевдоним для [S16] */
        @Stable val Normal = S16

        /** Псевдоним для [S18] */
        @Stable val MoreThanNormal = S18

        /** Псевдоним для [S20] */
        @Stable val Big = S20

        /** Псевдоним для [S22] */
        @Stable val ExtraBig = S22

        /**
         * [size] - 22.sp
         *
         * [color] - null
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.Medium
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable val H1 = TextConfig(size = 22.sp, weight = FontWeight.Medium)

        /**
         * [size] - 20.sp
         *
         * [color] - null
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.Medium
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable val H2 = TextConfig(size = 20.sp, weight = FontWeight.Medium)

        /**
         * [size] - 18.sp
         *
         * [color] - null
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.Medium
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable val H3 = TextConfig(size = 18.sp, weight = FontWeight.Medium)

        /**
         * [size] - 18
         *
         * [color] - MaterialTheme.colorScheme.onSurface
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.Medium
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable
        val onSurfaceH2
            @Composable
            get() =
                TextConfig(
                    size = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    weight = FontWeight.Medium,
                )

        /**
         * [size] - 16
         *
         * [color] - null
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.Normal
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable val Default = TextConfig()

        /**
         * [size] - 16
         *
         * [color] - null
         *
         * [align] - TextAlign.Center
         *
         * [weight] - FontWeight.Medium
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable
        val CenteredMedium = TextConfig(align = TextAlign.Center, weight = FontWeight.Medium)

        /**
         * [size] - 14
         *
         * [color] - null
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.Medium
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable val SmallMedium = TextConfig(size = 14.sp, weight = FontWeight.Medium)

        /**
         * [size] - 14
         *
         * [color] - null
         *
         * [align] - TextAlign.Center
         *
         * [weight] - FontWeight.Medium
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable
        val SmallCenteredMedium =
            TextConfig(size = 14.sp, align = TextAlign.Center, weight = FontWeight.Medium)

        /**
         * [size] - 14.sp
         *
         * [color] - MaterialTheme.colorScheme.onSurface с прозрачностью 85%
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.Normal
         *
         * [letterSpacing] - TextUnit.Unspecified
         */
        @Stable
        val onSurface85Small
            @Composable
            get() = TextConfig(size = 14.sp, color = MaterialTheme.colorScheme.onSurface)

        // Далее идут наименования стилей для элементов в библиотеке, недоступные публично

        /**
         * [size] - 14.sp
         *
         * [color] - MaterialTheme.colorScheme.onSurface с прозрачностью 85%
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.W500
         *
         * [letterSpacing] - 0.sp
         */
        @Stable
        internal val Chip = TextConfig(size = 14.sp, weight = FontWeight.W500, letterSpacing = 0.sp)

        /**
         * [size] - 23.sp
         *
         * [color] - null
         *
         * [align] - TextAlign.Unspecified
         *
         * [weight] - FontWeight.Medium
         *
         * [letterSpacing] - 1.5.sp
         */
        @Stable
        internal val TopAppBar =
            TextConfig(size = 23.sp, weight = FontWeight.Medium, letterSpacing = 1.5.sp)
    }
}
