package com.thoughtworks.ark.ui.themes.colors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.thoughtworks.ark.ui.R

@Immutable
data class Colors(
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
fun AttrColors.toColors() =
    R.style.Theme_App.let { themeId ->
        val context = LocalContext.current
        Colors(
            primary = obtainAttrColorToColor(context, themeId, this.primary),
            primaryVariant = obtainAttrColorToColor(context, themeId, this.primaryVariant),
            secondary = obtainAttrColorToColor(context, themeId, this.secondary),
            secondaryVariant = obtainAttrColorToColor(context, themeId, this.secondaryVariant),
            background = obtainAttrColorToColor(context, themeId, this.background),
            surface = obtainAttrColorToColor(context, themeId, this.surface),
            error = obtainAttrColorToColor(context, themeId, this.error),
            onPrimary = obtainAttrColorToColor(context, themeId, this.onPrimary),
            onSecondary = obtainAttrColorToColor(context, themeId, this.onSecondary),
            onBackground = obtainAttrColorToColor(context, themeId, this.onBackground),
            onSurface = obtainAttrColorToColor(context, themeId, this.onSurface),
            onError = obtainAttrColorToColor(context, themeId, this.onError),
            statusBarColor = obtainAttrColorToColor(context, themeId, this.statusBarColor),
            backgroundBlue = obtainAttrColorToColor(context, themeId, this.backgroundBlue),
            backgroundGreen = obtainAttrColorToColor(context, themeId, this.backgroundGreen)
        )
    }

val LocalColors = staticCompositionLocalOf<Colors> {
    error("No APPThemColors provided")
}