package com.thoughtworks.android.core.extensions

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(
    text: CharSequence,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionText: String = "",
    actionTextColor: Int = 0,
    action: (View) -> Unit = {}
) {
    val snackbar = Snackbar.make(this, text, duration)
    if (actionText.isNotBlank() && action != {}) {
        snackbar.setAction(actionText, action)
    }
    if (actionTextColor != 0) {
        snackbar.setActionTextColor(ContextCompat.getColor(context, actionTextColor))
    }
    snackbar.show()
}

fun View.showSnackbar(
    @StringRes resId: Int,
    duration: Int = Snackbar.LENGTH_SHORT,
    @StringRes actionName: Int = 0,
    @ColorRes actionTextColor: Int = 0,
    action: (View) -> Unit = {}
) {
    val snackbar = Snackbar.make(this, resId, duration)
    if (actionName != 0 && action != {}) {
        snackbar.setAction(actionName, action)
    }
    if (actionTextColor != 0) {
        snackbar.setActionTextColor(ContextCompat.getColor(context, actionTextColor))
    }

    snackbar.show()
}
