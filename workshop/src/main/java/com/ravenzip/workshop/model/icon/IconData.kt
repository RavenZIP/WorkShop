package com.ravenzip.workshop.model.icon

import androidx.compose.ui.graphics.vector.ImageVector

sealed class IconData {
    data class ImageVectorIcon(val imageVector: ImageVector) : IconData()

    data class ResourceIcon(val id: Int) : IconData()
}
