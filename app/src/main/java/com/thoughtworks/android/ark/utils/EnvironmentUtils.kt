package com.thoughtworks.android.ark.utils

import com.thoughtworks.android.ark.BuildConfig

fun isDevEnvironment(): Boolean {
    return BuildConfig.BUILD_TYPE == "debug"
}