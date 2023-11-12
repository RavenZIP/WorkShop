package com.ravenzip.workshop.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
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
        primaryContainer = defaultLightPrimaryContainer,
        onPrimaryContainer = defaultLightOnPrimaryContainer,
        secondary = defaultLightSecondary,
        onSecondary = defaultLightOnPrimary,
        secondaryContainer = defaultLightSecondaryContainer,
        onSecondaryContainer = defaultLightOnSecondaryContainer,
        tertiary = defaultLightTertiary,
        onTertiary = defaultLightOnPrimary,
        tertiaryContainer = defaultLightTertiaryContainer,
        onTertiaryContainer = defaultLightOnTertiaryContainer,
        error = defaultLightError,
        onError = defaultLightOnPrimary,
        errorContainer = defaultLightErrorContainer,
        onErrorContainer = defaultLightOnErrorContainer,
        surface = defaultLightSurface,
        onSurfaceVariant = defaultLightOnSurfaceVariant,
        surfaceContainer = defaultLightSurfaceContainer,
        onSurface = defaultLightOnSurface,
        background = defaultLightSurface
    )

@Composable
fun WorkShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme =
        when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }
            darkTheme -> darkColorScheme
            else -> lightColorScheme
        }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Цвет фона статус бара
            window.statusBarColor = colorScheme.surfaceContainer.toArgb()
            // Цвет нижней навигационной панели
            window.navigationBarColor = colorScheme.surfaceContainer.toArgb()
            // Цвет иконок в статус баре
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(10.dp)),
        typography = Typography,
        content = content
    )
}
