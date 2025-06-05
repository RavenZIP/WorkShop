package com.ravenzip.workshop.samples.forms.group

import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.group.FormGroup

fun createFormGroupSample() {
    val form =
        FormGroup(
            LoginForm(
                username = FormControl(""),
                password = FormControl(""),
                address = FormGroup(AddressForm(street = FormControl(""), city = FormControl(""))),
                phone = FormControl(0),
            )
        )

    val username = form.controls.username.value // ОК, тип String

    val phone = form.controls.phone.value // ОК, тип Int

    val address = form.controls.address // ОК, тип FormGroup<AddressForm>

    val street = form.controls.address.controls.street.value // ОК, тип String
}
