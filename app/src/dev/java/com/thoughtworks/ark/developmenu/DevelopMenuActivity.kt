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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thoughtworks.ark.R
import com.thoughtworks.ark.ui.themes.APPTheme
import com.thoughtworks.ark.ui.themes.colors.LocalAPPThemeColors

class DevelopMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupWindow()

        setContent {
            APPTheme {
                val systemUiController = rememberSystemUiController()
                val statusBarColor = LocalAPPThemeColors.current.statusBarColor

                LaunchedEffect(statusBarColor) {
                    systemUiController.setStatusBarColor(statusBarColor)
                    systemUiController.setNavigationBarColor(statusBarColor)
                }
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
                .background(color = APPTheme.colors.background)
        ) {
            val context = LocalContext.current
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
                text = context.getString(R.string.develop_menu_label),
                color = APPTheme.colors.primary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}