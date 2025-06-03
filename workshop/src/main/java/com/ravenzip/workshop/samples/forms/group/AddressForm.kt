package com.ravenzip.workshop.samples.forms.group

import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.control.base.AbstractFormControl
import com.ravenzip.workshop.forms.group.IFormGroup

class AddressForm(val street: FormControl<String>, val city: FormControl<String>) : IFormGroup {
    override fun getAllControls(): List<AbstractFormControl> {
        return listOf(street, city)
    }
}
