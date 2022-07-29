package com.thoughtworks.android.ark.ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


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
    val colors: ARKColors
        @Composable get() = LocaleARKColors.current
}

