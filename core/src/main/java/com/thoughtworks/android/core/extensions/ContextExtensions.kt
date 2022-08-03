package com.thoughtworks.android.core.extensions

import android.content.Context
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

fun Context.showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resId, duration).show()
}

fun Context.convertDpToPixel(dp: Float): Float {
    val resources = this.resources
    val metrics = resources.displayMetrics
    return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}

fun Context.convertPixelsToDp(px: Float): Float {
    val resources = this.resources
    val metrics = resources.displayMetrics
    return px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}