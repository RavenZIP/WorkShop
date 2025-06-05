package com.ravenzip.workshop.forms.group

import androidx.compose.runtime.Stable
import com.ravenzip.workshop.forms.control.base.AbstractFormControl
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible
import org.jetbrains.annotations.ApiStatus.Experimental

/**
 * [FormGroup] - класс для управления состоянием формы
 *
 * @sample com.ravenzip.workshop.samples.forms.group.LoginForm
 * @sample com.ravenzip.workshop.samples.forms.group.AddressForm
 * @sample com.ravenzip.workshop.samples.forms.group.createFormGroupSample
 */
@Experimental
open class FormGroup<T : IFormGroup>(val controls: T) {
    init {
        validateControls(controls)
    }

    /**
     * Валидация FormGroup при ее создании
     *
     * Проверяем, что объявленные в controls поля принадлежат AbstractFormControl или IFormGroup
     */
    private fun validateControls(obj: Any, path: String = "") {
        val controls = obj::class

        for (property in controls.declaredMemberProperties) {
            val controlsClassifier = property.returnType.classifier

            if (controlsClassifier is KClass<*>) {
                property.isAccessible = true
                val value = property.getter.call(obj) ?: continue
                val fullPath = "$path.${property.name}".trimStart('.')

                when (value) {
                    is AbstractFormControl -> {} // OK
                    is IFormGroup -> validateControls(value, fullPath) // Recurse
                    else ->
                        throw IllegalArgumentException(
                            "Invalid control at '$fullPath': ${value::class.simpleName}"
                        )
                }
            }
        }
    }

    fun reset() {
        controls.getAll().forEach { control -> control.reset() }
    }

    fun disable() {
        controls.getAll().forEach { control -> control.disable() }
    }

    fun enable() {
        controls.getAll().forEach { control -> control.enable() }
    }

    @Stable
    val isValid
        get() = controls.getAll().all { control -> control.isValid }

    @Stable
    val isInvalid
        get() = controls.getAll().any { controls -> controls.isInvalid }

    @Stable
    val errorMessages
        get() =
            controls
                .getAll()
                .filter { controls -> controls.isInvalid }
                .map { controls -> controls.errorMessage }

    @Stable
    val errorMessage
        get() = controls.getAll().firstOrNull { controls -> controls.isInvalid }?.errorMessage ?: ""
}
