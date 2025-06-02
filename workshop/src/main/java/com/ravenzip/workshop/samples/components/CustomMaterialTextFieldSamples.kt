package com.ravenzip.workshop.samples.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.ravenzip.workshop.components.DropDownTextField
import com.ravenzip.workshop.components.SinglenessOutlinedTextField
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.state.DropDownTextFieldState
import com.ravenzip.workshop.forms.state.TextFieldState
import com.ravenzip.workshop.samples.model.Item

@Preview
@Composable
fun SinglenessOutlinedTextFieldWithFormStateSample() {
    val control = remember { FormControl(initialValue = "") }
    val state = remember { TextFieldState() }

    SinglenessOutlinedTextField(control = control, state = state)
}

@Preview
@Composable
fun DropDownTextFieldWithFormStateSample() {
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
