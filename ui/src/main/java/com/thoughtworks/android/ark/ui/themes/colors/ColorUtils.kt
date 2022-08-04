package com.thoughtworks.android.ark.ui.themes.colors

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.view.ContextThemeWrapper
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.android.material.color.MaterialColors

fun isNightMode(context: Context) =
    context.resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

/**
 *  get the attr color from the theme
 */
fun obtainThemeAttrColorToColor(
    context: Context,
    @StyleRes themeId: Int,
    @AttrRes attrResId: Int
) = Color(obtainThemeAttrColorToColorInt(context, themeId, attrResId))


/**
 *  get the attr color Int from the theme
 */
fun obtainThemeAttrColorToColorInt(
    context: Context,
    @StyleRes themeId: Int,
    @AttrRes attrResId: Int
) = MaterialColors.getColor(
    ContextThemeWrapper(context, themeId),
    attrResId, Color.White.toArgb()
)


/**
 *   get the attr color from the theme and transform to color state list
 */
fun obtainThemeAttrColorToStateList(
    context: Context,
    @StyleRes themeId: Int,
    @AttrRes attrResId: Int
) = ColorStateList.valueOf(obtainThemeAttrColorToColorInt(context, themeId, attrResId))
