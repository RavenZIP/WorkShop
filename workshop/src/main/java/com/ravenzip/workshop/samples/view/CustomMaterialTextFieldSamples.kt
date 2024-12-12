package com.ravenzip.workshop.samples.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.ravenzip.workshop.components.DropDownTextField
import com.ravenzip.workshop.components.SinglenessOutlinedTextField
import com.ravenzip.workshop.forms.FormState
import com.ravenzip.workshop.forms.state.DropDownTextFieldState
import com.ravenzip.workshop.samples.model.Item

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SinglenessOutlinedTextFieldWithFormStateSample() {
    val formState = FormState.create("")

    SinglenessOutlinedTextField(formState)
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

    val formState =
        DropDownTextFieldState.create(
            initialValue = items.first(),
            resetValue = Item("", "", 0),
            items = items,
            itemsView = { it.name },
        )

    DropDownTextField(formState)
}
