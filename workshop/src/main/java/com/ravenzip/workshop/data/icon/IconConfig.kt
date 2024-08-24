package com.ravenzip.workshop.data.icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
class IconConfig(val description: String = "", val size: Int = 25, val color: Color? = null) {
    companion object {
        /**
         * description - ""
         *
         * size - 22
         *
         * color - null
         */
        val Big = IconConfig(size = 22)

        /**
         * description - ""
         *
         * size - 22
         *
         * color - MaterialTheme.colorScheme.primary
         */
        val PrimaryBig: IconConfig
            @Composable get() = IconConfig(color = MaterialTheme.colorScheme.primary, size = 22)

        /**
         * description - ""
         *
         * size - 25
         *
         * color - null
         */
        val Default = IconConfig()

        /**
         * description - ""
         *
         * size - 22
         *
         * color - null
         */
        val Small = IconConfig(size = 20)

        /**
         * description - ""
         *
         * size - 25
         *
         * color - MaterialTheme.colorScheme.primary
         */
        val Primary: IconConfig
            @Composable get() = IconConfig(color = MaterialTheme.colorScheme.primary)

        /**
         * description - ""
         *
         * size - 20
         *
         * color - MaterialTheme.colorScheme.primary
         */
        val PrimarySmall: IconConfig
            @Composable get() = IconConfig(color = MaterialTheme.colorScheme.primary, size = 20)
    }
}
