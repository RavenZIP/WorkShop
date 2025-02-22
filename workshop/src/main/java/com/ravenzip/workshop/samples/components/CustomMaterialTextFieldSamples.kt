package com.ravenzip.workshop.samples.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.ravenzip.workshop.components.DropDownTextField
import com.ravenzip.workshop.components.SinglenessOutlinedTextField
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.dropdown.DropDownTextFieldComponent
import com.ravenzip.workshop.forms.dropdown.DropDownTextFieldState
import com.ravenzip.workshop.forms.textfield.TextFieldComponent
import com.ravenzip.workshop.forms.textfield.TextFieldState
import com.ravenzip.workshop.samples.model.Item

@Preview
@Composable
fun SinglenessOutlinedTextFieldWithFormStateSample() {
    val composableScope = rememberCoroutineScope()

    val component = remember {
        TextFieldComponent(
            control = FormControl(""),
            state = TextFieldState(),
            scope = composableScope,
        )
    }

    SinglenessOutlinedTextField(component)
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

    val composableScope = rememberCoroutineScope()

    val component = remember {
        DropDownTextFieldComponent(
            control = FormControl(Item.createItem()),
            state = DropDownTextFieldState(source = items, sourceView = { it.name }),
            scope = composableScope,
        )
    }

    DropDownTextField(component)
}
