package com.thoughtworks.ark.core.permission

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.thoughtworks.ark.ui.component.AppFilledButton
import com.thoughtworks.ark.ui.theme.Theme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PermissionHandler {
    internal val state = mutableStateOf(false)

    fun requestPermission() {
        state.value = true
    }

    internal fun resetState() {
        state.value = false
    }
}

class PermissionAlwaysDeniedHandler {
    internal val state = mutableStateOf(false)
    internal val alwaysDeniedState = mutableStateOf(false)

    fun openSystemSettingPage() {
        state.value = true
    }

    fun resetAlwaysDeniedState() {
        alwaysDeniedState.value = false
    }

    internal fun resetState() {
        state.value = false
        alwaysDeniedState.value = false
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SinglePermission(
    permission: String,
    beforeRequestContent: @Composable (PermissionHandler) -> Unit = { it.requestPermission() },
    alwaysDeniedContent: @Composable (PermissionAlwaysDeniedHandler) -> Unit = { DefaultAlwaysDeniedContent(it) },
    grantedContent: @Composable () -> Unit = {}
) {
    val context = LocalContext.current
    val permissionHandler = remember { PermissionHandler() }
    val permissionAlwaysDeniedHandler = remember { PermissionAlwaysDeniedHandler() }

    val permissionState = rememberPermissionState(permission) {
        if (!it && !context.shouldShowRequestPermissionRationale(permission)) {
            permissionAlwaysDeniedHandler.alwaysDeniedState.value = true
        }
    }

    if (permissionState.status.isGranted) {
        grantedContent()
    } else {
        beforeRequestContent(permissionHandler)

        if (permissionAlwaysDeniedHandler.alwaysDeniedState.value) {
            alwaysDeniedContent(permissionAlwaysDeniedHandler)
        }

        LaunchedEffect(permissionHandler) {
            snapshotFlow { permissionHandler.state.value }
                .onEach {
                    if (it) {
                        permissionState.launchPermissionRequest()
                        permissionHandler.resetState()
                    }
                }
                .launchIn(this)
        }

        LaunchedEffect(permissionAlwaysDeniedHandler) {
            snapshotFlow { permissionAlwaysDeniedHandler.state.value }
                .onEach {
                    if (it) {
                        context.openSystemPermissionSettingPage()
                        permissionAlwaysDeniedHandler.resetState()
                    }
                }
                .launchIn(this)
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MultiplePermissions(
    vararg permissions: String,
    beforeRequestContent: @Composable (PermissionHandler) -> Unit = { it.requestPermission() },
    alwaysDeniedContent: @Composable (PermissionAlwaysDeniedHandler) -> Unit = { DefaultAlwaysDeniedContent(it) },
    grantedContent: @Composable () -> Unit = {}
) {
    val context = LocalContext.current
    val permissionHandler = remember { PermissionHandler() }
    val permissionAlwaysDeniedHandler = remember { PermissionAlwaysDeniedHandler() }

    val multiplePermissionsState = rememberMultiplePermissionsState(permissions.toList()) {
        var isAlwaysDenied = false
        it.entries.forEach { entry ->
            if (!entry.value && !context.shouldShowRequestPermissionRationale(entry.key)) {
                isAlwaysDenied = true
            }
        }
        if (isAlwaysDenied) {
            permissionAlwaysDeniedHandler.alwaysDeniedState.value = true
        }
    }

    if (multiplePermissionsState.allPermissionsGranted) {
        grantedContent()
    } else {
        beforeRequestContent(permissionHandler)

        if (permissionAlwaysDeniedHandler.alwaysDeniedState.value) {
            alwaysDeniedContent(permissionAlwaysDeniedHandler)
        }

        LaunchedEffect(permissionHandler) {
            snapshotFlow { permissionHandler.state.value }
                .onEach {
                    if (it) {
                        multiplePermissionsState.launchMultiplePermissionRequest()
                        permissionHandler.resetState()
                    }
                }
                .launchIn(this)
        }

        LaunchedEffect(permissionAlwaysDeniedHandler) {
            snapshotFlow { permissionAlwaysDeniedHandler.state.value }
                .onEach {
                    if (it) {
                        context.openSystemPermissionSettingPage()
                        permissionAlwaysDeniedHandler.resetState()
                    }
                }
                .launchIn(this)
        }
    }
}

@Composable
private fun DefaultAlwaysDeniedContent(launcher: PermissionAlwaysDeniedHandler) {
    AlertDialog(
        backgroundColor = Theme.colors.background,
        onDismissRequest = { launcher.resetAlwaysDeniedState() },
        confirmButton = {
            AppFilledButton(
                onClick = { launcher.openSystemSettingPage() },
                text = {
                    CommonText(text = stringResource(R.string.default_always_denied_dialog_confirm))
                }
            )
        },
        dismissButton = {
            AppFilledButton(
                onClick = { launcher.resetAlwaysDeniedState() },
                text = {
                    CommonText(text = stringResource(R.string.default_always_denied_dialog_cancel))
                }
            )
        },
        title = {
            CommonText(text = stringResource(R.string.default_always_denied_dialog_title))
        },
        text = {
            CommonText(stringResource(R.string.default_always_denied_dialog_content))
        }
    )
}

@Composable
private fun CommonText(text: String) {
    Text(
        text = text,
        style = Theme.typography.body02,
        color = Theme.colors.onBackground
    )
}