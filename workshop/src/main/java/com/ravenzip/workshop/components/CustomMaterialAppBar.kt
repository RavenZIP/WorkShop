package com.ravenzip.workshop.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ravenzip.workshop.data.AppBarButton
import com.ravenzip.workshop.data.AppBarMenuItem
import com.ravenzip.workshop.data.BottomItemsTextState
import com.ravenzip.workshop.data.BottomNavigationItem
import com.ravenzip.workshop.data.IconParameters

/**
 * TopAppBar
 *
 * Параметры:
 * 1) text - текст (обязательный)
 * 2) backArrow - кнопка назад (по умолчанию false, не обязательный)
 * 3) items - кнопки (по умолчанию пустой список, не обязательный)
 * 4) isMenu - выбор расположения кнопок: в меню или ряд на панели (по умолчанию false, не
 *    обязательный)
 * 5) backArrowClick - действие при нажатии на кнопку назад (не обязательный, по умолчанию действие
 *    не назначено)
 */
@Composable
fun TopAppBar(
    text: String,
    backArrow: Boolean = false,
    items: List<AppBarButton> = listOf(),
    isMenu: Boolean = false,
    backArrowClick: () -> Unit = {}
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
                    icon =
                        IconParameters(Icons.AutoMirrored.Outlined.ArrowBack, description = "Назад")
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
            if (items.isNotEmpty()) {
                if (items.count() <= 3 && !isMenu) {
                    items.forEachIndexed { index, button ->
                        AppBarIconButton(icon = button.icon as IconParameters) { button.onClick() }
                        if (index != 2) Spacer(modifier = Modifier.padding(start = 5.dp))
                    }
                } else {
                    val expanded = remember { mutableStateOf(false) }
                    AppBarMenuIconButton(expanded = expanded, menuItems = items) {
                        expanded.value = true
                    }
                }
            }
        }
    }
}

@Composable
private fun AppBarIconButton(icon: IconParameters, onClick: () -> Unit) {
    Box(
        modifier = Modifier.size(40.dp).clip(RoundedCornerShape(15)).clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon.value,
            contentDescription = icon.description,
            modifier = Modifier.size(icon.size.dp),
            tint = icon.color ?: MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun AppBarMenuIconButton(
    expanded: MutableState<Boolean>,
    menuItems: List<AppBarButton>,
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
                it as AppBarMenuItem
                if (it.text !== "") {
                    DropdownMenuItem(
                        text = { Text(it.text) },
                        onClick = {
                            it.onClick()
                            // expanded.value = false
                        },
                        modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                        leadingIcon = {
                            Icon(
                                imageVector = it.icon.value,
                                contentDescription = it.icon.description,
                                modifier = Modifier.size(it.icon.size.dp),
                                tint =
                                    (it.icon as IconParameters).color
                                        ?: MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        },
                        enabled = it.enabled,
                        colors = if (it.colors !== null) it.colors else MenuDefaults.itemColors(),
                        contentPadding = PaddingValues(15.dp)
                    )
                    if (index != lastItem) {
                        Spacer(modifier = Modifier.padding(bottom = 5.dp))
                    }
                }
            }
        }
    }
}

/**
 * [BottomNavigationBar] - Нижняя навигационная панель
 *
 * @param navController навигационный контроллер (обязательный)
 * @param buttonsList список кнопок (обязательный)
 * @param showLabelOnlyOnSelected отображать название только на активном экране (по умолчанию false,
 * не обязательный)
 */
@Composable
fun BottomNavigationBar(
    navController: NavController,
    buttonsList: List<BottomNavigationItem>,
    showLabelOnlyOnSelected: Boolean = false
) {
    val labelState = getLabelState(showLabelOnlyOnSelected, buttonsList.count())
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceContainer) {
        Row(
            modifier =
                Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = getBottomBarTopPadding(labelState)
                    )
                    .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            buttonsList.forEach {
                NavigationBarItem(
                    navController = navController,
                    currentDestination = currentDestination,
                    item = it,
                    labelState = labelState
                )
            }
        }
    }
}

@Composable
private fun NavigationBarItem(
    navController: NavController,
    currentDestination: NavDestination?,
    item: BottomNavigationItem,
    labelState: BottomItemsTextState
) {
    val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
    val background = getBoxColor(selected)
    val tint = getIconColor(selected)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        BadgedBox(badge = { item.GetBadge() }) {
            Box(
                modifier =
                    Modifier.size(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(background)
                        .clickable { item.click(navController = navController) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon.value,
                    contentDescription = item.label,
                    modifier = Modifier.size(item.icon.size.dp),
                    tint = tint
                )
            }
        }
        if (item.showLabel(navController = navController, labelState = labelState)) {
            Text(
                text = item.label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp,
                color = tint
            )
        }
    }
}

@Composable
private fun getBoxColor(selected: Boolean): Color {
    return if (selected) MaterialTheme.colorScheme.secondaryContainer
    else MaterialTheme.colorScheme.surfaceContainer
}

@Composable
private fun getIconColor(selected: Boolean): Color {
    return if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
}

@Composable
private fun BottomNavigationItem.GetBadge() {
    if (this.badgeCount != null) {
        val badgeCount = this.badgeCount
        return Badge { Text(text = badgeCount.toString()) }
    } else if (this.hasNews) {
        return Badge()
    }
}

private fun BottomNavigationItem.click(navController: NavController) {
    navController.navigate(this.route) {
        navController.graph.startDestinationRoute?.let { route ->
            popUpTo(route) { saveState = true }
        }
        // Отключаем возможность роутинга в одно и тоже место при
        // неоднократном нажатии
        launchSingleTop = true
        restoreState = true
    }
}

private fun getLabelState(
    showLabelOnlyOnSelected: Boolean,
    buttonsCount: Int
): BottomItemsTextState {
    return if (showLabelOnlyOnSelected) BottomItemsTextState.ONLY_SELECTED
    else if (buttonsCount <= 3) BottomItemsTextState.SHOW_ALL else BottomItemsTextState.HIDDEN
}

private fun BottomNavigationItem.showLabel(
    navController: NavController,
    labelState: BottomItemsTextState
): Boolean {
    return labelState === BottomItemsTextState.SHOW_ALL ||
        labelState === BottomItemsTextState.ONLY_SELECTED &&
            navController.currentDestination?.route == this.route
}

private fun getBottomBarTopPadding(labelState: BottomItemsTextState): Dp {
    return if (labelState !== BottomItemsTextState.HIDDEN) 20.dp else 0.dp
}
