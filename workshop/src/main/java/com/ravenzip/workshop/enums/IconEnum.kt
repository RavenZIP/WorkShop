package com.ravenzip.workshop.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import com.ravenzip.workshop.data.IconParameters

internal enum class IconEnum(val value: IconParameters) {
    EMAIL(IconParameters(value = Icons.Outlined.Email)),
    SETTINGS(IconParameters(value = Icons.Outlined.Settings)),
    INFO(IconParameters(value = Icons.Outlined.Info))
}
