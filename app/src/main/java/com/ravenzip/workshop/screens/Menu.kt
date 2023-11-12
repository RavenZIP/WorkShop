package com.ravenzip.workshop.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ravenzip.workshop.components.BottomAppBar
import com.ravenzip.workshop.components.SimpleButton
import com.ravenzip.workshop.components.TopAppBar
import com.ravenzip.workshop.data.BottomNavigationItem
import com.ravenzip.workshop.data.TextParameters
import com.ravenzip.workshop.data.TopNavigationItem

val bottomBarButtons =
    listOf(
        BottomNavigationItem(
            label = "Меню",
            route = "Menu",
            icon = Icons.Outlined.Home,
            hasNews = false
        ),
        BottomNavigationItem(
            label = "Кнопки",
            route = "Buttons",
            icon = Icons.Outlined.Build,
            hasNews = false
        ),
        BottomNavigationItem(
            label = "Текстовые поля",
            route = "Text fields",
            icon = Icons.Outlined.Lock,
            hasNews = false,
            badgeCount = 5
        ),
        BottomNavigationItem(
            label = "Элементы выбора",
            route = "Selection elements",
            icon = Icons.Outlined.Menu,
            hasNews = true
        )
    )

@SuppressLint("UnrememberedMutableState")
@Composable
fun Menu(navController: NavController) {
    val topBarButtons =
        listOf(
            TopNavigationItem(icon = Icons.Outlined.Add, description = "", enabled = true) {},
            TopNavigationItem(icon = Icons.Outlined.Edit, description = "", enabled = true) {},
            TopNavigationItem(icon = Icons.Outlined.Delete, description = "", enabled = true) {}
        )
    Scaffold(
        topBar = { TopAppBar(text = "Меню", items = topBarButtons) },
        bottomBar = {
            BottomAppBar(
                navController = navController,
                buttonsList = bottomBarButtons,
                showLabelOnlyOnSelected = false
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 30.dp))

            SimpleButton(
                text = TextParameters(value = "Кнопки", size = 18),
                textAlign = TextAlign.Center
            ) {
                navController.navigate("Buttons")
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            SimpleButton(
                text = TextParameters(value = "Текстовые поля", size = 18),
                textAlign = TextAlign.Center
            ) {
                navController.navigate("Text fields")
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            SimpleButton(
                text = TextParameters(value = "Карточки", size = 18),
                textAlign = TextAlign.Center
            ) {
                navController.navigate("Cards")
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            SimpleButton(
                text = TextParameters(value = "Элементы выбора", size = 18),
                textAlign = TextAlign.Center
            ) {
                navController.navigate("Selection elements")
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            SimpleButton(
                text = TextParameters(value = "Списки", size = 18),
                textAlign = TextAlign.Center
            ) {
                /// navController.navigate("lists")
            }
        }
    }
}
