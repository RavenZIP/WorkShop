package com.ravenzip.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.app.ui.theme.WorkShopTheme
import com.ravenzip.workshop.components.CheckBoxGroup
import com.ravenzip.workshop.components.DropDownTextField
import com.ravenzip.workshop.components.SimpleButton
import com.ravenzip.workshop.components.SinglenessOutlinedTextField
import com.ravenzip.workshop.forms.Validators
import com.ravenzip.workshop.forms.components.CheckBoxGroupComponent
import com.ravenzip.workshop.forms.control.FormControl
import com.ravenzip.workshop.forms.control.FormControlMulti
import com.ravenzip.workshop.forms.dropdown.DropDownTextFieldComponent
import com.ravenzip.workshop.forms.dropdown.DropDownTextFieldState
import com.ravenzip.workshop.forms.textfield.TextFieldComponent
import com.ravenzip.workshop.forms.textfield.TextFieldState
import com.ravenzip.workshop.samples.model.Item

internal enum class Screen {
    MAIN,
    DROP_DOWN_TEXT_FIELD,
    SIMPLE_OUTLINED_TEXT_FIELD,
    CHECK_BOX_GROUP,
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkShopTheme {
                val screen = remember { mutableStateOf(Screen.MAIN) }

                Scaffold { padding ->
                    Column(
                        modifier = Modifier.fillMaxSize().padding(padding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))

                        when (screen.value) {
                            Screen.MAIN -> MainScreen(screen)
                            Screen.DROP_DOWN_TEXT_FIELD -> DropDownTextFieldTest(screen)
                            Screen.SIMPLE_OUTLINED_TEXT_FIELD -> TextFields(screen)
                            Screen.CHECK_BOX_GROUP -> CheckBoxGroupTest(screen)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MainScreen(screen: MutableState<Screen>) {
    SimpleButton(text = "DropDownTextField") { screen.value = Screen.DROP_DOWN_TEXT_FIELD }

    Spacer(modifier = Modifier.height(10.dp))

    SimpleButton(text = "SimpleOutlinedTextField") {
        screen.value = Screen.SIMPLE_OUTLINED_TEXT_FIELD
    }

    Spacer(modifier = Modifier.height(10.dp))

    SimpleButton(text = "CheckBoxGroup") { screen.value = Screen.CHECK_BOX_GROUP }

    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
private fun DropDownTextFieldTest(screen: MutableState<Screen>) {
    Text("DropDownTextField", fontSize = 24.sp, fontWeight = FontWeight.W500)
    Spacer(modifier = Modifier.height(20.dp))

    val composableScope = rememberCoroutineScope()

    val items = remember { Item.createItems() }

    val formControl = remember {
        FormControl(
            initialValue = items.first(),
            resetValue = Item.createEmptyModel(),
            validators = listOf { value -> Validators.required(value.name) },
        )
    }

    val formState = remember { DropDownTextFieldState(source = items, sourceView = { it.name }) }

    DropDownTextField(
        component =
            DropDownTextFieldComponent(
                control = formControl,
                state = formState,
                scope = composableScope,
            )
    )

    Spacer(modifier = Modifier.height(10.dp))

    DropDownTextFieldInfo(formControl, formState)

    Spacer(modifier = Modifier.height(10.dp))

    SimpleButton(text = "Назад") { screen.value = Screen.MAIN }
}

@Composable
private fun TextFields(screen: MutableState<Screen>) {
    Text("SinglenessOutlinedTextField", fontSize = 24.sp, fontWeight = FontWeight.W500)
    Spacer(modifier = Modifier.height(20.dp))

    val formControl = remember {
        FormControl(
            initialValue = "",
            validators =
                listOf { value -> if (value.isEmpty()) "Поле не может быть пустым" else null },
        )
    }

    val formState = remember { TextFieldState() }

    val composableScope = rememberCoroutineScope()

    Text("TEXTFIELD IS: ${formControl.value.ifEmpty { "EMPTY" }}")

    Spacer(modifier = Modifier.height(10.dp))

    SinglenessOutlinedTextField(
        TextFieldComponent(control = formControl, state = formState, scope = composableScope)
    )

    Spacer(modifier = Modifier.height(10.dp))
    SimpleButton(text = "Отключить") { formControl.disable() }

    Spacer(modifier = Modifier.height(10.dp))
    SimpleButton(text = "Включить") { formControl.enable() }

    Spacer(modifier = Modifier.height(10.dp))
    SimpleButton(text = "Только для чтения") { formState.readonly() }

    Spacer(modifier = Modifier.height(10.dp))
    SimpleButton(text = "Разрешить редактирование") { formState.editable() }

    Spacer(modifier = Modifier.height(10.dp))
    SimpleButton(text = "Сброс") { formControl.reset() }

    Spacer(modifier = Modifier.height(10.dp))
    SimpleButton(text = "Назад") { screen.value = Screen.MAIN }
}

@Composable
private fun CheckBoxGroupTest(screen: MutableState<Screen>) {
    val items = remember { Item.createItems() }
    val control = remember { FormControlMulti(initialValue = emptyList<Item>()) }
    val component = remember {
        CheckBoxGroupComponent(control = control, items, view = { it.name })
    }

    CheckBoxGroup(component = component)

    //    val state =
    //        CheckBoxTreeComponent(
    //            children = FormControlMulti(emptyList(), comparableKey = { it.name }),
    //            source = items,
    //            view = { it.name },
    //        )
    //
    //    CheckboxTree(component = state, parentText = "Дерево чекбоксов")

    //    val state2 = remember { FormControl(initialValue = Item.createItem()) }
    //    Spacer(modifier = Modifier.height(10.dp))
    //
    //    ChipRadioGroup(
    //        state = state2,
    //        source = items,
    //        viewOptions =
    //            items.associate { item ->
    //                item.name to
    //                    ChipViewOptions(
    //                        text = item.name,
    //                        textConfig = TextConfig.SmallMedium,
    //                        icon = IconData.ResourceIcon(R.drawable.ic_launcher_foreground),
    //                        iconConfig = IconConfig.PrimarySmall,
    //                    )
    //            },
    //        comparableKey = { it.name },
    //    )
}

@Composable
fun DropDownTextFieldInfo(control: FormControl<Item>, state: DropDownTextFieldState<Item>) {
    Card(modifier = Modifier.fillMaxWidth(0.9f)) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text("Выбранное значение: ${control.value.name.ifEmpty { "Пусто" }}")
            Text("Введенное значение: ${state.text.ifEmpty { "Пусто" }}")
            Text("Включено: ${if (control.isEnabled) "Да" else "Нет"}")
            Text("Выключено: ${if (control.isDisabled) "Да" else "Нет"}")
            Text("Валидно: ${if (control.isValid) "Да" else "Нет"}")
            Text("Невалидно: ${if (control.isInvalid) "Да" else "Нет"}")
            Text("Только для чтения: ${if (state.isReadonly) "Да" else "Нет"}")
            Text("Изменяемый: ${if (state.isEditable) "Да" else "Нет"}")
            Text("Открыто: ${if (state.expanded) "Да" else "Нет"}")
            Text("В фокусе: ${if (state.isFocused) "Да" else "Нет"}")
        }
    }
}
