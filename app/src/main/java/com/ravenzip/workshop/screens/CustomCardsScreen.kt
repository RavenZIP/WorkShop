package com.ravenzip.workshop.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ravenzip.workshop.components.BottomAppBar
import com.ravenzip.workshop.components.InfoCard
import com.ravenzip.workshop.components.TopAppBar
import com.ravenzip.workshop.data.IconParameters
import com.ravenzip.workshop.data.TextParameters

@Composable
fun CustomCardsScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar("Карточки", backArrow = true) { navController.popBackStack() } },
        bottomBar = { BottomAppBar(navController = navController, buttonsList = bottomBarButtons) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 30.dp))

            InfoCard(
                icon = IconParameters(value = Icons.Outlined.Info),
                title = TextParameters(value = "Информационная карточка", size = 18),
                text =
                    TextParameters(
                        value =
                            "Это карточка, которая содержит в себе иконку, заголовок и описание. Предназначена для предоставления пользователям важной информации",
                        size = 14
                    )
            )
        }
    }
}
