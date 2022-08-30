package com.thoughtworks.ark.core.utils

import com.thoughtworks.ark.core.BuildConfig

fun isDevEnvironment() = BuildConfig.BUILD_TYPE == "debug"