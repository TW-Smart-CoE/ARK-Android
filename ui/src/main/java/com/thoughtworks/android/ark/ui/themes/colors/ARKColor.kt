package com.thoughtworks.android.ark.ui.themes.colors

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

sealed interface ARKColor {

    @Composable
    fun colorValue(): Color

    @Composable
    fun alphaColor(alphaValue: Float): Color

    fun alphaColorInt(context: Context, alphaValue: Float): Int

    fun colorInt(context: Context): Int

    fun hex(context: Context): String
}


