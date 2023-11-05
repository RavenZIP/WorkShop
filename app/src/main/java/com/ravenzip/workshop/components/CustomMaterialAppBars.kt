package com.ravenzip.workshop.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

data class TopNavigationItem(
    val icon: ImageVector,
    val description: String,
    val onClick: () -> Unit
)

data class TopNavigationItemMenu(
    val icon: ImageVector,
    val description: String,
    val text: String,
    val colors: MenuItemColors,
    val enabled: Boolean,
    val onClick: () -> Unit
)

data class BottomNavigationItem(
    val text: String,
    val icon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
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
    buttonsList: List<TopNavigationItem>? = null,
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
                AppBarIconButton(
                    icon = Icons.AutoMirrored.Outlined.ArrowBack,
                    description = "Назад",
                ) {
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
                    AppBarIconButton(icon = button.icon, description = button.description) {
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
 * 1) Текст (обязательный)
 * 2) Кнопка назад (по умолчанию false, не обязательный)
 * 3) Список элементов меню (обязательный)
 * 4) Действие при нажатии на кнопку назад (не обязательный, по умолчанию действие не назначено)
 */
@SuppressLint("UnrememberedMutableState")
@Composable
fun TopAppBarWithMenu(
    text: String,
    backArrow: Boolean = false,
    menuItems: List<TopNavigationItemMenu>,
    backArrowClick: () -> Unit
) {
    val expanded = mutableStateOf(false)
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
                AppBarIconButton(
                    icon = Icons.AutoMirrored.Outlined.ArrowBack,
                    description = "",
                ) {
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
            AppBarMenuIconButton(expanded = expanded, menuItems = menuItems) {
                expanded.value = true
            }
        }
    }
}

@Composable
private fun AppBarIconButton(icon: ImageVector, description: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier.size(40.dp).clip(RoundedCornerShape(15)).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier.size(25.dp),
        )
    }
}

@Composable
private fun AppBarMenuIconButton(
    expanded: MutableState<Boolean>,
    menuItems: List<TopNavigationItemMenu>,
    onClick: () -> Unit
) {
    val lastItem = menuItems.count() - 1
    Box(
        modifier = Modifier.size(40.dp).clip(RoundedCornerShape(15)).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = "Меню",
            modifier = Modifier.size(25.dp),
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            Modifier.padding(start = 10.dp, top = 2.5.dp, end = 10.dp, bottom = 2.5.dp)
        ) {
            menuItems.forEachIndexed { index, it ->
                DropdownMenuItem(
                    text = { Text(it.text) },
                    onClick = {
                        it.onClick()
                        // expanded.value = false
                    },
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                    leadingIcon = { Icon(imageVector = it.icon, contentDescription = it.text) },
                    enabled = it.enabled,
                    colors = it.colors,
                    contentPadding = PaddingValues(15.dp)
                )
                if (index != lastItem) {
                    Spacer(modifier = Modifier.padding(bottom = 5.dp))
                }
            }
        }
    }
}

/**
 * BottomAppBar
 *
 * Параметры:
 *
 * 1)
 * 2)
 */
@Composable
fun BottomAppBar(navController: NavController, buttonsList: List<BottomNavigationItem>) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceContainer) {
        Row(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            buttonsList.forEach { NavigationBarItem(navController = navController, item = it) }
        }
    }
}

@Composable
private fun NavigationBarItem(navController: NavController, item: BottomNavigationItem) {
    val selected = navController.currentDestination?.route == item.text
    // Цвета экспериментальные, потребуется доработка
    val background =
        if (selected) MaterialTheme.colorScheme.secondaryContainer
        else MaterialTheme.colorScheme.surfaceContainer
    val tint = if (selected) MaterialTheme.colorScheme.secondary else Color.Black
    BadgedBox(
        badge = {
            if (item.badgeCount != null) {
                Badge { Text(text = item.badgeCount.toString()) }
            } else if (item.hasNews) {
                Badge()
            }
        }
    ) {
        Box(
            modifier =
                Modifier.size(45.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(background)
                    .clickable {
                        navController.navigate(item.text) {
                            // Разобраться надо ли это делать :D
                            // Просто перетянул из старого проекта
                            popUpTo(navController.graph.findStartDestination().navigatorName) {
                                saveState = true
                            }
                            // Отключаем возможность роутинга в одно и тоже место при неоднократном
                            // нажатии
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.text,
                modifier = Modifier.size(25.dp),
                tint = tint
            )
        }
    }
}
