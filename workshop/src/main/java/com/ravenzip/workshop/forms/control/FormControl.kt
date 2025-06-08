package com.ravenzip.workshop.forms.control

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.ravenzip.workshop.forms.control.base.AbstractFormControl
import com.ravenzip.workshop.forms.valueChange.ValueChange
import com.ravenzip.workshop.forms.valueChange.ValueChangeType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

// TODO реализовать запрет null значений -> надо ли?
// TODO запоминать когда контрол потрогали -> надо ли?
@Stable
class FormControl<T>(
    private val initialValue: T,
    val resetValue: T = initialValue,
    private val validators: List<(T) -> String?> = emptyList(),
    disable: Boolean = false,
) : AbstractFormControl(disable) {
    private val _state: MutableState<T> = mutableStateOf(initialValue)

    private val _valueChanges: MutableStateFlow<ValueChange<T>> =
        MutableStateFlow(ValueChange(initialValue, ValueChangeType.Initialize))
    val valueChangesWithTypeChanges = _valueChanges.asSharedFlow()

    val valueChanges =
        valueChangesWithTypeChanges.map { valueChangesWithTypeChanges ->
            valueChangesWithTypeChanges.value
        }

    @Stable
    val value: T
        get() = _state.value

    fun setValue(value: T) {
        _state.value = value
        _valueChanges.update { ValueChange(value, ValueChangeType.Set) }
        updateValidity()
    }

    private fun updateValidity() {
        val error = validators.firstNotNullOfOrNull { validator -> validator(value) } ?: ""
        super.setError(error)
    }

    override fun reset() {
        _state.value = resetValue
        _valueChanges.update { ValueChange(resetValue, ValueChangeType.Reset) }
        super.reset()
    }

    companion object {
        @Composable
        fun <T> create(
            initialValue: T,
            resetValue: T = initialValue,
            validators: List<(T) -> String?> = emptyList(),
            disable: Boolean = false,
        ): FormControl<T> where T : Any {
            return remember { FormControl(initialValue, resetValue, validators, disable) }
        }
    }
}
