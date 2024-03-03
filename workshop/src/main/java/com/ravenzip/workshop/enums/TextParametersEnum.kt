package com.ravenzip.workshop.enums

import com.ravenzip.workshop.data.TextParameters

internal enum class TextParametersEnum(val value: TextParameters) {
    TITLE(TextParameters(value = "Заголовок", size = 18)),
    TEXT(TextParameters(value = "Текст", size = 18)),
    CLICK_TO_ME(
        TextParameters(
            value = "Нажми меня",
            size = 14
        )
    ),
    LONG_TEXT(
        TextParameters(
            value = "Длинный текст для того, чтобы понять как выглядит элемент",
            size = 16
        )
    ),
    WIDE_TEXT(
        TextParameters(
            value =
                "Еще более длинный текст для того, чтобы понять как выглядит элемент. " +
                    "Подходит для проверки визуального оформления карточек, " +
                    "но может быть использован и для других элементов",
            size = 16
        )
    )
}
