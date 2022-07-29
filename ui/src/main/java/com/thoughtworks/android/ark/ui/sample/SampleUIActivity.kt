package com.thoughtworks.android.ark.ui.sample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import com.thoughtworks.android.ark.ui.themes.AndroidARKTheme

class SampleUIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidARKTheme(isSystemInDarkTheme()) {
                ColorSampleScreen()
            }
        }
    }
}