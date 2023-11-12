package com.ravenzip.workshop.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ravenzip.workshop.components.BottomAppBar
import com.ravenzip.workshop.components.DeterminateSpinner
import com.ravenzip.workshop.components.InfoCard
import com.ravenzip.workshop.components.SimpleButton
import com.ravenzip.workshop.components.Spinner
import com.ravenzip.workshop.components.TopAppBar
import com.ravenzip.workshop.data.IconParameters
import com.ravenzip.workshop.data.TextParameters
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CustomCardsScreen(navController: NavController) {
    var isLoading by remember { mutableStateOf(false) }
    var isLoadingDeterminate by remember { mutableStateOf(false) }
    var timer = 0
    var progress by remember { mutableFloatStateOf(0.00f) }
    val scope = rememberCoroutineScope()

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
                icon =
                    IconParameters(
                        value = Icons.Outlined.Info,
                        color = MaterialTheme.colorScheme.primary
                    ),
                title = TextParameters(value = "Информационная карточка", size = 18),
                text =
                    TextParameters(
                        value =
                            "Это карточка, которая содержит в себе иконку, заголовок и описание. Предназначена для предоставления пользователям важной информации",
                        size = 14
                    ),
                colors =
                    CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer
                    )
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            SimpleButton(
                text = TextParameters(value = "Показать цикличный спиннер", size = 18),
                textAlign = TextAlign.Center
            ) {
                isLoading = true
                scope.launch {
                    while (timer != 3) {
                        delay(1000)
                        timer += 1
                    }
                    timer = 0
                    isLoading = false
                }
            }

            Spacer(modifier = Modifier.padding(top = 20.dp))

            SimpleButton(
                text = TextParameters(value = "Показать пошаговый спиннер", size = 18),
                textAlign = TextAlign.Center
            ) {
                isLoadingDeterminate = true
                scope.launch {
                    while (progress <= 1.00f) {
                        delay(100)
                        progress += Random.nextDouble(from = 0.0000, until = 0.0200).toFloat()
                    }
                    progress = 0.0f
                    isLoadingDeterminate = false
                }
            }
        }
        Spinner(
            show = isLoading,
            text = TextParameters("Загрузка", size = 14),
            containerColors =
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
        )
        DeterminateSpinner(
            show = isLoadingDeterminate,
            progressValue = progress,
            text = TextParameters("Загрузка", size = 14),
            showProgressPercentages = true,
            containerColors =
                CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
        )
    }
}
