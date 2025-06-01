package com.ravenzip.workshop.forms.control.action

import androidx.compose.runtime.Stable
import com.ravenzip.workshop.forms.control.base.AbstractControl
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
@Stable
class MultiActionControl(
    private val onClick: (() -> Unit)? = null,
    private val onDoubleClick: (() -> Unit)? = null,
    private val onLongClick: (() -> Unit)? = null,
    disabled: Boolean = false,
) : AbstractControl(disabled) {
    fun onClick() {
        if (isEnabled) {
            onClick?.invoke()
        }
    }

    fun onLongClick() {
        if (isEnabled) {
            onLongClick?.invoke()
        }
    }

    fun onDoubleClick() {
        if (isEnabled) {
            onDoubleClick?.invoke()
        }
    }
}
