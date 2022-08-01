package com.thoughtworks.android.ark.uisample

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import com.thoughtworks.android.ark.ui.sample.state.Action
import com.thoughtworks.android.ark.ui.sample.state.NavigateActivityAction
import com.thoughtworks.android.ark.ui.themes.AndroidARKTheme

class SampleUIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidARKTheme(isSystemInDarkTheme()) {
                SampleUIScreen {
                    handAction(it)
                }
            }
        }
    }

    private fun handAction(action: Action) {
        when (action) {
            is NavigateActivityAction -> handNavigateAction(action)
            else -> {}
        }
    }

    private fun handNavigateAction(action: NavigateActivityAction) {
        startActivity(Intent(this, action.destination))
    }
}