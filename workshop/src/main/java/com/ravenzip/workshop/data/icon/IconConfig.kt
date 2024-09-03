package com.ravenzip.workshop.data.icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

@Immutable
class IconConfig(val description: String = "", val size: Int = 25, val color: Color? = null) {
    companion object {
        /**
         * [Big]
         *
         * [description] - ""
         *
         * [size] - 22
         *
         * [color] - null
         */
        @Stable val Big = IconConfig(size = 22)

        /**
         * [PrimaryBig]
         *
         * [description] - ""
         *
         * [size] - 22
         *
         * [color] - MaterialTheme.colorScheme.primary
         */
        @Stable
        val PrimaryBig
            @Composable get() = IconConfig(color = MaterialTheme.colorScheme.primary, size = 22)

        /**
         * [Default]
         *
         * [description] - ""
         *
         * [size] - 25
         *
         * [color] - null
         */
        @Stable val Default = IconConfig()

        /**
         * [Small]
         *
         * [description] - ""
         *
         * [size] - 22
         *
         * [color] - null
         */
        @Stable val Small = IconConfig(size = 20)

        /**
         * [Primary]
         *
         * [description] - ""
         *
         * [size] - 25
         *
         * [color] - MaterialTheme.colorScheme.primary
         */
        @Stable
        val Primary
            @Composable get() = IconConfig(color = MaterialTheme.colorScheme.primary)

        /**
         * [PrimarySmall]
         *
         * [description] - ""
         *
         * [size] - 20
         *
         * [color] - MaterialTheme.colorScheme.primary
         */
        @Stable
        val PrimarySmall
            @Composable get() = IconConfig(color = MaterialTheme.colorScheme.primary, size = 20)
    }
}
