package com.thoughtworks.ark.sample.designsystem.colorsystem.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import com.thoughtworks.ark.sample.R
import com.thoughtworks.ark.ui.theme.Theme
import com.thoughtworks.ark.ui.theme.color.ExtendedColors
import com.thoughtworks.ark.ui.theme.icon.Icons

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

        findViewById<ImageView>(R.id.xml_image_view_02).apply {
            setImageResource(Icons.Favorite)
        }
    }

    @Composable
    private fun ThemeColorTestContent() {
        Theme {
            Text(
                text = stringResource(R.string.theme_color_test_for_compose),
                color = Theme.colors.primary,
                style = Theme.typography.body01
            )
        }
    }

    @Composable
    private fun SpecialColorTestContent() {
        Theme {
            Text(
                text = stringResource(R.string.extended_color_test_for_compose),
                color = ExtendedColors.ButtonBackground.color(),
                style = Theme.typography.body01
            )
        }
    }
}