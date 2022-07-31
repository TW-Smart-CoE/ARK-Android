package com.thoughtworks.android.ark.ui.sample.colorsystem

import android.view.LayoutInflater
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.thoughtworks.android.ark.ui.themes.AndroidARKTheme
import com.thoughtworks.android.ark.UI.R
import com.thoughtworks.android.ark.ui.themes.colors.ComposeColors

@Composable
fun ColorSampleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AndroidARKTheme.colors.background)
    ) {
        val context = LocalContext.current
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "compose color", color = AndroidARKTheme.colors.primary)
        Spacer(modifier = Modifier.height(30.dp))
        AndroidView(factory = { ctx ->
            LayoutInflater.from(ctx).inflate(R.layout.xml_color_test, null)
        }) {
            (it as TextView).setTextColor(ComposeColors.Dynamic.ButtonContent.colorInt(context))
        }
    }
}