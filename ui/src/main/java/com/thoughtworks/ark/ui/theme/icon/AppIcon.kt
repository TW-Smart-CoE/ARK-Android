package com.thoughtworks.ark.ui.theme.icon

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.thoughtworks.ark.ui.R
import com.thoughtworks.ark.ui.theme.Theme

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    icon: Icon,
    size: Dp = dimensionResource(id = R.dimen.icon_normal),
    tint: Color = Theme.colors.primary,
    contentDescription: String = "",
) = when (icon) {
    is Icon.DrawableResourceIcon -> {
        Icon(
            painter = painterResource(id = icon.id),
            contentDescription = contentDescription,
            modifier = Modifier
                .size(size)
                .then(modifier),
            tint = tint
        )
    }
    is Icon.ImageVectorIcon -> {
        Icon(
            imageVector = icon.imageVector,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(size)
                .then(modifier),
            tint = tint
        )
    }
}
