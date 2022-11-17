package com.thoughtworks.ark.ui.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf

@Immutable
class Shapes(
    val small: CornerBasedShape = RoundedCornerShape(Dimensions.dimension2),
    val medium: CornerBasedShape = RoundedCornerShape(Dimensions.dimension4),
    val large: CornerBasedShape = RoundedCornerShape(Dimensions.dimension8),
)

val LocalShapes = staticCompositionLocalOf<Shapes> { error("No ThemeShapes provided") }
