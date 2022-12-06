package com.thoughtworks.ark.sample.permission.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.ark.ui.theme.Theme

class PermissionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                PermissionSampleScreen()
            }
        }
    }
}