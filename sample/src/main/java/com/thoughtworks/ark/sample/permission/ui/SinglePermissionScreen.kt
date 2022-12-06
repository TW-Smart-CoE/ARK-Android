package com.thoughtworks.ark.sample.permission.ui

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thoughtworks.ark.core.permission.SinglePermission

@Composable
internal fun SinglePermissionScreen() {
    SinglePermission(
        permission = Manifest.permission.READ_PHONE_STATE,
        beforeRequestContent = {
            Box(modifier = Modifier.fillMaxSize()) {
                CommonText(
                    text = "The app needs phone permission!",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            it.requestPermission()
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            CommonText(
                text = "The phone permission already granted!",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}