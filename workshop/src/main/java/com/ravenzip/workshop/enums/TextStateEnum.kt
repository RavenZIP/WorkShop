package com.ravenzip.workshop.enums

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

enum class TextStateEnum(val value: MutableState<String>) {
    TEXT(mutableStateOf("Введенный текст")),
    LONG_TEXT(
        mutableStateOf(
            "Тучки небесные, вечные странники!\n" +
                "Степью лазурною, цепью жемчужною\n" +
                "Мчитесь вы, будто как я же, изгнанники\n" +
                "С милого севера в сторону южную."
        )
    )
}
