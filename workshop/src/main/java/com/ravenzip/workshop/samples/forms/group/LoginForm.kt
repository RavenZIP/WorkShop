package com.ravenzip.workshop.samples.forms.group

import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.group.FormGroup

class LoginForm(
    val username: FormControl<String>,
    val password: FormControl<String>,
    val address: FormGroup<AddressForm>,
    val phone: FormControl<Int>,
)
