package com.ravenzip.workshop.samples.forms.group

import com.ravenzip.workshop.forms.group.FormKey

sealed class UserForm<T> : FormKey<T> {
    data object ID : UserForm<Int>()

    data object NAME : UserForm<String>()
}
