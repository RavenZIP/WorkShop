package com.ravenzip.workshop.samples.forms.group

import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.control.base.AbstractFormControl
import com.ravenzip.workshop.forms.group.IFormGroup

class LoginForm(
    val username: FormControl<String>,
    val password: FormControl<String>,
    val address: AddressForm,
    val phone: FormControl<Int>,
) : IFormGroup {
    override fun getAllControls(): List<AbstractFormControl> {
        return listOf(username, password, phone)
    }

    override fun getAllGroups(): List<IFormGroup> {
        return listOf(address)
    }
}
