package com.ravenzip.workshop.forms.control.action

import androidx.compose.runtime.Stable
import com.ravenzip.workshop.forms.control.base.AbstractControl
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
@Stable
class SingleActionControl(private val onClick: (() -> Unit)? = null, disable: Boolean = false) :
    AbstractControl(disable) {
    fun onClick() {
        if (isEnabled) {
            onClick?.invoke()
        }
    }
}
