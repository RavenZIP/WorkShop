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
import com.ravenzip.workshop.components.ButtonWithIcon
import com.ravenzip.workshop.components.ButtonWithTitleAndIcon
import com.ravenzip.workshop.components.SimpleButton
import com.ravenzip.workshop.components.TextButtonWithIcon

@Composable
fun CustomButtonsScreen() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleButton(
            width = null,
            text = "Простая кнопка",
            textSize = 18,
            textAlign = TextAlign.Start
        ) {}

        Spacer(modifier = Modifier.padding(top = 20.dp))

        SimpleButton(text = "Простая кнопка", textSize = 18, textAlign = TextAlign.Start) {}

        Spacer(modifier = Modifier.padding(top = 20.dp))

        ButtonWithIcon(
            text = "Кнопка с иконкой слева",
            textSize = 18,
            icon = Icons.Outlined.FavoriteBorder,
        ) {}

        Spacer(modifier = Modifier.padding(top = 20.dp))

        ButtonWithIcon(
            text = "Кнопка с иконкой справа",
            textSize = 18,
            icon = Icons.Outlined.FavoriteBorder,
            iconPositionLeft = false
        ) {}

        Spacer(modifier = Modifier.padding(top = 20.dp))

        ButtonWithTitleAndIcon(
            title = "Нажми меня",
            titleSize = 18,
            text = "Если нажмешь, то ничего не будет",
            textSize = 14,
            icon = Icons.Outlined.FavoriteBorder
        ) {}

        Spacer(modifier = Modifier.padding(top = 20.dp))

        TextButtonWithIcon(
            text = "Подпись",
            textSize = 14,
            icon = Icons.Outlined.FavoriteBorder,
        ) {}
    }
}
