package com.ravenzip.workshop.forms.control.model

/** Типы изменений значения в контролах */
sealed class ValueChangeType {
    /** Значение при инициализации */
    data object Initialize : ValueChangeType()

    /** Значение при установке */
    data object Set : ValueChangeType()

    /** Значение при сбросе */
    data object Reset : ValueChangeType()
}