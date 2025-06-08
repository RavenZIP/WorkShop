package com.ravenzip.workshop.samples.forms.group

import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.group.FormGroup

fun createFormGroupSample() {
    val addressForm =
        FormGroup(
            controls = AddressForm(street = FormControl(""), city = FormControl("")),
            formValueProvider = { controls ->
                AddressValue(street = controls.street.value, city = controls.city.value)
            },
            setValueProvider = { controls, value ->
                controls.street.setValue(value.street)
                controls.city.setValue(value.city)
            },
        )

    val loginForm =
        FormGroup(
            controls =
                LoginForm(
                    username = FormControl(""),
                    password = FormControl(""),
                    address = addressForm,
                    phone = FormControl(0),
                ),
            formValueProvider = { controls ->
                LoginValue(
                    username = controls.username.value,
                    password = controls.password.value,
                    phone = controls.phone.value,
                    address = controls.address.value,
                )
            },
            setValueProvider = { controls, value ->
                controls.username.setValue(value.username)
                controls.password.setValue(value.password)
                controls.address.setValue(value.address)
                controls.phone.setValue(value.phone)
            },
        )

    val username = loginForm.controls.username.value // ОК, тип String

    val phone = loginForm.controls.phone.value // ОК, тип Int

    val address = loginForm.controls.address // ОК, тип FormGroup<AddressForm>

    val street = loginForm.controls.address.controls.street.value // ОК, тип String

    val loginFormValue = loginForm.value // ОК, тип LoginValue
}
