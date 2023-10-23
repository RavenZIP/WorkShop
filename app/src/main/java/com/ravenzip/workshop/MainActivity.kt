package com.ravenzip.workshop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ravenzip.workshop.screens.CustomButtonsScreen
import com.ravenzip.workshop.screens.CustomTextFieldsScreen
import com.ravenzip.workshop.screens.Menu
import com.ravenzip.workshop.screens.OtherElementsScreen
import com.ravenzip.workshop.ui.theme.WorkShopTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "menu") {
                        composable("menu") { Menu(navController) }
                        composable("buttons") { CustomButtonsScreen() }
                        composable("text fields") { CustomTextFieldsScreen() }
                        composable("other elements") { OtherElementsScreen() }
                    }
                }
            }
        }
    }
}
