package com.thoughtworks.android.ark

import android.app.Application
import com.thoughtworks.android.ark.developtools.DevelopToolsInitializer
import dagger.hilt.android.HiltAndroidApp
import kotlin.properties.Delegates

@HiltAndroidApp
class ArkApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DevelopToolsInitializer().init(this)
        instance = this
    }

    companion object {
        private var instance: ArkApplication by Delegates.notNull()
        fun instance() = instance
    }
}