package com.ravenzip.workshop.components.appBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.components.appBar.shared.AppBarButton
import com.ravenzip.workshop.components.icon.Icon
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.appbar.AppBarMenuItem
import com.ravenzip.workshop.data.appbar.BackArrow

/**
 * [TopAppBarWithMenu] - Верхняя панель, в которой кнопки спрятаны в меню
 *
 * @param title текст
 * @param titleConfig параметры текста
 * @param backgroundColor фоновый цвет верхней панели
 * @param backArrow кнопка назад
 * @param items кнопки
 */
@Composable
fun TopAppBarWithMenu(
    title: String,
    titleConfig: TextConfig = TextConfig.TopAppBar,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    backArrow: BackArrow? = null,
    items: List<AppBarMenuItem> = listOf(),
) {
    val expanded = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth().background(backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
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
                fontSize = titleConfig.size,
                fontWeight = titleConfig.weight,
                letterSpacing = titleConfig.letterSpacing,
            )

            Spacer(modifier = Modifier.weight(if (backArrow !== null) 0.9f else 1f))

            AppBarMenu(expanded = expanded, menuItems = items) { expanded.value = true }
        }
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
                    text = {
                        Text(
                            text = menuItem.text,
                            fontSize = menuItem.textConfig.size,
                            fontWeight = menuItem.textConfig.weight,
                            letterSpacing = menuItem.textConfig.letterSpacing,
                        )
                    },
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
                    colors = menuItem.colors ?: MenuDefaults.itemColors(),
                    contentPadding = PaddingValues(15.dp),
                )
                if (index != lastItem) Spacer(modifier = Modifier.padding(bottom = 5.dp))
            }
        }
    }
}
