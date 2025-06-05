package com.ravenzip.workshop.forms.group

import androidx.compose.runtime.Stable
import com.ravenzip.workshop.forms.control.base.AbstractFormControl
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible
import org.jetbrains.annotations.ApiStatus.Experimental

private val DEFAULT_FORM_GROUP_FIELDS =
    listOf<String>("_controls", "isValid", "isInvalid", "errorMessages", "errorMessage")

/**
 * [FormGroup] - класс для управления состоянием формы
 *
 * @sample com.ravenzip.workshop.samples.forms.group.LoginForm
 * @sample com.ravenzip.workshop.samples.forms.group.AddressForm
 * @sample com.ravenzip.workshop.samples.forms.group.createFormGroupSample
 */
// TODO Попробовать реализовать value формы (возможно ли?)
// TODO Попробовать реализовать setValue формы (возможно ли?)
@Experimental
open class FormGroup<T : Any>(val controls: T) {
    private val _controls = mutableListOf<AbstractFormControl>()

    init {
        validateControls(controls)
    }

    /**
     * Валидация FormGroup при ее создании
     *
     * Проверяем, что объявленные в controls поля принадлежат AbstractFormControl или FormGroup
     */
    private fun validateControls(obj: Any, path: String = "") {
        val controls = obj::class

        for (property in controls.declaredMemberProperties) {
            val controlsClassifier = property.returnType.classifier

            if (controlsClassifier is KClass<*>) {
                property.isAccessible = true

                val value = property.getter.call(obj) ?: continue
                if (DEFAULT_FORM_GROUP_FIELDS.contains(property.name)) {
                    continue
                }
                println(property.name)

                val fullPath = "$path.${property.name}".trimStart('.')

                when (value) {
                    is AbstractFormControl -> _controls.add(value)
                    is FormGroup<*> -> validateControls(value, fullPath)
                    else ->
                        throw IllegalArgumentException(
                            "Invalid control at '$fullPath': ${value::class.simpleName}"
                        )
                }
            }
        }
    }

    fun reset() {
        _controls.forEach { control -> control.reset() }
    }

    fun disable() {
        _controls.forEach { control -> control.disable() }
    }

    fun enable() {
        _controls.forEach { control -> control.enable() }
    }

    @Stable
    val isValid
        get() = _controls.all { control -> control.isValid }

    @Stable
    val isInvalid
        get() = _controls.any { controls -> controls.isInvalid }

    @Stable
    val errorMessages
        get() =
            _controls
                .filter { controls -> controls.isInvalid }
                .map { controls -> controls.errorMessage }

    @Stable
    val errorMessage
        get() = _controls.firstOrNull { controls -> controls.isInvalid }?.errorMessage ?: ""
}
