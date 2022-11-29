package com.thoughtworks.ark.sample.feeds

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.ark.sample.feeds.ui.FeedScreen
import com.thoughtworks.ark.ui.theme.Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                FeedScreen()
            }
        }
    }
}