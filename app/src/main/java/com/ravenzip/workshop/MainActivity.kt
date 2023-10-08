package com.ravenzip.workshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ravenzip.workshop.ui.theme.WorkShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SimpleButton(text = "Простая кнопка", textSize = 16, TextAlign.Start) {}

                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        SimpleButtonWithIcon(
                            text = "Кнопка с иконкой слева",
                            textSize = 16,
                            icon = Icons.Outlined.FavoriteBorder,
                        ) {}
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        SimpleButtonWithIcon(
                            text = "Кнопка с иконкой справа",
                            textSize = 16,
                            icon = Icons.Outlined.FavoriteBorder,
                            iconPositionIsLeft = false
                        ) {}
                        Spacer(modifier = Modifier.padding(top = 20.dp))
                        SimpleButtonWithTitle(
                            title = "Кнопка с заголовком и описанием",
                            titleSize = 16,
                            text = "Описание",
                            textSize = 14
                        ) {}
                    }
                }
            }
        }
    }
}

/** Простая кнопка с текстом */
@Composable
fun SimpleButton(
    text: String,
    textSize: Int,
    textAlign: TextAlign,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(15),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(0.9f),
        colors = colors,
        shape = shape
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp),
            fontSize = textSize.sp,
            fontWeight = FontWeight.Medium,
            textAlign = textAlign
        )
    }
}

/** Кнопка с заголовком и описанием */
@Composable
fun SimpleButtonWithTitle(
    title: String,
    titleSize: Int,
    text: String,
    textSize: Int,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(15),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(0.9f),
        colors = colors,
        shape = shape
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = title, fontSize = titleSize.sp, fontWeight = FontWeight.Bold)
            Text(text = text, fontSize = textSize.sp, fontWeight = FontWeight.Medium)
        }
    }
}

/** Кнопка с иконкой и текстом */
@Composable
fun SimpleButtonWithIcon(
    text: String,
    textSize: Int,
    icon: ImageVector,
    contentDescription: String = "",
    iconPositionIsLeft: Boolean = true,
    iconRightAtTheEnd: Boolean = false,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    shape: Shape = RoundedCornerShape(15),
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(0.9f),
        colors = colors,
        shape = shape
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 10.dp)) {
            if (iconPositionIsLeft) {
                Icon(imageVector = icon, contentDescription = contentDescription)
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = textSize.sp,
                    fontWeight = FontWeight.Medium
                )
            } else {
                Text(
                    text = text,
                    modifier =
                        if (iconRightAtTheEnd) Modifier.fillMaxWidth(0.9f).padding(end = 10.dp)
                        else Modifier.padding(end = 10.dp),
                    fontSize = textSize.sp,
                    fontWeight = FontWeight.Medium
                )
                Icon(imageVector = icon, contentDescription = contentDescription)
            }
        }
    }
}
