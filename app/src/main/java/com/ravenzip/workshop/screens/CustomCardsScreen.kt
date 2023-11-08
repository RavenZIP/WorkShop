package com.ravenzip.workshop.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ravenzip.workshop.components.BottomAppBar
import com.ravenzip.workshop.components.CustomCardColors
import com.ravenzip.workshop.components.InfoCard
import com.ravenzip.workshop.components.TopAppBar

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
                icon = Icons.Outlined.Info,
                title = "Информационная карточка",
                text =
                    "Это карточка, которая содержит в себе иконку, заголовок и описание. Предназначена для предоставления пользователям важной информации",
                colors =
                    CustomCardColors(
                        backgroundColor = MaterialTheme.colorScheme.surfaceContainer,
                        iconColor = MaterialTheme.colorScheme.primary,
                        titleColor = MaterialTheme.colorScheme.onPrimary,
                        textColor = MaterialTheme.colorScheme.onPrimary
                    )
            )
        }
    }
}
