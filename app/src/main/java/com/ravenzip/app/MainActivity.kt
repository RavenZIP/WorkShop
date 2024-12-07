package com.ravenzip.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ravenzip.app.ui.theme.WorkShopTheme
import com.ravenzip.workshop.components.DropDownTextField
import com.ravenzip.workshop.forms.DropDownFieldState
import com.ravenzip.workshop.samples.model.Item

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkShopTheme {
                Scaffold { padding ->
                    Column(
                        modifier = Modifier.fillMaxSize().padding(padding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        TextFields()
                    }
                }
            }
        }
    }
}

@Composable
fun TextFields() {
    val items = remember { Item.createItems() }

    val dropDownFieldFormState = remember {
        DropDownFieldState(
            initialValue = items.first(),
            resetValue = Item.createEmptyModel(),
            items = items,
            itemsView = { it.name },
        )
    }

    Text("DROPDOWN IS: ${dropDownFieldFormState.value.name.ifEmpty { "EMPTY" }}")

    DropDownTextField(state = dropDownFieldFormState, label = "Выпадающий список с форм стейтом")
}
