package com.ravenzip.workshop.components.textField.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.ravenzip.workshop.components.textField.DropDownTextField
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.state.DropDownTextFieldState
import com.ravenzip.workshop.model.sample.Item

@Preview
@Composable
fun DropDownTextFieldSample() {
    val items = remember {
        listOf(
            Item("1111111111", "Клен", 1),
            Item("222222222", "Дуб", 2),
            Item("333333333", "Береза", 3),
        )
    }
    val control = remember { FormControl(Item.createItem()) }

    DropDownTextField(
        control = control,
        state = DropDownTextFieldState(source = items, sourceView = { it.name }),
    )
}
