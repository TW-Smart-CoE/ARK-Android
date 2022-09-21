package com.thoughtworks.ark.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.thoughtworks.ark.ui.theme.color.AttrColors
import com.thoughtworks.ark.ui.theme.color.Colors
import com.thoughtworks.ark.ui.theme.color.LocalColors
import com.thoughtworks.ark.ui.theme.color.toColors
import com.thoughtworks.ark.ui.theme.typography.LocalTypography
import com.thoughtworks.ark.ui.theme.typography.Typographies
import com.thoughtworks.ark.ui.theme.typography.generateTypographyFromAttributes

@Composable
fun Theme(content: @Composable () -> Unit) {
    val colors = AttrColors().toColors()
    val types = generateTypographyFromAttributes()

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides types,
        content = content
    )
}

object Theme {
    val colors: Colors
        @Composable
        get() = LocalColors.current
    val types: Typographies
        @Composable
        get() = LocalTypography.current
}

