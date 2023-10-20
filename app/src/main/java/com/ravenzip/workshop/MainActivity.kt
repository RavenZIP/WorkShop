package com.ravenzip.workshop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ravenzip.workshop.ui.theme.WorkShopTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var text = mutableStateOf("")
                        var numbers = mutableStateOf("")

                        SimpleTextField(
                            text = text,
                            label = "Простое текстовое поле",
                            leadingIcon = Icons.Outlined.FavoriteBorder
                        )

                        Spacer(modifier = Modifier.padding(top = 20.dp))

                        SimpleTextField(
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
                    }
                }
            }
        }
    }
}

/*
SimpleButton(
                            text = "Простая кнопка",
                            textSize = 16,
                            textAlign = TextAlign.Start
                        ) {}

                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        SimpleButtonWithIcon(
                            text = "Кнопка с иконкой слева",
                            textSize = 16,
                            icon = Icons.Outlined.FavoriteBorder,
                        ) {}
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        SimpleButtonWithIcon(
                            text = "Кнопка с иконкой справа",
                            textSize = 16,
                            icon = Icons.Outlined.FavoriteBorder,
                            iconPositionIsLeft = false
                        ) {}
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        SimpleButtonWithIcon(
                            text = "Кнопка с иконкой справа с краю",
                            textSize = 16,
                            icon = Icons.Outlined.FavoriteBorder,
                            iconPositionIsLeft = false,
                            iconRightAtTheEnd = true
                        ) {}
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        SimpleButtonWithTitleAndIcon(
                            title = "Кнопка с заголовком, описанием и иконкой",
                            titleSize = 16,
                            text = "Описание",
                            textSize = 14,
                            icon = Icons.Outlined.FavoriteBorder
                        ) {}
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        SimpleButtonWithTitleAndIcon(
                            title = "Кнопка с заголовком и описанием",
                            titleSize = 16,
                            text = "Описание",
                            textSize = 14,
                            icon = null
                        ) {}
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        TextButtonWithIcon(
                            text = "Подпись",
                            textSize = 12,
                            icon = Icons.Outlined.FavoriteBorder,
                        ) {}
*/
