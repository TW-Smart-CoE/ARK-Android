package com.thoughtworks.ark.core.permission

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.core.content.PermissionChecker
import com.thoughtworks.ark.core.extensions.findActivity

fun Context.hasPermission(vararg permissions: String): Boolean {
    var result = true
    permissions.forEach {
        if (!checkPermission(it)) {
            result = false
        }
    }
    return result
}

private fun Context.checkPermission(permission: String): Boolean {
    return PermissionChecker.checkSelfPermission(this, permission) == PermissionChecker.PERMISSION_GRANTED
}

fun Context.shouldShowRequestPermissionRationale(vararg permissions: String): Boolean {
    var result = false
    val activity = findActivity()
    permissions.forEach {
        if (activity.shouldShowRequestPermissionRationale(it)) {
            result = true
        }
    }
    return result
}

fun Context.openSystemPermissionSettingPage() {
    val intent = Intent()
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    intent.data = Uri.parse("package:$packageName")
    startActivity(intent)
}