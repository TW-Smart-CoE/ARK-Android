package com.thoughtworks.android.ark.uisample.colorsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import com.thoughtworks.android.ark.ui.themes.APPTheme
import com.thoughtworks.android.ark.ui.themes.colors.APPExtendedColors
import com.thoughtworks.android.ark.ui.themes.colors.LocalAPPThemeColors
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
        APPTheme {
            Text(
                text = stringResource(id = R.string.theme_color_test_for_compose),
                color = LocalAPPThemeColors.current.primary
            )
        }
    }

    @Composable
    private fun SpecialColorTestContent() {
        Text(
            text = stringResource(R.string.extended_color_test_for_compose),
            color = APPExtendedColors.ButtonBackground.colorValue()
        )
    }
}