package com.thoughtworks.android.ark

import android.app.Application
import com.thoughtworks.android.ark.developtools.DevelopToolsInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ArkApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DevelopToolsInitializer().init(this)
    }
}