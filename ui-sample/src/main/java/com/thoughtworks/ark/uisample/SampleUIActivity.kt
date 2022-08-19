package com.thoughtworks.ark.uisample

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.thoughtworks.ark.ui.themes.APPTheme
import com.thoughtworks.ark.ui.themes.colors.LocalAPPThemeColors
import com.thoughtworks.ark.uisample.state.NavigateActivityAction

class SampleUIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APPTheme {
                val systemUiController = rememberSystemUiController()
                val statusBarColor = LocalAPPThemeColors.current.statusBarColor

                LaunchedEffect(statusBarColor) {
                    systemUiController.setStatusBarColor(statusBarColor)
                    systemUiController.setNavigationBarColor(statusBarColor)
                }

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