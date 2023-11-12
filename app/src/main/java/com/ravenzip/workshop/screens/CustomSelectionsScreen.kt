package com.ravenzip.workshop.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ravenzip.workshop.components.BottomAppBar
import com.ravenzip.workshop.components.CheckBoxes
import com.ravenzip.workshop.components.CheckBoxesTree
import com.ravenzip.workshop.components.RadioGroup
import com.ravenzip.workshop.components.Switch
import com.ravenzip.workshop.components.TopAppBar_v2
import com.ravenzip.workshop.data.RootParameters
import com.ravenzip.workshop.data.SelectionParameters
import com.ravenzip.workshop.data.TextParameters

@SuppressLint("UnrememberedMutableState")
@Composable
fun SelectionElementsScreen(navController: NavController) {
    val isChecked = mutableStateOf(false)
    val radioList =
        mutableStateListOf(
            SelectionParameters(isSelected = true, text = "Первая"),
            SelectionParameters(isSelected = false, text = "Вторая"),
            SelectionParameters(isSelected = false, text = "Третья")
        )

    val checkBoxList =
        mutableStateListOf(
            SelectionParameters(isSelected = false, text = "Первый"),
            SelectionParameters(isSelected = false, text = "Второй"),
            SelectionParameters(isSelected = false, text = "Третий")
        )

    val triState = mutableStateOf(ToggleableState.Off)
    val checkBoxList2 =
        mutableStateListOf(
            SelectionParameters(isSelected = false, text = "Первый"),
            SelectionParameters(isSelected = false, text = "Второй"),
            SelectionParameters(isSelected = false, text = "Третий")
        )

    Scaffold(
        topBar = { TopAppBar_v2("Элементы выбора", backArrow = true, isMenu = false) },
        bottomBar = { BottomAppBar(navController = navController, buttonsList = bottomBarButtons) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 30.dp))

            Text(text = "Switch", fontSize = 25.sp)
            Spacer(modifier = Modifier.padding(top = 20.dp))

            Switch(
                isChecked = isChecked,
                title = TextParameters(value = "Заголовок", size = 18),
                text = TextParameters(value = "Описание", size = 14)
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))

            Text(text = "RadioButtons", fontSize = 25.sp)
            Spacer(modifier = Modifier.padding(top = 20.dp))

            RadioGroup(list = radioList)
            Spacer(modifier = Modifier.padding(top = 20.dp))

            Text(text = "CheckBoxes", fontSize = 25.sp)
            Spacer(modifier = Modifier.padding(top = 20.dp))

            CheckBoxes(list = checkBoxList)
            Spacer(modifier = Modifier.padding(top = 20.dp))

            CheckBoxesTree(
                root =
                    RootParameters(
                        state = triState,
                        text = "Главный",
                        colors = CheckboxDefaults.colors()
                    ),
                list = checkBoxList2
            )
        }
    }
}
