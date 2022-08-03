package com.thoughtworks.android.ark.uisample.colorsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import com.thoughtworks.android.ark.ui.themes.colors.obtainThemeAttrColorToColor
import com.thoughtworks.android.ark.uisample.R

class XmlColorSystemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.xml_color_system)

        val composeColor01 = findViewById<ComposeView>(R.id.compose_color_01)
        composeColor01.setContent {
            ComposeColorTestContent()
        }
    }

    @Composable
    private fun ComposeColorTestContent() {
        val colorInt = obtainThemeAttrColorToColor(
            context = LocalContext.current,
            attrResId = com.google.android.material.R.attr.colorPrimary
        )
        Text(
            text = LocalContext.current.getString(R.string.theme_color_test_for_compose),
            color = Color(colorInt)
        )
    }
}