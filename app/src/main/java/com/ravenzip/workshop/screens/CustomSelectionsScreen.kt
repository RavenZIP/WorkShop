package com.ravenzip.workshop.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.ravenzip.workshop.components.BottomAppBar
import com.ravenzip.workshop.components.CheckBoxes
import com.ravenzip.workshop.components.CheckBoxesTree
import com.ravenzip.workshop.components.ComponentInfo
import com.ravenzip.workshop.components.RadioGroup
import com.ravenzip.workshop.components.Switch
import com.ravenzip.workshop.components.TopAppBar

@SuppressLint("UnrememberedMutableState")
@Composable
fun SelectionElementsScreen() {
    val isChecked = mutableStateOf(false)
    val radioList =
        mutableStateListOf(
            ComponentInfo(isSelected = true, text = "Первая"),
            ComponentInfo(isSelected = false, text = "Вторая"),
            ComponentInfo(isSelected = false, text = "Третья")
        )

    val checkBoxList =
        mutableStateListOf(
            ComponentInfo(isSelected = false, text = "Первый"),
            ComponentInfo(isSelected = false, text = "Второй"),
            ComponentInfo(isSelected = false, text = "Третий")
        )

    val triState = mutableStateOf(ToggleableState.Off)
    val checkBoxList2 =
        mutableStateListOf(
            ComponentInfo(isSelected = false, text = "Первый"),
            ComponentInfo(isSelected = false, text = "Второй"),
            ComponentInfo(isSelected = false, text = "Третий")
        )

    Scaffold(
        topBar = { TopAppBar("Элементы выбора", backArrow = true) },
        bottomBar = { BottomAppBar() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 30.dp))

            Text(text = "Switch", fontSize = 25.sp)
            Spacer(modifier = Modifier.padding(top = 20.dp))

            Switch(isChecked = isChecked, title = "Заголовок", text = "Описание")
            Spacer(modifier = Modifier.padding(top = 20.dp))

            Text(text = "RadioButtons", fontSize = 25.sp)
            Spacer(modifier = Modifier.padding(top = 20.dp))

            RadioGroup(list = radioList)
            Spacer(modifier = Modifier.padding(top = 20.dp))

            Text(text = "CheckBoxes", fontSize = 25.sp)
            Spacer(modifier = Modifier.padding(top = 20.dp))

            CheckBoxes(list = checkBoxList)
            Spacer(modifier = Modifier.padding(top = 20.dp))

            CheckBoxesTree(rootState = triState, rootText = "Главный", list = checkBoxList2)
        }
    }
}
