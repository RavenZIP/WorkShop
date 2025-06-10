package com.ravenzip.workshop.forms.control.extension

import com.ravenzip.workshop.forms.control.FormControlMulti

// TODO не хочется добавлять эти два метода в сам FormControlMulti, потому что он не должен знать
// TODO ничего про source. Как вариант оставить extension функции, либо же создать класс-обертку, но
// TODO компонент ничего о ней не будет знать (чисто для разработчика)

/** Выбрать все элементы */
fun <T> FormControlMulti<T>.selectAll(source: List<T>) {
    source.filter { x -> !this.isSelected(x) }.forEach { this.setValue(it) }
}

/** Снять выбор со всеъ элементов */
fun <T> FormControlMulti<T>.unselectAll(source: List<T>) {
    source.filter { x -> this.isSelected(x) }.forEach { this.setValue(it) }
}
