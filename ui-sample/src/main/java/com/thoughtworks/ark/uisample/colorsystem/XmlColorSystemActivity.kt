package com.thoughtworks.ark.uisample.colorsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import com.thoughtworks.ark.ui.theme.Theme
import com.thoughtworks.ark.ui.theme.color.ExtendedColors
import com.thoughtworks.ark.uisample.R

class XmlColorSystemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
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
        Theme {
            Text(
                text = stringResource(R.string.theme_color_test_for_compose),
                color = Theme.colors.primary,
                style = Theme.typographies.body01
            )
        }
    }

    @Composable
    private fun SpecialColorTestContent() {
        Theme {
            Text(
                text = stringResource(R.string.extended_color_test_for_compose),
                color = ExtendedColors.ButtonBackground.color(),
                style = Theme.typographies.body01
            )
        }
    }
}