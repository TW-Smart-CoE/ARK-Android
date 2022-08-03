package com.thoughtworks.android.ark.uisample.colorsystem

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import com.thoughtworks.android.ark.ui.themes.AndroidARKTheme
import com.thoughtworks.android.ark.uisample.R

class ComposeColorSystemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidARKTheme(isSystemInDarkTheme()) {
                ColorSampleScreen()
            }
        }
    }
}