package com.ravenzip.workshop.forms.group

import com.ravenzip.workshop.forms.control.base.AbstractFormControl

interface IFormGroup {
    fun getAllControls(): List<AbstractFormControl> = emptyList()

    fun getAllGroups(): List<IFormGroup> = emptyList()

    fun getAll(): List<AbstractFormControl> =
        getAllControls() + getAllGroups().flatMap { x -> x.getAllControls() }
}
