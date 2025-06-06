package com.ravenzip.workshop.samples.forms.group

import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.group.FormGroup

class LoginForm(
    val username: FormControl<String>,
    val password: FormControl<String>,
    val address: FormGroup<AddressForm, AddressValue>,
    val phone: FormControl<Int>,
)

data class LoginValue(
    val username: String,
    val password: String,
    val address: AddressValue,
    val phone: Int
)