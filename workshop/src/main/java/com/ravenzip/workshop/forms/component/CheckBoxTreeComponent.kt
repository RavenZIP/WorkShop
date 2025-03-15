package com.ravenzip.workshop.forms.component

import androidx.compose.runtime.Immutable
import androidx.compose.ui.state.ToggleableState
import com.ravenzip.workshop.data.Equatable
import com.ravenzip.workshop.forms.control.FormControlTree
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
@Immutable
class CheckBoxTreeComponent<T : Equatable>(
    val control: FormControlTree<T>,
    val source: List<T>,
    val view: (T) -> String,
    scope: CoroutineScope,
) {
    fun selectAll() {
        val unselectedItems = source.filter { item -> item !in control.value.children }
        control.setValue(unselectedItems)
    }

    fun unselectAll() {
        val selectedItems = source.filter { item -> item in control.value.children }
        control.setValue(selectedItems)
    }

    fun changeParentState() {
        val calculatedParentValue =
            when (control.value.parent) {
                ToggleableState.On -> ToggleableState.Off
                else -> ToggleableState.On
            }

        control.setValue(calculatedParentValue)
    }

    init {
        control.valueChangesWithTypeChanges
            .distinctUntilChanged { previousChanges, currentChanges ->
                previousChanges.value.parent == currentChanges.value.parent
            }
            .onEach { valueChanges ->
                when (valueChanges.value.parent) {
                    ToggleableState.On -> selectAll()
                    ToggleableState.Off -> unselectAll()
                    else -> {
                        // ничего не делаем, потому что в случае indereterminate невозможно
                        // определить какие значения добавить, а какие нет
                    }
                }
            }
            .launchIn(scope)

        control.valueChangesWithTypeChanges
            .distinctUntilChanged { previousChanges, currentChanges ->
                previousChanges.value.children == currentChanges.value.children
            }
            .onEach { valueChanges ->
                val calculatedParentValue =
                    when (valueChanges.value.children.count()) {
                        0 -> ToggleableState.Off
                        source.count() -> ToggleableState.On
                        else -> ToggleableState.Indeterminate
                    }

                control.setValue(calculatedParentValue)
            }
            .launchIn(scope)
    }
}
