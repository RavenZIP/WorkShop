package com.ravenzip.workshop.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val darkColorScheme =
    darkColorScheme(
        primary = defaultDarkPrimary,
        onPrimary = defaultDarkOnPrimary,
        secondary = defaultDarkSecondary
    )

private val lightColorScheme =
    lightColorScheme(
        primary = defaultLightPrimary,
        onPrimary = defaultLightOnPrimary,
        secondary = defaultLightSecondary

        /* Other default colors to override
        background = Color(0xFFFFFBFE),
        surface = Color(0xFFFFFBFE),
        onPrimary = Color.White,
        onSecondary = Color.White,
        onTertiary = Color.White,
        onBackground = Color(0xFF1C1B1F),
        onSurface = Color(0xFF1C1B1F),
        */
    )

@Composable
fun WorkShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    // dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme =
        when {
            // Динамические темы. Для нормального функционирования нужна еще одна переменная
            // дополнительно регулирующая вкл\выкл данной фичи
            //        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            //            val context = LocalContext.current
            //            if (darkTheme) dynamicDarkColorScheme(context) else
            // dynamicLightColorScheme(context)
            //        }

            darkTheme -> darkColorScheme
            else -> lightColorScheme
        }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Цвет фона статус бара
            window.statusBarColor = colorScheme.onPrimary.toArgb()
            // Цвет нижней навигационной панели
            window.navigationBarColor = colorScheme.onPrimary.toArgb()
            // Цвет иконок в статус баре
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
