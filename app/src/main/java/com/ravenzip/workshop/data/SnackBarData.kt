package com.ravenzip.workshop.data

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals

enum class SnackBarType {
    Info,
    Warning,
    Error
}

class SnackBarVisualsCustom(
    override val message: String,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = false,
    override val duration: SnackbarDuration =
        if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
    val type: SnackBarType
) : SnackbarVisuals