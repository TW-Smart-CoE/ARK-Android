package com.thoughtworks.ark.ui.themes

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource

@Composable
fun getDimension(dimensId: Int) = dimensionResource(id = dimensId)