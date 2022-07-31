package com.thoughtworks.android.ark.ui.themes.colors

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * BasicColor
 * */

// Purple
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)

// Teal
val Teal200 = Color(0xFF03DAC5)
val Teal700 = Color(0xFF00796B)

// Red
val Red900 = Color(0xFFB00020)

/**
 * The theme color.
 * Please define the common color on here.
 * if you want to add a special color, please move to [ComposeColors].
 */

@Immutable
data class ARKThemeColors(
    val primary: Color = Color.Unspecified,
    val primaryVariant: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val secondaryVariant: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val surface: Color = Color.Unspecified,
    val error: Color = Color.Unspecified,
    val onPrimary: Color = Color.Unspecified,
    val onSecondary: Color = Color.Unspecified,
    val onBackground: Color = Color.Unspecified,
    val onSurface: Color = Color.Unspecified,
    val onError: Color = Color.Unspecified
)

val lightColors = ARKThemeColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200,
    secondaryVariant = Teal700,
    background = Color.White,
    surface = Color.White,
    error = Red900,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

val darkColors = ARKThemeColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200,
    secondaryVariant = Teal700,
    background = Color.Black,
    surface = Color.White,
    error = Red900,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

val LocaleARKColors = staticCompositionLocalOf {
    ARKThemeColors()
}