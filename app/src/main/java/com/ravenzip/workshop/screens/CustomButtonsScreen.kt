package com.ravenzip.workshop.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ravenzip.workshop.components.BottomAppBar
import com.ravenzip.workshop.components.ColIconButton
import com.ravenzip.workshop.components.CustomButton
import com.ravenzip.workshop.components.RowIconButton
import com.ravenzip.workshop.components.SimpleButton
import com.ravenzip.workshop.components.TopAppBar
import com.ravenzip.workshop.data.AppBarMenuItem
import com.ravenzip.workshop.data.IconParameters
import com.ravenzip.workshop.data.TextParameters

@Composable
fun CustomButtonsScreen(navController: NavController) {
    val menuItems =
        listOf(
            AppBarMenuItem(
                icon = IconParameters(Icons.Outlined.Add),
                text = "Добавить",
                onClick = {}
            ),
            AppBarMenuItem(
                icon = IconParameters(Icons.Outlined.Edit),
                text = "Редактировать",
                onClick = {}
            ),
            AppBarMenuItem(
                icon = IconParameters(Icons.Outlined.Delete),
                text = "Удалить",
                onClick = {}
            ),
        )
    Scaffold(
        topBar = {
            TopAppBar(text = "Кнопки", backArrow = true, items = menuItems, isMenu = true) {
                navController.popBackStack()
            }
        },
        bottomBar = { BottomAppBar(navController = navController, buttonsList = bottomBarButtons) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 30.dp))

            SimpleButton(
                width = null,
                text = TextParameters(value = "Простая кнопка", size = 18),
                textAlign = TextAlign.Start
            ) {}

            Spacer(modifier = Modifier.padding(top = 20.dp))

            SimpleButton(
                text = TextParameters(value = "Простая кнопка", size = 18),
                textAlign = TextAlign.Start
            ) {}

            Spacer(modifier = Modifier.padding(top = 20.dp))

            RowIconButton(
                text = TextParameters(value = "Кнопка с иконкой слева", size = 18),
                icon = IconParameters(value = Icons.Outlined.FavoriteBorder),
            ) {}

            Spacer(modifier = Modifier.padding(top = 20.dp))

            RowIconButton(
                text = TextParameters(value = "Кнопка с иконкой справа", size = 18),
                icon = IconParameters(value = Icons.Outlined.FavoriteBorder),
                iconPositionIsLeft = false
            ) {}

            Spacer(modifier = Modifier.padding(top = 20.dp))

            CustomButton(
                title = TextParameters(value = "Нажми меня", size = 18),
                text = TextParameters(value = "Если нажмешь, то ничего не будет", size = 14),
                icon = IconParameters(value = Icons.Outlined.FavoriteBorder)
            ) {}

            Spacer(modifier = Modifier.padding(top = 20.dp))

            ColIconButton(
                text = TextParameters(value = "Подпись", size = 14),
                icon = IconParameters(value = Icons.Outlined.FavoriteBorder)
            ) {}
        }
    }
}
