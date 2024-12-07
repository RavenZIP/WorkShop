package com.ravenzip.workshop.forms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.util.Locale
import org.jetbrains.annotations.ApiStatus.Experimental

// TODO реализовать валидацию
@Experimental
class DropDownFieldState<T>(
    private val initialValue: T,
    private val resetValue: T = initialValue,
    private val items: List<T>,
    private val itemsView: (T) -> String,
    validators: List<(T) -> String?> = emptyList(),
    disable: Boolean = false,
    readonly: Boolean = false,
) : FormState<T>(initialValue, resetValue, validators, disable, readonly) {
    private val _fieldState: MutableState<String> = mutableStateOf("")
    private val _expanded: MutableState<Boolean> = mutableStateOf(false)
    private val _results: SnapshotStateList<T> = mutableStateListOf()

    init {
        _fieldState.value = itemsView(initialValue)
        _results.addAll(items)
    }

    val fieldValue: String
        get() = _fieldState.value

    val expanded: Boolean
        get() = _expanded.value

    val results: SnapshotStateList<T>
        get() = _results

    fun view(value: T): String = itemsView(value)

    override fun setValue(value: T) {
        super.setValue(value)
        _fieldState.value = itemsView(value)
        _expanded.value = false
    }

    fun setFieldValue(value: String) {
        super.setValue(resetValue)
        search(value)
        _fieldState.value = value
        _expanded.value = true
    }

    private fun search(value: String) {
        _results.clear()
        val loverCaseValue = value.lowercase(Locale.getDefault())
        _results.addAll(
            items.filter { itemsView(it).lowercase(Locale.getDefault()).contains(loverCaseValue) }
        )
    }

    fun setExpanded(expanded: Boolean) {
        _expanded.value = expanded
        if (expanded) search(fieldValue) else reset()
    }

    override fun reset() {
        super.reset()
        _fieldState.value = itemsView(resetValue)
    }

    companion object {
        @Composable
        fun <T> create(
            initialValue: T,
            resetValue: T = initialValue,
            items: List<T>,
            itemsView: (T) -> String,
            validators: List<(T) -> String?> = emptyList(),
            disable: Boolean = false,
            readonly: Boolean = false,
        ): DropDownFieldState<T> where T : Any {
            return rememberSaveable {
                DropDownFieldState(
                    initialValue,
                    resetValue,
                    items,
                    itemsView,
                    validators,
                    disable,
                    readonly,
                )
            }
        }
    }
}
