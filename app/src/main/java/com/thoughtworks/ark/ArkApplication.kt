package com.thoughtworks.ark

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlin.properties.Delegates

@HiltAndroidApp
class ArkApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: ArkApplication by Delegates.notNull()
        fun instance() = instance
    }
}