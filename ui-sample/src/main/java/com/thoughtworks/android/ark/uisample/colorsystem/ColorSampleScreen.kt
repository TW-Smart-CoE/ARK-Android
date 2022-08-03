package com.thoughtworks.android.ark.uisample.colorsystem

import android.view.LayoutInflater
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.thoughtworks.android.ark.ui.themes.AndroidARKTheme
import com.thoughtworks.android.ark.uisample.R
import com.thoughtworks.android.ark.ui.themes.colors.ComposeColors
import com.thoughtworks.android.ark.ui.themes.colors.LocaleARKColors

@Composable
fun ColorSampleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AndroidARKTheme.colors.background)
    ) {
        // The theme color test for compose
        val context = LocalContext.current
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = LocalContext.current.getString(R.string.theme_color_test_for_compose),
            color = LocaleARKColors.current.primary
        )

        // The theme color test for xml
        Spacer(modifier = Modifier.height(30.dp))
        val colors = AndroidARKTheme.colors
        AndroidView(factory = { ctx ->
            LayoutInflater.from(ctx).inflate(R.layout.xml_color_test, null)
        }) {
            (it as TextView).setTextColor(colors.primary.toArgb())
        }

        // The special color test for compose
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = LocalContext.current.getString(R.string.special_color_test_for_compose),
            color = ComposeColors.Dynamic.ButtonBackground.colorValue()
        )

        // The special color test for xml
        Spacer(modifier = Modifier.height(30.dp))
        AndroidView(factory = { ctx ->
            LayoutInflater.from(ctx).inflate(R.layout.xml_color_test, null)
        }) {
            (it as TextView).setText(R.string.special_color_test_for_xml)
            val textColor = ComposeColors.Dynamic.ButtonBackground.colorInt(context)
            (it as TextView).setTextColor(textColor)
        }
    }
}