package com.ravenzip.workshop.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.ravenzip.workshop.components.BottomAppBar
import com.ravenzip.workshop.components.TopAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CustomAppBarsScreen() {
    Scaffold(
        topBar = {
            TopAppBar("Главная", rightButton = true, backArrowClick = {}, rightButtonClick = {})
        },
        bottomBar = { BottomAppBar() }
    ) {
        // Элементы...
    }
}
