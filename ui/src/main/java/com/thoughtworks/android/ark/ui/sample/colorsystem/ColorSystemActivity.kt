package com.thoughtworks.android.ark.ui.sample.colorsystem

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import com.thoughtworks.android.ark.UI.R
import com.thoughtworks.android.ark.ui.themes.AndroidARKTheme

class ColorSystemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidARKTheme(isSystemInDarkTheme()) {
                ColorSampleScreen()
                R.layout.xml_color_test
            }
        }
    }
}