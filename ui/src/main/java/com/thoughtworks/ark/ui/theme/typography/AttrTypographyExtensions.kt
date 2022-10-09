package com.thoughtworks.ark.ui.theme.typography

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat

@SuppressLint("ResourceType")
fun AttrTypography.attributes(context: Context): AttrTypography.Attributes =
    context.obtainStyledAttributes(
        styleRes,
        intArrayOf(
            android.R.attr.textSize,
            android.R.attr.textStyle,
            android.R.attr.textAllCaps,
            android.R.attr.fontFamily,
            android.R.attr.letterSpacing
        )
    ).run {
        val textSizePx = getDimension(0, 14.sp.value)
        val textSizeSp = geSizeSp(0, textSizePx)
        val typefaceStyle = getInt(1, Typeface.NORMAL)
        val allCaps = getBoolean(2, false)
        val fontFamily = getString(3) ?: FontFamily.Default.toString()
        val letterSpacingEm = getFloat(4, 0f)

        recycle()
        AttrTypography.Attributes(
            textSizePx,
            textSizeSp,
            fontFamily,
            allCaps,
            typefaceStyle,
            letterSpacingEm
        )
    }

private fun TypedArray.geSizeSp(index: Int, defaultValue: Float) =
    getString(index)?.replace("sp", "")?.toFloat() ?: defaultValue


internal fun AttrTypography.style(context: Context): TextStyle = with(attributes(context)) {
    val fontWeight =
        if (typefaceStyle == Typeface.NORMAL) FontWeight.Normal else FontWeight.Bold
    val fontStyle = if (typefaceStyle == Typeface.ITALIC) FontStyle.Italic else FontStyle.Normal

    TextStyle(
        fontSize = textSizeSp.sp,
        fontWeight = fontWeight,
        fontStyle = fontStyle,
        letterSpacing = letterSpacingEm.em,
        fontFamily = createTypeface(context, this)?.let { FontFamily(it) },
    )
}

private fun createTypeface(
    context: Context,
    attributes: AttrTypography.Attributes,
): Typeface? = if (attributes.fontFamily.isFontFamilyPath()) {
    val id = context.resources.getIdentifier(
        attributes.fontFamily.getFontFamilyName(), "font", context.packageName
    )
    ResourcesCompat.getFont(context, id)
} else {
    // system's font here
    Typeface.create(attributes.fontFamily, attributes.typefaceStyle)
}

private fun String.isFontFamilyPath() = contains("res/font") && endsWith(".ttf")

private fun String.getFontFamilyName(): String =
    substringAfterLast("res/font/").substringBeforeLast(".ttf")
