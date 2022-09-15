package com.thoughtworks.ark.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.thoughtworks.ark.ui.theme.color.AttrColors
import com.thoughtworks.ark.ui.theme.color.Colors
import com.thoughtworks.ark.ui.theme.color.LocalColors
import com.thoughtworks.ark.ui.theme.color.toColors

@Composable
fun Theme(content: @Composable () -> Unit) {
    val colors = AttrColors().toColors()

    CompositionLocalProvider(
        LocalColors provides colors,
        content = content
    )
}

object Theme {
    val colors: Colors
        @Composable
        get() = LocalColors.current
}

