package com.ravenzip.workshop.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.components.CheckBoxes
import com.ravenzip.workshop.components.CheckBoxesTree
import com.ravenzip.workshop.components.ComponentInfo
import com.ravenzip.workshop.components.RadioGroup
import com.ravenzip.workshop.components.Switch

@SuppressLint("UnrememberedMutableState")
@Composable
fun SelectionElementsScreen() {
    Column(
        modifier =
            Modifier.fillMaxWidth().padding(top = 20.dp).verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val isChecked = mutableStateOf(false)
        val radioList = mutableStateListOf<ComponentInfo>()
        val radioButton1 = ComponentInfo(isSelected = true, text = "Первая")
        val radioButton2 = ComponentInfo(isSelected = false, text = "Вторая")
        val radioButton3 = ComponentInfo(isSelected = false, text = "Третья")
        radioList.addAll(arrayListOf(radioButton1, radioButton2, radioButton3))

        val checkBoxList = mutableStateListOf<ComponentInfo>()
        val checkbox1 = ComponentInfo(isSelected = false, text = "Первый")
        val checkbox2 = ComponentInfo(isSelected = false, text = "Второй")
        val checkbox3 = ComponentInfo(isSelected = false, text = "Третий")
        checkBoxList.addAll(arrayListOf(checkbox1, checkbox2, checkbox3))

        val triState = mutableStateOf(ToggleableState.Off)
        val checkBoxList2 = mutableStateListOf<ComponentInfo>()
        val checkbox4 = ComponentInfo(isSelected = false, text = "Первый")
        val checkbox5 = ComponentInfo(isSelected = false, text = "Второй")
        val checkbox6 = ComponentInfo(isSelected = false, text = "Третий")
        checkBoxList2.addAll(arrayListOf(checkbox4, checkbox5, checkbox6))

        Text(text = "Элементы выбора", fontSize = 30.sp)
        Spacer(modifier = Modifier.padding(top = 50.dp))

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

        CheckBoxesTree(triState = triState, triText = "Главный", list = checkBoxList2)
    }
}
