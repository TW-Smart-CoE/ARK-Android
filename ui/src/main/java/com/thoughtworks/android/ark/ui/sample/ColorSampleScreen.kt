package com.thoughtworks.android.ark.ui.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thoughtworks.android.ark.ui.themes.AndroidARKTheme

@Composable
fun ColorSampleScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AndroidARKTheme.colors.background)
    ){
        Text(text = "Test", color = AndroidARKTheme.colors.primary)
    }
}