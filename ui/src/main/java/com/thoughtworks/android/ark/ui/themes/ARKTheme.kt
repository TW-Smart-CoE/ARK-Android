package com.thoughtworks.android.ark.ui.themes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.thoughtworks.android.ark.ui.themes.colors.*

@Composable
fun ARKTheme(content: @Composable () -> Unit) {
    val arkThemeColors = ARKThemeAttrColors().toARKColors()

    CompositionLocalProvider(
        LocalARKThemeColors provides arkThemeColors,
        content = content
    )
}

object ARKTheme {
    val colors: ARKThemeColors
        @Composable
        get() = LocalARKThemeColors.current
}

