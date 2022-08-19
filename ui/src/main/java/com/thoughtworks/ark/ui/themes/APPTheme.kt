package com.thoughtworks.ark.ui.themes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.thoughtworks.ark.ui.themes.colors.APPThemeAttrColors
import com.thoughtworks.ark.ui.themes.colors.APPThemeColors
import com.thoughtworks.ark.ui.themes.colors.LocalAPPThemeColors
import com.thoughtworks.ark.ui.themes.colors.toARKColors

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

