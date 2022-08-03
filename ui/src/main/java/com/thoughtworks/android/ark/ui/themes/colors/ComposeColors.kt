package com.thoughtworks.android.ark.ui.themes.colors

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import java.util.*

sealed class ComposeColors(
    private val lightColor: Color = Color.Unspecified,
    private val darkColor: Color = Color.Unspecified
) : ARKColor {

    /**
     * If you want to adapt the dark model, please create the color on here.
     */
    sealed class Dynamic(lightColor: Color, darkColor: Color) :
        ComposeColors(lightColor, darkColor) {

        object ButtonBackground : Dynamic(Purple200, Teal200)
        object ButtonContent : Dynamic(Color.Black, Color.White)

    }

    /**
     *  The Static contains the color which don't need to adapt the dark model.
     */
    sealed class Static(color: Color) : ComposeColors(color, color) {
        object ErrorOutLineColor : Static(Red900)
    }

    @Composable
    override fun colorValue(): Color {
        return if (isSystemInDarkTheme()) darkColor else lightColor
    }

    override fun colorInt(context: Context): Int =
        if (isNightMode(context)) darkColor.toArgb() else lightColor.toArgb()

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

    override fun hex(context: Context) =
        colorInt(context).let {
            Integer.toHexString(it).uppercase(Locale.ROOT)
        }
}