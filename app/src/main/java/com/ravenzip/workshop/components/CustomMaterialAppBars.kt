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
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class SquareIconButton(
    val description: String,
    val color: Color,
    val onClick: () -> Unit
)

data class IconButton (
    val icon: ImageVector,
    val description: String,
    val iconColor: Color,
    val text: String,
    val onClick: () -> Unit
)

/**
 * TopAppBar
 *
 * Параметры:
 * 1) Текст (обязательный)
 * 2) Кнопка назад (по умолчанию false, не обязательный)
 * 3) Кнопки справа (по умолчанию null, не обязательный, максимум 3 кнопки)
 * 4) Действие при нажатии на кнопку назад (не обязательный, по умолчанию действие не назначено)
 */
@Composable
fun TopAppBar(
    text: String,
    backArrow: Boolean = false,
    buttonsList: List<SquareIconButton>? = null,
    backArrowClick: () -> Unit = {},
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
                AppBarIconButton(Icons.AutoMirrored.Outlined.ArrowBack, "Назад", Color.Black) {
                    backArrowClick()
                }
                Spacer(modifier = Modifier.weight(0.1f))
            }
            Text(
                text = text,
                fontSize = 23.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.5.sp
            )
            Spacer(modifier = Modifier.weight(if (backArrow) 0.9f else 1f))
            if (buttonsList !== null && buttonsList.isNotEmpty() && buttonsList.count() <= 3) {
                buttonsList.forEachIndexed { index, button ->
                    AppBarIconButton(button.icon, button.description, button.color) {
                        button.onClick()
                    }
                    if (index != 2) {
                        Spacer(modifier = Modifier.padding(start = 5.dp))
                    }
                }
            }
        }
    }
}

/**
 * TopAppBar (с меню)
 *
 * Параметры:
 */
@Composable
fun TopAppBarWithMenu(
    text: String,
    backArrow: Boolean = false,
    menuItems: List<IconButton>,
    backArrowClick: () -> Unit
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
                AppBarIconButton(Icons.AutoMirrored.Outlined.ArrowBack, "", Color.Black) { backArrowClick() }
                Spacer(modifier = Modifier.weight(0.1f))
            }
            Text(
                text = text,
                fontSize = 23.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.5.sp
            )
            Spacer(modifier = Modifier.weight(if (backArrow) 0.9f else 1f))
            AppBarIconButton(Icons.Outlined.MoreVert, "Menu", Color.Black) {  }
        }
    }
}

@Composable
private fun AppBarIconButton(
    icon: ImageVector,
    description: String,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.size(40.dp).clip(RoundedCornerShape(15)).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier.size(25.dp),
            tint = color
        )
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
