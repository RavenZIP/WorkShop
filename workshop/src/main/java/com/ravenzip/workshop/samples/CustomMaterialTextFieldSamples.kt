package com.ravenzip.workshop.samples

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ravenzip.workshop.components.SinglenessOutlinedTextField
import com.ravenzip.workshop.forms.FormState

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SinglenessOutlinedTextFieldWithFormStateSample() {
    val formState = FormState.create("")

    SinglenessOutlinedTextField(formState)
}
