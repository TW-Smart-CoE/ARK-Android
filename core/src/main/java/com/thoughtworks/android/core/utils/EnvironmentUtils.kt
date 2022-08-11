package com.thoughtworks.android.core.utils

import com.thoughtworks.android.core.BuildConfig

fun isDevEnvironment() = BuildConfig.BUILD_TYPE == "debug"