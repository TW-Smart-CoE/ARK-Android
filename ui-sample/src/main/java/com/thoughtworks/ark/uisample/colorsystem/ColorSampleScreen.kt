package com.thoughtworks.ark.uisample.colorsystem

import android.view.LayoutInflater
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import com.thoughtworks.ark.ui.themes.Dimensions
import com.thoughtworks.ark.ui.themes.Theme
import com.thoughtworks.ark.ui.themes.colors.ExtendedColors
import com.thoughtworks.ark.uisample.R

@Composable
fun ColorSampleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Dimensions.standardPadding)
            .background(color = Theme.colors.background),
    ) {
        Text(
            text = stringResource(R.string.theme_color_test_for_compose),
            color = Theme.colors.primary
        )
        val colors = Theme.colors
        AndroidView(modifier = Modifier.padding(top = Dimensions.standardSpacing),
            factory = { ctx ->
                LayoutInflater.from(ctx).inflate(R.layout.xml_color_test, null)
            }) {
            (it as TextView).setTextColor(colors.primary.toArgb())
        }
        Text(
            modifier = Modifier.padding(top = Dimensions.standardSpacing),
            text = stringResource(R.string.extended_color_test_for_compose),
            color = ExtendedColors.ButtonBackground.color()
        )
        val context = LocalContext.current
        AndroidView(
            modifier = Modifier.padding(top = Dimensions.standardSpacing),
            factory = { ctx ->
                LayoutInflater.from(ctx).inflate(
                    R.layout.xml_color_test,
                    null
                )
            }) {
            (it as TextView).setText(R.string.extended_color_test_for_xml)
            val textColor = ExtendedColors.ButtonBackground.colorInt(context)
            println("Shuai =====" + ExtendedColors.ButtonBackground.hex(context))
            it.setTextColor(textColor)
        }
    }
}