package com.ravenzip.app

import android.os.Bundle
import android.util.Log
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.app.ui.theme.WorkShopTheme
import com.ravenzip.workshop.components.CheckboxTree
import com.ravenzip.workshop.components.ChipRadioGroup
import com.ravenzip.workshop.components.DropDownTextField
import com.ravenzip.workshop.components.SimpleButton
import com.ravenzip.workshop.components.SinglenessOutlinedTextField
import com.ravenzip.workshop.data.ChipGroupItem
import com.ravenzip.workshop.data.TextConfig
import com.ravenzip.workshop.data.icon.Icon
import com.ravenzip.workshop.data.icon.IconConfig
import com.ravenzip.workshop.data.selection.SelectableChipConfig
import com.ravenzip.workshop.forms.Validators
import com.ravenzip.workshop.forms.state.FormState
import com.ravenzip.workshop.forms.state.special.CheckBoxTreeFormState
import com.ravenzip.workshop.forms.state.special.DropDownTextFieldState
import com.ravenzip.workshop.forms.state.special.TextFieldState
import com.ravenzip.workshop.samples.model.Item

internal enum class Screen {
    MAIN,
    DROP_DOWN_TEXT_FIELD,
    SIMPLE_OUTLINED_TEXT_FIELD,
    CHECK_BOX_GROUP,
}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
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

    val items = remember { Item.createItems() }

    val dropDownFieldFormState = remember {
        DropDownTextFieldState(
            initialValue = items.first(),
            resetValue = Item.createEmptyModel(),
            items = items,
            itemsView = { it.name },
            validators = listOf { value -> Validators.required(value.name) },
        )
    }

    DropDownTextField(state = dropDownFieldFormState)

    Spacer(modifier = Modifier.height(10.dp))

    DropDownTextFieldInfo(dropDownFieldFormState)

    Spacer(modifier = Modifier.height(10.dp))

    SimpleButton(text = "Назад") { screen.value = Screen.MAIN }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextFields(screen: MutableState<Screen>) {
    Text("SinglenessOutlinedTextField", fontSize = 24.sp, fontWeight = FontWeight.W500)
    Spacer(modifier = Modifier.height(20.dp))

    val textFieldState = remember {
        TextFieldState(
            initialValue = "",
            validators =
                listOf { value -> if (value.isEmpty()) "Поле не может быть пустым" else null },
        )
    }

    Text("TEXTFIELD IS: ${textFieldState.value.ifEmpty { "EMPTY" }}")

    Spacer(modifier = Modifier.height(10.dp))

    SinglenessOutlinedTextField(textFieldState)

    Spacer(modifier = Modifier.height(10.dp))
    SimpleButton(text = "Отключить") { textFieldState.disable() }

    Spacer(modifier = Modifier.height(10.dp))
    SimpleButton(text = "Включить") { textFieldState.enable() }

    Spacer(modifier = Modifier.height(10.dp))
    SimpleButton(text = "Только для чтения") { textFieldState.readonly() }

    Spacer(modifier = Modifier.height(10.dp))
    SimpleButton(text = "Разрешить редактирование") { textFieldState.editable() }

    Spacer(modifier = Modifier.height(10.dp))
    SimpleButton(text = "Сброс") { textFieldState.reset() }

    Spacer(modifier = Modifier.height(10.dp))
    SimpleButton(text = "Назад") { screen.value = Screen.MAIN }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CheckBoxGroupTest(screen: MutableState<Screen>) {
    val items = remember { Item.createItems() }

    val state =
        CheckBoxTreeFormState(
            emptyList(),
            items,
            comparableKey = { it.name },
            sourceView = { it.name },
        )

    val state2 = remember { FormState(initialValue = Item.createItem()) }

    CheckboxTree(state = state, parentText = "Дерево чекбоксов")

    Spacer(modifier = Modifier.height(10.dp))

    ChipRadioGroup(
        state = state2,
        source =
            items.map { item ->
                ChipGroupItem(
                    value = item,
                    icon = Icon.ResourceIcon(R.drawable.ic_launcher_foreground),
                )
            },
        comparableKey = { it.name },
        view = { it.name },
    )

    ChipRadioGroup(
        list =
            items
                .mapIndexed { key, item ->
                    SelectableChipConfig(
                        isSelected = key == 0,
                        text = item.name,
                        textConfig = TextConfig.SmallMedium,
                        icon = Icon.ResourceIcon(R.drawable.ic_launcher_foreground),
                        iconConfig = IconConfig.PrimarySmall,
                    )
                }
                .toMutableStateList()
    )

    LaunchedEffect(Unit) {
        state.valueChanges.collect { Log.d("valueChanges", "valueChanges: $it") }
    }
}

@Composable
fun DropDownTextFieldInfo(state: DropDownTextFieldState<Item>) {
    Card(modifier = Modifier.fillMaxWidth(0.9f)) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text("Выбранное значение: ${state.value.name.ifEmpty { "Пусто" }}")
            Text("Введенное значение: ${state.text.ifEmpty { "Пусто" }}")
            Text("Включено: ${if (state.isEnabled) "Да" else "Нет"}")
            Text("Выключено: ${if (state.isDisabled) "Да" else "Нет"}")
            Text("Валидно: ${if (state.isValid) "Да" else "Нет"}")
            Text("Невалидно: ${if (state.isInvalid) "Да" else "Нет"}")
            Text("Только для чтения: ${if (state.isReadonly) "Да" else "Нет"}")
            Text("Изменяемый: ${if (state.isEditable) "Да" else "Нет"}")
            Text("Открыто: ${if (state.expanded) "Да" else "Нет"}")
            Text("В фокусе: ${if (state.isFocused) "Да" else "Нет"}")
        }
    }
}
