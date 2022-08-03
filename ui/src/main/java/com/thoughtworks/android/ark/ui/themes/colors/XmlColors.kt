package com.thoughtworks.android.ark.ui.themes.colors

import android.content.Context
import androidx.annotation.ColorRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.thoughtworks.android.ark.ui.R
import java.util.*

sealed class XmlColors(
    @ColorRes private val colorRes: Int
) : ARKColor {

    object ButtonBackground : XmlColors(R.color.button_background)
    object ButtonContent : XmlColors(R.color.button_content)
    object ErrorOutLineColor : XmlColors(R.color.error_out_line)

    @Composable
    override fun colorValue() =
        Color(LocalContext.current.getColor(colorRes))

    @Composable
    override fun alphaColor(alphaValue: Float) =
        colorValue().let {
            it.copy(alphaValue * it.alpha)
        }

    override fun alphaColorInt(context: Context, alphaValue: Float): Int {
        return Color(colorInt(context)).let {
            it.copy(alphaValue * it.alpha)
        }.toArgb()
    }

    override fun colorInt(context: Context) =
        context.getColor(colorRes)


    override fun hex(context: Context) = colorInt(context).let {
        Integer.toHexString(it).uppercase(Locale.ROOT)
    }
}