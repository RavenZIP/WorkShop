package com.ravenzip.workshop.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.ButtonWithIcon
import com.ravenzip.workshop.ButtonWithTitleAndIcon
import com.ravenzip.workshop.SimpleButton
import com.ravenzip.workshop.TextButtonWithIcon

@Composable
fun CustomButtonsScreen() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleButton(text = "Простая кнопка", textSize = 16, textAlign = TextAlign.Start) {}

        Spacer(modifier = Modifier.padding(top = 20.dp))

        ButtonWithIcon(
            text = "Кнопка с иконкой слева",
            textSize = 16,
            icon = Icons.Outlined.FavoriteBorder,
        ) {}

        Spacer(modifier = Modifier.padding(top = 20.dp))

        ButtonWithIcon(
            text = "Кнопка с иконкой справа",
            textSize = 16,
            icon = Icons.Outlined.FavoriteBorder,
            iconPositionLeft = false
        ) {}

        Spacer(modifier = Modifier.padding(top = 20.dp))

        ButtonWithTitleAndIcon(
            title = "Кнопка с заголовком, описанием и иконкой",
            titleSize = 16,
            text = "Описание",
            textSize = 14,
            icon = Icons.Outlined.FavoriteBorder
        ) {}

        Spacer(modifier = Modifier.padding(top = 20.dp))

        TextButtonWithIcon(
            text = "Подпись",
            textSize = 12,
            icon = Icons.Outlined.FavoriteBorder,
        ) {}
    }
}
