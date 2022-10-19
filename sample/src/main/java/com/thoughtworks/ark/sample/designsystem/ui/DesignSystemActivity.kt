package com.thoughtworks.ark.sample.designsystem.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thoughtworks.ark.core.logging.Logger
import com.thoughtworks.ark.sample.main.ui.state.Action
import com.thoughtworks.ark.sample.main.ui.state.NavigateActivityAction
import com.thoughtworks.ark.ui.theme.Theme

class DesignSystemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        supportActionBar?.hide()
        setContent {
            Theme {
                val systemUiController = rememberSystemUiController()
                systemUiController.setStatusBarColor(Theme.colors.background)
                systemUiController.setNavigationBarColor(Theme.colors.background)
                DesignSystemScreen {
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
        Logger.d("Navigate to %s", action.destination.simpleName)
        startActivity(Intent(this, action.destination))
    }
}
