package com.thoughtworks.ark.ui.theme.typography

import androidx.annotation.StyleRes
import com.thoughtworks.ark.ui.R

enum class AttrTypography(@StyleRes val styleRes: Int) {
    H1(R.style.Headline1),
    H2(R.style.Headline2),
    H3(R.style.Headline3),
    H4(R.style.Headline4),
    H5(R.style.Headline5),
    H6(R.style.Headline6),
    Subtitle01(R.style.Subtitle01),
    Subtitle02(R.style.Subtitle02),
    Body01(R.style.Body01),
    Body02(R.style.Body02),
    Button(R.style.Button),
    Caption(R.style.Caption),
    Overline(R.style.Overline);

    data class Attributes(
        val textSizePx: Float,
        val textSizeSp: Float,
        val fontFamily: String,
        val allCaps: Boolean,
        val typefaceStyle: Int,
        val letterSpacingEm: Float
    )
}
