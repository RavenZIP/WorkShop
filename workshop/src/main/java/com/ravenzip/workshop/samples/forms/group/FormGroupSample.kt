package com.ravenzip.workshop.samples.forms.group

import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.group.FormGroup

fun createFormGroupSample() {
    val form =
        FormGroup(
            LoginForm(
                username = FormControl(""),
                password = FormControl(""),
                address = AddressForm(street = FormControl(""), city = FormControl("")),
                phone = FormControl(0),
            )
        )

    val username = form.controls.username.value // ОК, тип String

    val phone = form.controls.phone.value // ОК, тип Int

    val street = form.controls.address.street.value // ОК, тип String
}
