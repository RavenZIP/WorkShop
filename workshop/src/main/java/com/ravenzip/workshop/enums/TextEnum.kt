package com.ravenzip.workshop.enums

import com.ravenzip.workshop.data.TextParameters

internal enum class TextEnum(val value: TextParameters) {
    TITLE(TextParameters(value = "Заголовок", size = 18)),
    TEXT(
        TextParameters(
            value = "Длинный текст для того, чтобы понять как выглядит элемент",
            size = 16
        )
    ),
    LITTLE_TEXT(
        TextParameters(
            value = "Длинный текст для того, чтобы понять как выглядит элемент",
            size = 14
        )
    ),
    LONG_TEXT(
        TextParameters(
            value =
                "Еще более длинный текс для того, чтобы понять как выглядит элемент. " +
                    "Подходит для проверки визуального оформления карточек, " +
                    "но может быть использован и для других элементов",
            size = 16
        )
    )
}
