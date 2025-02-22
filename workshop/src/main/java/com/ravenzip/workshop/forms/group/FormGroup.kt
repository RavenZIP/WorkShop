package com.ravenzip.workshop.forms.group

import com.ravenzip.workshop.forms.control.FormControl
import org.jetbrains.annotations.ApiStatus.Experimental

/**
 * [FormGroup] - класс для управления состоянием формы
 *
 * Для использования сначала необходимо описать пользовательскую форму при помощи sealed class
 *
 * @sample com.ravenzip.workshop.samples.forms.group.UserForm
 * @sample com.ravenzip.workshop.samples.forms.group.createFormGroupSample
 */
// TODO сейчас нельзя использовать вместе с FormControlMulti, точнее может и можно, но as
// FormControl<T> мягко говоря вранье
// TODO реализовать setValue
@Experimental
class FormGroup internal constructor(private val _controls: Map<FormKey<*>, FormControl<*>>) {
    @Suppress("UNCHECKED_CAST")
    fun <T> control(key: FormKey<T>): FormControl<T> =
        _controls[key] as? FormControl<T>
            ?: throw IllegalArgumentException("Control with name $key does not exist")

    val controls: Collection<FormControl<*>>
        get() = _controls.values

    fun reset() {
        _controls.values.forEach { control -> control.reset() }
    }

    fun disable() {
        _controls.values.forEach { control -> control.disable() }
    }

    fun enable() {
        _controls.values.forEach { control -> control.enable() }
    }

    fun isValid(): Boolean {
        return _controls.values.all { control -> control.isValid }
    }

    fun isInvalid(): Boolean {
        return _controls.values.any { control -> control.isInvalid }
    }
}
