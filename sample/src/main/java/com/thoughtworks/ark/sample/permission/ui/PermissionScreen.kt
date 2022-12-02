package com.thoughtworks.ark.sample.permission.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.Theme

@Composable
internal fun PermissionSampleScreen() {
    val screenState = remember { mutableStateOf<ScreenState>(ScreenState.None) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(Dimensions.standardPadding)
    ) {
        when (screenState.value) {
            is ScreenState.SinglePermissionState -> {
                SinglePermissionScreen()
            }
            is ScreenState.SinglePermissionWithDialogState -> {
                SinglePermissionWithDialogScreen()
            }
            is ScreenState.MultiplePermissionsState -> {
                MultiplePermissionsScreen()
            }
            else -> {
                MenuScreen(screenState = screenState)
            }
        }
    }

    BackHandler(screenState.value != ScreenState.None) {
        if (screenState.value != ScreenState.None) {
            screenState.value = ScreenState.None
        }
    }
}

@Composable
internal fun CommonText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = Theme.typography.body02,
        color = Theme.colors.onBackground,
        modifier = modifier
    )
}