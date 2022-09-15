package com.thoughtworks.ark.developmenu

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.thoughtworks.ark.R
import com.thoughtworks.ark.ui.theme.Theme

class DevelopMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupWindow()

        setContent {
            Theme {
                DevelopMenuScreen()
            }
        }
    }

    private fun setupWindow() {
        supportActionBar?.hide()
        window.statusBarColor = Color.Transparent.toArgb()

        val controller = WindowCompat.getInsetsController(window, window.decorView)
        controller.isAppearanceLightStatusBars = true
    }

    @Composable
    fun DevelopMenuScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.colors.background)
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
                text = stringResource(id = R.string.develop_menu_label),
                color = Theme.colors.primary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}