package com.thoughtworks.ark.sample.permission

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.ark.ui.theme.Theme

class PermissionSampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                PermissionSampleUI()
            }
        }
    }
}