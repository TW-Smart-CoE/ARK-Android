package com.thoughtworks.android.ark.uisample.colorsystem

import android.view.LayoutInflater
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.thoughtworks.android.ark.ui.themes.APPTheme
import com.thoughtworks.android.ark.ui.themes.colors.APPExtendedColors
import com.thoughtworks.android.ark.uisample.R

@Composable
fun ColorSampleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = APPTheme.colors.background)
    ) {
        // The theme color test for compose
        val context = LocalContext.current
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(R.string.theme_color_test_for_compose),
            color = APPTheme.colors.primary
        )

        // The theme color test for xml
        Spacer(modifier = Modifier.height(30.dp))
        val colors = APPTheme.colors
        AndroidView(factory = { ctx ->
            LayoutInflater.from(ctx).inflate(R.layout.xml_color_test, null)
        }) {
            (it as TextView).setTextColor(colors.primary.toArgb())
        }

        // The special color test for compose
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(R.string.extended_color_test_for_compose),
            color = APPExtendedColors.ButtonBackground.colorValue()
        )

        // The special color test for xml
        Spacer(modifier = Modifier.height(30.dp))
        AndroidView(factory = { ctx ->
            LayoutInflater.from(ctx).inflate(
                R.layout.xml_color_test,
                null
            )
        }) {
            (it as TextView).setText(R.string.extended_color_test_for_xml)
            val textColor = APPExtendedColors.ButtonBackground.colorInt(context)
            it.setTextColor(textColor)
        }
    }
}