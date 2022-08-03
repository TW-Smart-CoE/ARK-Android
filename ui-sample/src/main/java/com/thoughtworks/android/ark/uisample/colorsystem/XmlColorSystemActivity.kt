package com.thoughtworks.android.ark.uisample.colorsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import com.google.android.material.color.MaterialColors
import com.thoughtworks.android.ark.ui.themes.colors.XmlColors
import com.thoughtworks.android.ark.ui.themes.colors.obtainThemeAttrColorToColor
import com.thoughtworks.android.ark.uisample.R

class XmlColorSystemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.xml_color_system)

        val themeColorTestCompose = findViewById<ComposeView>(R.id.compose_color_01)
        themeColorTestCompose.setContent {
            ThemeColorTestContent()
        }

        val specialColorTestCompose = findViewById<ComposeView>(R.id.compose_color_02)
        specialColorTestCompose.setContent {
            SpecialColorTestContent()
        }
    }

    @Composable
    private fun ThemeColorTestContent() {
        val colorInt = obtainThemeAttrColorToColor(
            context = LocalContext.current,
            attrResId = com.google.android.material.R.attr.colorPrimary
        )
        Text(
            text = LocalContext.current.getString(R.string.theme_color_test_for_compose),
            color = Color(colorInt)
        )
    }

    @Composable
    private fun SpecialColorTestContent() {
        Text(
            text = LocalContext.current.getString(R.string.special_color_test_for_compose),
            color = XmlColors.ButtonBackground.colorValue()
        )
    }
}