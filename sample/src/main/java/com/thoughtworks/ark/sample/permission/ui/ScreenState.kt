package com.thoughtworks.ark.sample.permission.ui

internal sealed class ScreenState {
    object None : ScreenState()
    object SinglePermissionState : ScreenState()
    object SinglePermissionWithDialogState : ScreenState()
    object MultiplePermissionsState : ScreenState()
}