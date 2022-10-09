package com.thoughtworks.ark.uisample.colorsystem

import android.annotation.SuppressLint
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
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.Theme
import com.thoughtworks.ark.ui.theme.color.ExtendedColors
import com.thoughtworks.ark.uisample.R

@Composable
fun ComposeColorSystemScreen() {
    Column(
        modifier = Modifier
            .background(color = Theme.colors.background)
            .fillMaxSize()
            .padding(all = Dimensions.standardPadding),
    ) {
        Text(
            text = stringResource(R.string.theme_color_test_for_compose),
            color = Theme.colors.primary
        )
        TextViewFromXml(
            color = Theme.colors.primary.toArgb(),
            text = stringResource(id = R.string.theme_color_test_for_xml)
        )
        Text(
            modifier = Modifier.padding(top = Dimensions.standardSpacing),
            text = stringResource(R.string.extended_color_test_for_compose),
            color = ExtendedColors.ButtonBackground.color()
        )
        TextViewFromXml(
            color = ExtendedColors.ButtonBackground.colorInt(LocalContext.current),
            text = stringResource(id = R.string.extended_color_test_for_xml))
    }
}

@SuppressLint("InflateParams")
@Composable
private fun TextViewFromXml(color: Int, text: String) {
    AndroidView(
        modifier = Modifier.padding(top = Dimensions.standardSpacing),
        factory = { LayoutInflater.from(it).inflate(R.layout.xml_color_layout, null) as TextView }
    ) {
        it.text = text
        it.setTextColor(color)
    }
}