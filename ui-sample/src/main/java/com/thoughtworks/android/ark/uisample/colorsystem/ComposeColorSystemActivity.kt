package com.thoughtworks.android.ark.uisample.colorsystem

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.android.ark.ui.themes.APPTheme

class ComposeColorSystemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APPTheme {
                ColorSampleScreen()
            }
        }
    }
}