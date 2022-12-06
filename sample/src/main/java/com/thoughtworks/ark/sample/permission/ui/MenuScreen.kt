package com.thoughtworks.ark.sample.permission.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.thoughtworks.ark.ui.component.AppFilledButton

@Composable
internal fun MenuScreen(screenState: MutableState<ScreenState>) {
    Column {
        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { screenState.value = ScreenState.SinglePermissionState },
            text = {
                Text(text = "Request Single Permission")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { screenState.value = ScreenState.SinglePermissionWithDialogState },
            text = {
                Text(text = "Request Single Permission With Dialog")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { screenState.value = ScreenState.MultiplePermissionsState },
            text = {
                Text(text = "Request Multiple Permissions")
            }
        )
    }
}