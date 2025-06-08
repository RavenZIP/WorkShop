package com.ravenzip.workshop.components.textField.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.ravenzip.workshop.components.textField.SinglenessOutlinedTextField
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.state.TextFieldState

@Preview
@Composable
fun SinglenessOutlinedTextFieldSample() {
    val control = remember { FormControl(initialValue = "") }
    val state = remember { TextFieldState() }

    SinglenessOutlinedTextField(control = control, state = state)
}
