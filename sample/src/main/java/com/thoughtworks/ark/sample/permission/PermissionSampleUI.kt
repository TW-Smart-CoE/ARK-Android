package com.thoughtworks.ark.sample.permission

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CAMERA
import android.Manifest.permission.RECORD_AUDIO
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.thoughtworks.ark.core.permission.MultiplePermissions
import com.thoughtworks.ark.core.permission.PermissionHandler
import com.thoughtworks.ark.core.permission.SinglePermission
import com.thoughtworks.ark.sample.R
import com.thoughtworks.ark.ui.component.AppFilledButton
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.LocalShapes
import com.thoughtworks.ark.ui.theme.Theme

@Composable
fun PermissionSampleUI() {
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

sealed class ScreenState {
    object None : ScreenState()
    object SinglePermissionState : ScreenState()
    object SinglePermissionWithDialogState : ScreenState()
    object MultiplePermissionsState : ScreenState()
}

@Composable
private fun MenuScreen(screenState: MutableState<ScreenState>) {
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

@Composable
private fun SinglePermissionScreen() {
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

@Composable
private fun SinglePermissionWithDialogScreen() {
    SinglePermission(
        permission = ACCESS_FINE_LOCATION,
        beforeRequestContent = {
            PermissionPlaceHolder(it)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            CommonText(
                text = "The location permission already granted!",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun MultiplePermissionsScreen() {
    MultiplePermissions(
        CAMERA, RECORD_AUDIO,
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

@Composable
private fun PermissionPlaceHolder(permissionHandler: PermissionHandler) {
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        AppFilledButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            onClick = { showDialog = true },
            text = {
                Text(text = "Click to request location permission")
            }
        )
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(Theme.colors.background, shape = LocalShapes.current.large)
            ) {
                Column(
                    modifier = Modifier
                        .height(300.dp)
                        .align(Alignment.Center)
                        .padding(Dimensions.dimension16),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(Color.Gray),
                        modifier = Modifier.padding(top = Dimensions.dimension16)
                    )
                    CommonText(
                        modifier = Modifier.padding(top = Dimensions.dimension16),
                        text = stringResource(R.string.request_permission_dialog_content)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = Dimensions.dimension20),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        AppFilledButton(
                            modifier = Modifier.width(130.dp),
                            onClick = { showDialog = false },
                            text = {
                                Text(text = stringResource(R.string.request_permission_dialog_cancel_button))
                            }
                        )

                        AppFilledButton(
                            modifier = Modifier.width(130.dp),
                            onClick = {
                                showDialog = false
                                permissionHandler.requestPermission()
                            },
                            text = {
                                Text(text = stringResource(R.string.request_permission_dialog_confirm_button))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CommonText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = Theme.typography.body02,
        color = Theme.colors.onBackground,
        modifier = modifier
    )
}