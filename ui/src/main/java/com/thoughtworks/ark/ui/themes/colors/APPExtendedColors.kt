package com.thoughtworks.ark.ui.themes.colors

import android.content.Context
import androidx.annotation.ColorRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.thoughtworks.ark.ui.R

sealed class APPExtendedColors(
    @ColorRes val colorRes: Int
) {

    object ButtonBackground : APPExtendedColors(R.color.button_background)
    object ButtonContent : APPExtendedColors(R.color.button_content)
    object ErrorOutLineColor : APPExtendedColors(R.color.error_out_line)

    @Composable
    fun colorValue() =
        Color(LocalContext.current.getColor(colorRes))

    @Composable
    fun alphaColor(alphaValue: Float) =
        colorValue().let {
            it.copy(alphaValue * it.alpha)
        }

    fun alphaColorInt(context: Context, alphaValue: Float): Int {
        return Color(colorInt(context)).let {
            it.copy(alphaValue * it.alpha)
        }.toArgb()
    }

    fun colorInt(context: Context) =
        context.getColor(colorRes)


    fun hex(context: Context) =
        colorInt(context).let {
            String.format("%08X", (0XFFFFFFFF and it.toLong()))
        }
}