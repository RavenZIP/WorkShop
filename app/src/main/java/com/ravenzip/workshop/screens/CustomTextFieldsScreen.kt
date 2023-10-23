package com.ravenzip.workshop.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.components.MultilineTextField
import com.ravenzip.workshop.components.SimpleTextField
import com.ravenzip.workshop.components.SinglenessTextField

@SuppressLint("UnrememberedMutableState")
@Composable
fun CustomTextFieldsScreen() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val text = mutableStateOf("")
        val text2 = mutableStateOf("")
        val text3 = mutableStateOf("")
        val numbers = mutableStateOf("")

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

        Spacer(modifier = Modifier.padding(top = 20.dp))

        SimpleTextField(text = text3, textSize = 16, placeholder = "Простое текстовое поле")
    }
}
