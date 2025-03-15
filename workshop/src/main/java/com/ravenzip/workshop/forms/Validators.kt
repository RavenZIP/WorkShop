package com.ravenzip.workshop.forms

import android.util.Patterns.EMAIL_ADDRESS
import android.util.Patterns.PHONE
import androidx.compose.runtime.Immutable

/** Возможные валидаторы для компонентов */
@Immutable
class Validators {
    companion object {
        val required = { value: String ->
            if (value.isEmpty()) "Поле обязательно для заполнения" else null
        }

        val minLength = { value: String, min: Int ->
            if (value.length < min) "Минимальная длина $min символа" else null
        }

        val maxLength = { value: String, max: Int ->
            if (value.length > max) "Максимальная длина $max символа" else null
        }

        val email = { value: String ->
            if (!EMAIL_ADDRESS.matcher(value).matches()) "Введен некорректный email" else null
        }

        val phone = { value: String ->
            if (!PHONE.matcher(value).matches()) "Введен некорректный номер телефона" else null
        }
    }
}
