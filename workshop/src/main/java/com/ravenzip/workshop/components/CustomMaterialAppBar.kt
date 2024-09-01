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
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.material.icons.Icons
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
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import com.ravenzip.workshop.data.appbar.AppBarItem
import com.ravenzip.workshop.data.appbar.AppBarMenuItem
import com.ravenzip.workshop.data.appbar.BackArrow
import com.ravenzip.workshop.data.appbar.BottomItemsTextStateEnum
import com.ravenzip.workshop.data.appbar.BottomNavigationItem
import com.ravenzip.workshop.data.icon.Icon
import com.ravenzip.workshop.data.icon.IconConfig

/**
 * [TopAppBar] - Верхняя панель
 *
 * @param title текст
 * @param backgroundColor фоновый цвет верхней панели
 * @param backArrow кнопка назад
 * @param items кнопки
 */
@Composable
fun TopAppBar(
    title: String,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    backArrow: BackArrow? = null,
    items: List<AppBarItem> = listOf(),
) {
    val lastItem = items.count() - 1

    Box(Modifier.fillMaxWidth().background(backgroundColor), Alignment.Center) {
        Row(
            modifier =
                Modifier.fillMaxWidth(0.9f).padding(top = 10.dp, bottom = 10.dp).height(40.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (backArrow !== null) {
                AppBarButton(icon = backArrow.icon, backArrow.iconConfig) { backArrow.onClick() }
                Spacer(modifier = Modifier.weight(0.1f))
            }

            Text(
                text = title,
                fontSize = 23.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.5.sp,
            )
            Spacer(modifier = Modifier.weight(if (backArrow !== null) 0.9f else 1f))

            items.forEachIndexed { index, button ->
                AppBarButton(icon = button.icon, iconConfig = button.iconConfig) {
                    button.onClick()
                }
                if (index != lastItem) Spacer(modifier = Modifier.padding(start = 5.dp))
            }
        }
    }
}

/**
 * [TopAppBarWithMenu] - Верхняя панель, в которой кнопки спрятаны в меню
 *
 * @param title текст
 * @param backgroundColor фоновый цвет верхней панели
 * @param backArrow кнопка назад
 * @param items кнопки
 */
@Composable
fun TopAppBarWithMenu(
    title: String,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    backArrow: BackArrow? = null,
    items: List<AppBarMenuItem> = listOf(),
) {
    val expanded = remember { mutableStateOf(false) }

    Box(Modifier.fillMaxWidth().background(backgroundColor), Alignment.Center) {
        Row(
            modifier =
                Modifier.fillMaxWidth(0.9f).padding(top = 10.dp, bottom = 10.dp).height(40.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (backArrow !== null) {
                AppBarButton(icon = backArrow.icon, iconConfig = backArrow.iconConfig) {
                    backArrow.onClick()
                }
                Spacer(modifier = Modifier.weight(0.1f))
            }

            Text(
                text = title,
                fontSize = 23.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.5.sp,
            )
            Spacer(modifier = Modifier.weight(if (backArrow !== null) 0.9f else 1f))

            AppBarMenu(expanded = expanded, menuItems = items) { expanded.value = true }
        }
    }
}

/**
 * [SearchBar] - Верхняя панель с поиском
 *
 * @param query запрос
 * @param placeholder временный текст
 * @param onSearch действие при нажатии на кнопку поиска
 * @param backgroundColor фоновый цвет верхней панели,
 * @param textFieldColors цвета текстового поля
 */
@Composable
fun SearchBar(
    query: MutableState<String>,
    placeholder: String?,
    onSearch: (KeyboardActionScope.() -> Unit)?,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    textFieldColors: TextFieldColors =
        TextFieldDefaults.colors(
            focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
) {
    Box(Modifier.fillMaxWidth().background(backgroundColor), Alignment.Center) {
        Column {
            Spacer(modifier = Modifier.padding(top = 10.dp))
            SearchBarTextField(
                text = query,
                placeholder = placeholder,
                onSearch = onSearch,
                colors = textFieldColors,
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
        }
    }
}

@Composable
private fun AppBarButton(icon: Icon, iconConfig: IconConfig, onClick: () -> Unit) {
    Box(
        modifier = Modifier.size(40.dp).clip(RoundedCornerShape(15)).clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            icon = icon,
            iconConfig = iconConfig,
            defaultColor = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
private fun AppBarMenu(
    expanded: MutableState<Boolean>,
    menuItems: List<AppBarMenuItem>,
    onClick: () -> Unit,
) {
    val lastItem = menuItems.count() - 1
    Box(
        modifier = Modifier.size(40.dp).clip(RoundedCornerShape(15)).clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = "Меню",
            modifier = Modifier.size(25.dp),
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            Modifier.padding(start = 10.dp, top = 2.5.dp, end = 10.dp, bottom = 2.5.dp),
        ) {
            menuItems.forEachIndexed { index, menuItem ->
                DropdownMenuItem(
                    text = { Text(menuItem.text) },
                    onClick = {
                        menuItem.onClick()
                        expanded.value = false
                    },
                    modifier = Modifier.clip(RoundedCornerShape(10.dp)),
                    leadingIcon = {
                        Icon(
                            icon = menuItem.icon,
                            iconConfig = menuItem.iconConfig,
                            defaultColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    },
                    enabled = menuItem.enabled,
                    colors =
                        if (menuItem.colors !== null) menuItem.colors
                        else MenuDefaults.itemColors(),
                    contentPadding = PaddingValues(15.dp),
                )
                if (index != lastItem) Spacer(modifier = Modifier.padding(bottom = 5.dp))
            }
        }
    }
}

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
    val labelState = getLabelState(showLabelOnlyOnSelected, buttonsList.count())
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceContainer) {
        Row(
            modifier =
                Modifier.padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = getBottomBarTopPadding(labelState),
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

private fun getBottomBarTopPadding(labelState: BottomItemsTextStateEnum): Dp {
    return if (labelState !== BottomItemsTextStateEnum.HIDDEN) 20.dp else 0.dp
}
