package com.thoughtworks.ark.ui.themes.icon

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.thoughtworks.ark.ui.R

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    size: Dp = dimensionResource(id = R.dimen.icon_normal),
    tint: Color = Color.Transparent,
    contentDescription: String = "",
) {
    Icon(
        painter = painterResource(id = icon),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(size)
            .then(modifier),
        tint = tint
    )
}

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    size: Dp = dimensionResource(id = R.dimen.icon_normal),
    tint: Color = Color.Transparent,
    contentDescription: String = "",
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = Modifier
            .size(size)
            .then(modifier),
        tint = tint
    )
}