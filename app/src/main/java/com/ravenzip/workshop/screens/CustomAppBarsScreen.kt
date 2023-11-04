package com.ravenzip.workshop.screens

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.ravenzip.workshop.components.BottomAppBar
import com.ravenzip.workshop.components.TopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomAppBarsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                "Главная",
                rightButton = true,
                rightButtonIcon = Icons.Outlined.Close,
                backArrowClick = {},
                rightButtonClick = {}
            )
        },
        bottomBar = { BottomAppBar() }
    ) {
        // Элементы...
    }
}
