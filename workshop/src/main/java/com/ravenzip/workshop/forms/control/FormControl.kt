package com.ravenzip.workshop.forms.control

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.ravenzip.workshop.forms.control.base.AbstractFormControl
import com.ravenzip.workshop.forms.control.model.ValueChange
import com.ravenzip.workshop.forms.control.model.ValueChangeType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

// TODO реализовать запрет null значений -> надо ли?
// TODO запоминать когда контрол потрогали -> надо ли?
@Stable
class FormControl<T>(
    initialValue: T,
    private val validators: List<(T) -> String?> = emptyList(),
    disable: Boolean = false,
) : AbstractFormControl(disable) {
    internal val resetValue = initialValue

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

    override fun reset() = resetStateTo(resetValue)

    fun reset(value: T) = resetStateTo(value)

    private fun resetStateTo(value: T) {
        _state.value = value
        _valueChanges.update { ValueChange(value, ValueChangeType.Reset) }
        super.reset()
    }

    companion object {
        @Composable
        fun <T> create(
            initialValue: T,
            validators: List<(T) -> String?> = emptyList(),
            disable: Boolean = false,
        ): FormControl<T> where T : Any {
            return remember { FormControl(initialValue, validators, disable) }
        }
    }
}
