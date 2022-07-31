package com.thoughtworks.android.ark.ui.themes.colors

import android.content.Context
import android.content.res.Configuration

fun isNightMode(context: Context) =
    context.resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
