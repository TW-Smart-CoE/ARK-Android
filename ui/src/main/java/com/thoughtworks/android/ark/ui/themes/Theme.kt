package com.thoughtworks.android.ark.ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.thoughtworks.android.ark.ui.themes.colors.ARKThemeColors
import com.thoughtworks.android.ark.ui.themes.colors.LocaleARKColors
import com.thoughtworks.android.ark.ui.themes.colors.darkColors
import com.thoughtworks.android.ark.ui.themes.colors.lightColors

@Composable
fun AndroidARKTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        darkColors
    } else {
        lightColors
    }

    CompositionLocalProvider(
        LocaleARKColors provides colors, content = content
    )
}

object AndroidARKTheme {
    val colors: ARKThemeColors
        @Composable get() = LocaleARKColors.current
}

