package com.ravenzip.workshop.data.icon

import androidx.compose.ui.graphics.vector.ImageVector

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()

    data class ResourceIcon(val id: Int) : Icon()
}
