package com.ravenzip.workshop.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ravenzip.workshop.components.BottomAppBar
import com.ravenzip.workshop.components.MultilineTextField
import com.ravenzip.workshop.components.SimpleTextField
import com.ravenzip.workshop.components.SinglenessTextField
import com.ravenzip.workshop.components.TopAppBar_v2

@SuppressLint("UnrememberedMutableState")
@Composable
fun CustomTextFieldsScreen(navController: NavController) {
    val text = mutableStateOf("")
    val text2 = mutableStateOf("")
    val text3 = mutableStateOf("")
    val numbers = mutableStateOf("")

    Scaffold(
        topBar = { TopAppBar_v2("Текстовые поля", backArrow = true, isMenu = false) },
        bottomBar = { BottomAppBar(navController = navController, buttonsList = bottomBarButtons) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 20.dp))

            SinglenessTextField(
                text = text,
                label = "Однострочное текстовое поле",
                leadingIcon = Icons.Outlined.FavoriteBorder
            )

            Spacer(modifier = Modifier.padding(top = 20.dp))

            SinglenessTextField(
                text = numbers,
                pattern = Regex("^\\d*\$"),
                maxLength = 50,
                label = "Числовое текстовое поле",
                leadingIcon = Icons.Outlined.FavoriteBorder,
                showTextLengthCounter = true,
                colors =
                    OutlinedTextFieldDefaults.colors(
                        focusedLeadingIconColor =
                            OutlinedTextFieldDefaults.colors().focusedIndicatorColor
                    )
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))

            MultilineTextField(text = text2, label = "Многострочное текстовое поле")

            Spacer(modifier = Modifier.padding(top = 40.dp))

            SimpleTextField(text = text3, textSize = 16, placeholder = "Простое текстовое поле")
        }
    }
}
