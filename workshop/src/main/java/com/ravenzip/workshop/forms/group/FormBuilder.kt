package com.ravenzip.workshop.forms.group

import com.ravenzip.workshop.forms.control.FormControl

@DslMarker annotation class FormGroupDsl

// TODO подумать над тем надо ли реализовывать группировку контролов
@FormGroupDsl
class FormGroupBuilder {
    private val controls = mutableMapOf<FormKey<*>, FormControl<*>>()

    fun <T> registerControl(key: FormKey<T>, control: FormControl<T>) {
        controls[key] = control
    }

    fun build(): FormGroup = FormGroup(controls)
}

fun formGroup(init: FormGroupBuilder.() -> Unit): FormGroup = FormGroupBuilder().apply(init).build()
