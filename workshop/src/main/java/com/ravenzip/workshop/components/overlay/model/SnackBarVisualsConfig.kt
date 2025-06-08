package com.ravenzip.workshop.components.overlay.model

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import com.ravenzip.workshop.model.icon.IconConfig
import com.ravenzip.workshop.model.icon.IconData

class SnackBarVisualsConfig(
    override val message: String,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = false,
    override val duration: SnackbarDuration =
        if (actionLabel == null) SnackbarDuration.Short else SnackbarDuration.Indefinite,
    val icon: IconData,
    val iconConfig: IconConfig,
) : SnackbarVisuals