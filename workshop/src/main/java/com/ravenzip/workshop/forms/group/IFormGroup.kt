package com.ravenzip.workshop.forms.group

import com.ravenzip.workshop.forms.control.base.AbstractFormControl

interface IFormGroup {
    fun getAllControls(): List<AbstractFormControl>

    fun getAllGroups(): List<IFormGroup>

    fun getAll(): List<AbstractFormControl> =
        getAllControls() + getAllGroups().flatMap { x -> x.getAllControls() }
}
