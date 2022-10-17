package com.thoughtworks.ark.sample.main.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.thoughtworks.ark.ui.theme.Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                MainScreen()
            }
        }
    }

    @Composable
    fun MainScreen() {
        Text(text = "Hello")
    }
}