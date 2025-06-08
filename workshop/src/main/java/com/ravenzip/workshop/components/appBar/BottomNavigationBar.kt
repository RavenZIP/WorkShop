package com.ravenzip.workshop.components.appBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.ravenzip.workshop.components.icon.Icon
import com.ravenzip.workshop.data.appbar.BottomItemsTextStateEnum
import com.ravenzip.workshop.data.appbar.BottomNavigationItem
import kotlin.collections.forEach

/**
 * [BottomNavigationBar] - Нижняя навигационная панель
 *
 * @param navController навигационный контроллер
 * @param buttonsList список кнопок
 * @param showLabelOnlyOnSelected отображать название только на активном экране
 */
@Composable
fun BottomNavigationBar(
    navController: NavController,
    buttonsList: List<BottomNavigationItem>,
    showLabelOnlyOnSelected: Boolean = false,
) {
    val labelState = calculateLabelState(showLabelOnlyOnSelected, buttonsList.count())
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceContainer) {
        Row(
            modifier =
                Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = calculateBottomBarTopPadding(labelState),
                    )
                    .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            buttonsList.forEach {
                NavigationBarItem(
                    navController = navController,
                    currentDestination = currentDestination,
                    item = it,
                    labelState = labelState,
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
    labelState: BottomItemsTextStateEnum,
) {
    val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
    val background = calculateBoxColor(selected)
    val tint = calculateIconColor(selected)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        BadgedBox(badge = { item.GetBadge() }) {
            Box(
                modifier =
                    Modifier.size(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(background)
                        .clickable { item.onClick(navController = navController) },
                contentAlignment = Alignment.Center,
            ) {
                Icon(icon = item.icon, iconConfig = item.iconConfig, defaultColor = tint)
            }
        }

        if (item.showLabel(navController = navController, labelState = labelState)) {
            Text(
                text = item.label,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.sp,
                color = tint,
            )
        }
    }
}

@Composable
private fun calculateBoxColor(selected: Boolean): Color {
    return if (selected) MaterialTheme.colorScheme.secondaryContainer
    else MaterialTheme.colorScheme.surfaceContainer
}

@Composable
private fun calculateIconColor(selected: Boolean): Color {
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

private fun BottomNavigationItem.onClick(navController: NavController) {
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

private fun calculateLabelState(
    showLabelOnlyOnSelected: Boolean,
    buttonsCount: Int,
): BottomItemsTextStateEnum {
    return if (showLabelOnlyOnSelected) BottomItemsTextStateEnum.ONLY_SELECTED
    else if (buttonsCount <= 3) BottomItemsTextStateEnum.SHOW_ALL
    else BottomItemsTextStateEnum.HIDDEN
}

private fun BottomNavigationItem.showLabel(
    navController: NavController,
    labelState: BottomItemsTextStateEnum,
): Boolean {
    return labelState === BottomItemsTextStateEnum.SHOW_ALL ||
        labelState === BottomItemsTextStateEnum.ONLY_SELECTED &&
            navController.currentDestination?.route == this.route
}

private fun calculateBottomBarTopPadding(labelState: BottomItemsTextStateEnum): Dp {
    return if (labelState !== BottomItemsTextStateEnum.HIDDEN) 20.dp else 0.dp
}
