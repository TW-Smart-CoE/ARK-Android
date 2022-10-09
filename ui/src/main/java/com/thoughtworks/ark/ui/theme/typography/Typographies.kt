package com.thoughtworks.ark.ui.theme.typography

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle

/**
 * ARK's typography system, modelled using Compose [TextStyle]s.
 */
@Immutable
data class Typographies(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
    val subtitle01: TextStyle,
    val subtitle02: TextStyle,
    val body01: TextStyle,
    val body02: TextStyle,
    val button: TextStyle,
    val caption: TextStyle,
    val overline: TextStyle
)

@Composable
fun generateTypographiesFromAttributes(): Typographies {
    val context = LocalContext.current
    return Typographies(
        h1 = AttrTypography.H1.style(context),
        h2 = AttrTypography.H2.style(context),
        h3 = AttrTypography.H3.style(context),
        h4 = AttrTypography.H4.style(context),
        h5 = AttrTypography.H5.style(context),
        h6 = AttrTypography.H6.style(context),
        subtitle01 = AttrTypography.Subtitle01.style(context),
        subtitle02 = AttrTypography.Subtitle02.style(context),
        body01 = AttrTypography.Body01.style(context),
        body02 = AttrTypography.Body02.style(context),
        button = AttrTypography.Button.style(context),
        caption = AttrTypography.Caption.style(context),
        overline = AttrTypography.Overline.style(context),
    )
}

val LocalTypographies = staticCompositionLocalOf<Typographies> {
    error("No ThemeTypography provided")
}
