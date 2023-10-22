package com.ravenzip.workshop.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ravenzip.workshop.SimpleButton

@Composable
fun Menu(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Меню", fontSize = 30.sp)

        Spacer(modifier = Modifier.padding(top = 20.dp))

        SimpleButton(text = "Кнопки", textSize = 18, textAlign = TextAlign.Center) {
            navController.navigate("buttons")
        }

        Spacer(modifier = Modifier.padding(top = 20.dp))

        SimpleButton(text = "Текстовые поля", textSize = 18, textAlign = TextAlign.Center) {
            navController.navigate("textfields")
        }

        Spacer(modifier = Modifier.padding(top = 20.dp))

        SimpleButton(text = "Карточки", textSize = 18, textAlign = TextAlign.Center) {
            /// navController.navigate("cards")
        }
    }
}
