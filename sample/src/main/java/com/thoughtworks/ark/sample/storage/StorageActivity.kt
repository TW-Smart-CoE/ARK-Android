package com.thoughtworks.ark.sample.storage

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.thoughtworks.ark.ui.theme.Theme

class StorageActivity : AppCompatActivity() {

    private val viewModel by viewModels<StorageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                StorageScreen(viewModel)
            }
        }
    }
}