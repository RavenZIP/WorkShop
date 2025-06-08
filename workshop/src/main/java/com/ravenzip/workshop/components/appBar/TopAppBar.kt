package com.ravenzip.workshop.components.appBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.components.appBar.shared.AppBarButton
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.appbar.AppBarItem
import com.ravenzip.workshop.data.appbar.BackArrow

/**
 * [TopAppBar] - Верхняя панель
 *
 * @param title текст
 * @param titleConfig параметры текста
 * @param backgroundColor фоновый цвет верхней панели
 * @param backArrow кнопка назад
 * @param items кнопки
 */
@Composable
fun TopAppBar(
    title: String,
    titleConfig: TextConfig = TextConfig.TopAppBar,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    backArrow: BackArrow? = null,
    items: List<AppBarItem> = listOf(),
) {
    val lastItem = items.count() - 1

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
                AppBarButton(icon = backArrow.icon, backArrow.iconConfig) { backArrow.onClick() }

                Spacer(modifier = Modifier.weight(0.1f))
            }

            Text(
                text = title,
                fontSize = titleConfig.size,
                fontWeight = titleConfig.weight,
                letterSpacing = titleConfig.letterSpacing,
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
