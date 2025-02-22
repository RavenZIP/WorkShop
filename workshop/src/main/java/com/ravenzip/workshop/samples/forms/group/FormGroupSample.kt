package com.ravenzip.workshop.samples.forms.group

import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.group.formGroup

fun createFormGroupSample() {
    val formGroup = formGroup {
        registerControl(UserForm.ID, FormControl(123))
        registerControl(UserForm.NAME, FormControl("Default Name"))
    }

    // idControl type is Int
    val idControl = formGroup.control(UserForm.ID)
    // nameControl type is String
    val nameControl = formGroup.control(UserForm.NAME)
}
