package com.example.effectiveacademy.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = MarvelDarkRed,
    secondary = MarvelDarkBlue,
    tertiary = MarvelLightText,
    background = MarvelDarkBackground,
    surface = MarvelDarkBackground,
    onPrimary = MarvelLightText,
    onSecondary = MarvelLightText,
    onTertiary = MarvelDarkBackground,
    onBackground = MarvelLightText,
    onSurface = MarvelLightText
)

private val LightColorScheme = lightColorScheme(
    primary = MarvelRed,
    secondary = MarvelBlue,
    tertiary = MarvelDarkText,
    background = MarvelLightBackground,
    surface = MarvelLightBackground,
    onPrimary = MarvelLightText,
    onSecondary = MarvelLightText,
    onTertiary = MarvelLightBackground,
    onBackground = MarvelDarkText,
    onSurface = MarvelDarkText
)

@Composable
fun EffectiveAcademyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val controller = WindowCompat.getInsetsController(window, view)

            // Убираем заполнение у системных панелей
            WindowCompat.setDecorFitsSystemWindows(window, false)

            // Устанавливаем прозрачный цвет навбару и статусбару
            window.navigationBarColor = Color.Transparent.toArgb()
            window.statusBarColor = Color.Transparent.toArgb()

            // Настройка цвета иконок (тёмные на светлой теме)
            //На прошивках кастомных вроде EMUI и т.д. невозможно добиться идеального прозрачного
            //статус бара (если я правильно понял) без хитростей. Для этого я не меняю его исходя из
            //темы устройства, а всегда оставляю темным и задаю прозрачность
            controller?.isAppearanceLightNavigationBars = false
            controller?.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}