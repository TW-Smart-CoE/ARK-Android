package com.thoughtworks.android.ark.ui.themes.colors

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.view.ContextThemeWrapper
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import com.google.android.material.color.MaterialColors
import com.thoughtworks.android.ark.ui.R

fun isNightMode(context: Context) =
    context.resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES

/**
 *  get the attr color from the theme
 */
@ColorInt
fun obtainThemeAttrColorToColor(
    context: Context,
    @AttrRes attrResId: Int
) = MaterialColors.getColor(
    ContextThemeWrapper(context, R.style.Theme_AndroidARK),
    attrResId,
    Color.WHITE
)


/**
 *   get the attr color from the theme and transform to color state list
 */
fun obtainThemeAttrColorToStateList(
    context: Context,
    @AttrRes attrResId: Int
) = ColorStateList.valueOf(obtainThemeAttrColorToColor(context, attrResId))
