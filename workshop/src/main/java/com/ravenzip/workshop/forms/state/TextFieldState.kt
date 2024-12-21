package com.ravenzip.workshop.forms.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import com.ravenzip.workshop.forms.FormState
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
open class TextFieldState<T>(
    private val initialValue: T,
    resetValue: T = initialValue,
    validators: List<(T) -> String?> = emptyList(),
    disable: Boolean = false,
    private val readonly: Boolean = false,
) : FormState<T>(initialValue, resetValue, validators, disable) {
    private val _isReadonly: MutableState<Boolean> = mutableStateOf(readonly)
    private val _isFocused: MutableState<Boolean> = mutableStateOf(false)

    val isFocused: Boolean
        get() = _isFocused.value

    val isReadonly: Boolean
        get() = _isReadonly.value

    val isEditable: Boolean
        get() = !_isReadonly.value

    fun changeFocusState(value: Boolean) {
        _isFocused.value = value
    }

    fun readonly() {
        _isReadonly.value = true
    }

    fun editable() {
        _isReadonly.value = false
    }

    override fun reset() {
        super.reset()
        _isReadonly.value = readonly
    }

    companion object {
        @Composable
        fun <T> create(
            initialValue: T,
            resetValue: T = initialValue,
            validators: List<(T) -> String?> = emptyList(),
            disable: Boolean = false,
            readonly: Boolean = false,
        ): TextFieldState<T> where T : Any {
            return rememberSaveable {
                TextFieldState(initialValue, resetValue, validators, disable, readonly)
            }
        }
    }
}
