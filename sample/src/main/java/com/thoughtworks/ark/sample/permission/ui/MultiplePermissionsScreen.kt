package com.thoughtworks.ark.sample.permission.ui

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.thoughtworks.ark.core.permission.MultiplePermissions

@Composable
internal fun MultiplePermissionsScreen() {
    MultiplePermissions(
        Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
        beforeRequestContent = {
            Box(modifier = Modifier.fillMaxSize()) {
                CommonText(
                    text = "The app needs camera and audio permissions!",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            it.requestPermission()
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            CommonText(
                text = "The camera and audio permissions already granted!",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}