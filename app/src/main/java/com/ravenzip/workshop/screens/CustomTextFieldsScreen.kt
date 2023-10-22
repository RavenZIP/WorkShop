package com.ravenzip.workshop.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.ButtonWithTitleAndIcon
import com.ravenzip.workshop.MultilineTextField
import com.ravenzip.workshop.SimpleButton
import com.ravenzip.workshop.SimpleTextField
import com.ravenzip.workshop.SinglenessTextField

@SuppressLint("UnrememberedMutableState")
@Composable
fun CustomTextFieldsScreen() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val text = mutableStateOf("")
        val numbers = mutableStateOf("")

        SinglenessTextField(
            text = text,
            label = "Простое текстовое поле",
            leadingIcon = Icons.Outlined.FavoriteBorder
        )

        Spacer(modifier = Modifier.padding(top = 20.dp))

        SinglenessTextField(
            text = numbers,
            pattern = Regex("^\\d*\$"),
            maxLength = 50,
            label = "Числовое текстовое поле",
            leadingIcon = Icons.Outlined.FavoriteBorder,
            showTextLengthCounter = true
        )

        Spacer(modifier = Modifier.padding(top = 20.dp))

        SimpleButton(
            text = "Простая кнопка",
            textSize = 16,
            textAlign = TextAlign.Start,
            width = null
        ) {}
        Spacer(modifier = Modifier.padding(top = 20.dp))

        MultilineTextField(text = text)

        Spacer(modifier = Modifier.padding(top = 20.dp))

        ButtonWithTitleAndIcon(
            title = "Заголовок",
            titleSize = 18,
            icon = Icons.Outlined.FavoriteBorder
        ) {}

        Spacer(modifier = Modifier.padding(top = 20.dp))

        SimpleTextField(text = text, textSize = 16, placeholder = "Простое текстовое поле")
    }
}
