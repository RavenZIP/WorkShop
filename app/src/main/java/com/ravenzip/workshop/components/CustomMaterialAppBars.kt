package com.ravenzip.workshop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * TopAppBar
 *
 * Параметры:
 */
@Composable
fun TopAppBar(
    text: String,
    backArrow: Boolean = false,
    rightButton: Boolean = false,
    rightButtonIcon: ImageVector? = null,
    backArrowClick: () -> Unit,
    rightButtonClick: () -> Unit
) {
    Box(
        Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surfaceContainer),
        Alignment.Center
    ) {
        Row(
            modifier =
                Modifier.fillMaxWidth(0.9f).padding(top = 10.dp, bottom = 10.dp).height(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (backArrow) {
                AppBarIconButton(Icons.AutoMirrored.Outlined.ArrowBack) { backArrowClick() }
                Spacer(modifier = Modifier.weight(0.1f))
            }
            Text(
                text = text,
                fontSize = 23.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.5.sp
            )
            if (rightButton && rightButtonIcon !== null) {
                Spacer(modifier = Modifier.weight(if (backArrow) 0.9f else 1f))
                AppBarIconButton(rightButtonIcon) { rightButtonClick() }
            }
        }
    }
}

@Composable
private fun AppBarIconButton(icon: ImageVector, onClick: () -> Unit) {
    Box(
        modifier = Modifier.size(40.dp).clip(RoundedCornerShape(15)).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = icon, contentDescription = "", modifier = Modifier.size(25.dp))
    }
}

/**
 * BottomAppBar
 *
 * Параметры:
 */
@Composable
fun BottomAppBar() {
    NavigationBar {
        //
    }
}
