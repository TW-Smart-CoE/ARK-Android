package com.thoughtworks.android.ark.developtools

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.thoughtworks.android.ark.developtools.assistdemo.AssistDemoActivity

class DevelopToolsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupWindow()

        setContent {
            MaterialTheme {
                DevelopToolsScreen()
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
    fun DevelopToolsScreen() {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
                text = "DevelopTools",
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            MenuItem(
                title = "Hilt Assist Demo",
                desc = "A demo show how to use hilt assist inject."
            ) {
                startActivity(Intent(this@DevelopToolsActivity, AssistDemoActivity::class.java))
            }
            MenuItem(
                title = "Other menu",
                desc = "other menu"
            )
            MenuItem(
                title = "Other menu",
                desc = "other menu"
            )
        }
    }

    @Composable
    fun MenuItem(title: String, desc: String = "", onClick: () -> Unit = {}) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clickable(onClick = onClick)
                .padding(16.dp)
        ) {
            Column {
                Text(text = title, color = Color.Black, fontSize = 18.sp)
                if (desc.isNotEmpty()) {
                    Text(text = desc, color = Color.DarkGray)
                }
            }
        }
    }
}