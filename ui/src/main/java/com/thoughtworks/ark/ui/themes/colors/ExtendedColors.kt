package com.thoughtworks.ark.ui.themes.colors

import android.content.Context
import androidx.annotation.ColorRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.thoughtworks.ark.ui.R

sealed class ExtendedColors(
    @ColorRes val colorRes: Int,
) {

    object ButtonBackground : ExtendedColors(R.color.button_background)
    object ButtonContent : ExtendedColors(R.color.button_content)
    object ErrorOutLine : ExtendedColors(R.color.error_out_line)

    @Composable
    fun color() =
        Color(LocalContext.current.getColor(colorRes))

    @Composable
    fun color(alpha: Float) =
        color().run {
            copy(alpha * this.alpha)
        }

    fun alphaColorInt(context: Context, alpha: Float): Int {
        return Color(colorInt(context)).run {
            copy(alpha * this.alpha)
        }.toArgb()
    }

    fun colorInt(context: Context) =
        context.getColor(colorRes)


    fun hex(context: Context) =
        String.format(HEX_FORMAT, Color.White.toArgb() and colorInt(context))

    companion object {
        private const val HEX_FORMAT = "%08X"
    }
}