package com.thoughtworks.ark.ui.component

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.thoughtworks.ark.ui.theme.Theme
import com.thoughtworks.ark.ui.theme.color.ExtendedColors

object AppButtonDefault {
    @Composable
    fun textButtonColors(
        backgroundColor: Color = ExtendedColors.ButtonBackground.color(),
        contentColor: Color = ExtendedColors.ButtonContent.color(),
        disabledContentColor: Color = Theme.colors.onSurface
            .copy(alpha = ContentAlpha.disabled),
    ) = ButtonDefaults.textButtonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor
    )
}