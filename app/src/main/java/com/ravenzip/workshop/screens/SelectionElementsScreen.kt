package com.ravenzip.workshop.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.components.CheckBoxes
import com.ravenzip.workshop.components.ComponentInfo
import com.ravenzip.workshop.components.RadioGroup
import com.ravenzip.workshop.components.SimpleSwitch

@SuppressLint("UnrememberedMutableState")
@Composable
fun SelectionElementsScreen() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val isChecked = mutableStateOf(false)
        val radioList = mutableStateListOf<ComponentInfo>()
        val radioButton1 = ComponentInfo(isSelected = true, text = "Первая")
        val radioButton2 = ComponentInfo(isSelected = false, text = "Вторая")
        val radioButton3 = ComponentInfo(isSelected = false, text = "Третья")
        radioList.addAll(arrayListOf(radioButton1, radioButton2, radioButton3))

        Text(text = "Элементы выбора", fontSize = 30.sp)
        Spacer(modifier = Modifier.padding(top = 50.dp))

        Text(text = "Switch", fontSize = 25.sp)
        Spacer(modifier = Modifier.padding(top = 20.dp))

        SimpleSwitch(isChecked = isChecked, title = "Заголовок", text = "Описание")
        Spacer(modifier = Modifier.padding(top = 20.dp))

        Text(text = "RadioButtons", fontSize = 25.sp)
        Spacer(modifier = Modifier.padding(top = 20.dp))

        RadioGroup(radioButtons = radioList)
        Spacer(modifier = Modifier.padding(top = 20.dp))

        Text(text = "CheckBoxes", fontSize = 25.sp)
        Spacer(modifier = Modifier.padding(top = 20.dp))

        CheckBoxes()
    }
}
