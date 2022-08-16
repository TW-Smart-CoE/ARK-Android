package com.thoughtworks.ark.ui.themes.icon

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val small = 12.dp
val normal = 24.dp
val big = 36.dp

@Composable
fun Icons(
    modifier: Modifier = Modifier,
    icon: AppIcon,
    size: Dp = normal,
    tint: Color = Color.Transparent
) {
    Icon(
        painter = painterResource(id = icon.resourceId),
        contentDescription = "",
        modifier = Modifier.size(size).then(modifier),
        tint = tint
    )
}