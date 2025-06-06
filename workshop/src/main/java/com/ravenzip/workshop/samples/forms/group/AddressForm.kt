package com.ravenzip.workshop.samples.forms.group

import com.ravenzip.workshop.forms.control.FormControl

class AddressForm(val street: FormControl<String>, val city: FormControl<String>)

data class AddressValue(val street: String, val city: String)
