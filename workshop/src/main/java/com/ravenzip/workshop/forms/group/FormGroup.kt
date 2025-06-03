package com.ravenzip.workshop.forms.group

import androidx.compose.runtime.Stable
import org.jetbrains.annotations.ApiStatus.Experimental

/**
 * [FormGroup] - класс для управления состоянием формы
 *
 * @sample com.ravenzip.workshop.samples.forms.group.LoginForm
 * @sample com.ravenzip.workshop.samples.forms.group.AddressForm
 * @sample com.ravenzip.workshop.samples.forms.group.createFormGroupSample
 */
@Experimental
class FormGroup<T : IFormGroup>(val controls: T) {
    fun reset() {
        controls.getAllControls().forEach { control -> control.reset() }
    }

    fun disable() {
        controls.getAllControls().forEach { control -> control.disable() }
    }

    fun enable() {
        controls.getAllControls().forEach { control -> control.enable() }
    }

    @Stable
    val isValid
        get() = controls.getAllControls().all { control -> control.isValid }

    @Stable
    val isInvalid
        get() = controls.getAllControls().any { controls -> controls.isInvalid }

    @Stable
    val errorMessages
        get() =
            controls
                .getAllControls()
                .filter { controls -> controls.isInvalid }
                .map { controls -> controls.errorMessage }

    @Stable
    val errorMessage
        get() =
            controls.getAllControls().firstOrNull { controls -> controls.isInvalid }?.errorMessage
                ?: ""
}
