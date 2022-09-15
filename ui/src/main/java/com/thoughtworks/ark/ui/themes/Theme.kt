package com.thoughtworks.ark.ui.themes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.thoughtworks.ark.ui.themes.colors.AttrColors
import com.thoughtworks.ark.ui.themes.colors.Colors
import com.thoughtworks.ark.ui.themes.colors.LocalColors
import com.thoughtworks.ark.ui.themes.colors.toColors

@Composable
fun Theme(content: @Composable () -> Unit) {
    val appThemeColors = AttrColors().toColors()

    CompositionLocalProvider(
        LocalColors provides appThemeColors,
        content = content
    )
}

object Theme {
    val colors: Colors
        @Composable
        get() = LocalColors.current
}

