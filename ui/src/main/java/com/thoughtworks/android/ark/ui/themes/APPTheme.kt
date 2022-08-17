package com.thoughtworks.android.ark.ui.themes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.thoughtworks.android.ark.ui.themes.colors.*

@Composable
fun APPTheme(content: @Composable () -> Unit) {
    val appThemeColors = APPThemeAttrColors().toARKColors()

    CompositionLocalProvider(
        LocalAPPThemeColors provides appThemeColors,
        content = content
    )
}

object APPTheme {
    val colors: APPThemeColors
        @Composable
        get() = LocalAPPThemeColors.current
}

