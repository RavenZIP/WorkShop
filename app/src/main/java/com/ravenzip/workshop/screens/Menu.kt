package com.ravenzip.workshop.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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

@Composable
fun Menu(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar("Меню", backArrowClick = {}, rightButtonClick = {}) },
        bottomBar = { BottomAppBar() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 30.dp))

            SimpleButton(text = "Кнопки", textSize = 18, textAlign = TextAlign.Center) {
                navController.navigate("buttons")
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            SimpleButton(text = "Текстовые поля", textSize = 18, textAlign = TextAlign.Center) {
                navController.navigate("text fields")
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            SimpleButton(
                text = "Нижняя и верхняя панель",
                textSize = 18,
                textAlign = TextAlign.Center
            ) {
                navController.navigate("app bars")
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            SimpleButton(text = "Карточки", textSize = 18, textAlign = TextAlign.Center) {
                /// navController.navigate("cards")
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            SimpleButton(text = "Элементы выбора", textSize = 18, textAlign = TextAlign.Center) {
                navController.navigate("selection elements")
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            SimpleButton(text = "Списки", textSize = 18, textAlign = TextAlign.Center) {
                /// navController.navigate("lists")
            }
        }
    }
}
