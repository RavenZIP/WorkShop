package com.ravenzip.workshop.forms.state

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import java.util.Locale
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update

class DropDownTextFieldState<T>(
    private val source: List<T>,
    private val sourceView: (T) -> String,
    readonly: Boolean = false,
) : TextFieldState(readonly) {
    private val _text: MutableState<String> = mutableStateOf("")
    private val _expanded: MutableState<Boolean> = mutableStateOf(false)
    private val _results: SnapshotStateList<T> = mutableStateListOf()

    private val _expandedChanges: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val expandedChanges: SharedFlow<Boolean> = _expandedChanges.asSharedFlow()

    val text: String
        get() = _text.value

    val expanded: Boolean
        get() = _expanded.value

    val results: SnapshotStateList<T>
        get() = _results

    fun view(value: T): String = sourceView(value)

    private fun search(value: String) {
        _results.clear()
        val loverCaseValue = value.lowercase(Locale.getDefault())
        _results.addAll(
            source.filter { sourceView(it).lowercase(Locale.getDefault()).contains(loverCaseValue) }
        )
    }

    fun setText(value: String) {
        _text.value = value
    }

    fun setExpanded(expanded: Boolean) {
        _expanded.value = expanded
        _expandedChanges.update { expanded }

        if (expanded) {
            search(text)
        }
    }
}
