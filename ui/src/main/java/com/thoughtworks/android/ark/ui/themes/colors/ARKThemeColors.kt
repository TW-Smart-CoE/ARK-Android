package com.thoughtworks.android.ark.ui.themes.colors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.thoughtworks.android.ark.ui.R

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
    val onError: Color = Color.Unspecified,
    val statusBarColor: Color = Color.Unspecified,
    val backgroundBlue: Color = Color.Unspecified,
    val backgroundGreen: Color = Color.Unspecified
)

@Composable
fun ARKThemeAttrColors.toARKColors() =
    R.style.Theme_AndroidARK.let { themeId ->
        val context = LocalContext.current
        ARKThemeColors(
            primary = obtainThemeAttrColorToColor(context, themeId, this.primary),
            primaryVariant = obtainThemeAttrColorToColor(context, themeId, this.primaryVariant),
            secondary = obtainThemeAttrColorToColor(context, themeId, this.secondary),
            secondaryVariant = obtainThemeAttrColorToColor(context, themeId, this.secondaryVariant),
            background = obtainThemeAttrColorToColor(context, themeId, this.background),
            surface = obtainThemeAttrColorToColor(context, themeId, this.surface),
            error = obtainThemeAttrColorToColor(context, themeId, this.error),
            onPrimary = obtainThemeAttrColorToColor(context, themeId, this.onPrimary),
            onSecondary = obtainThemeAttrColorToColor(context, themeId, this.onSecondary),
            onBackground = obtainThemeAttrColorToColor(context, themeId, this.onBackground),
            onSurface = obtainThemeAttrColorToColor(context, themeId, this.onSurface),
            onError = obtainThemeAttrColorToColor(context, themeId, this.onError),
            statusBarColor = obtainThemeAttrColorToColor(context, themeId, this.statusBarColor),
            backgroundBlue = obtainThemeAttrColorToColor(context, themeId, this.backgroundBlue),
            backgroundGreen = obtainThemeAttrColorToColor(context, themeId, this.backgroundGreen)
        )
    }

val LocalARKThemeColors = staticCompositionLocalOf<ARKThemeColors> {
    error("No ARKThemColors provided")
}