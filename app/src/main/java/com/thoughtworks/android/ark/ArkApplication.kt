package com.thoughtworks.android.ark

import android.app.Application
import android.util.Log
import com.thoughtworks.android.ark.developtools.DevelopToolsInitializer
import com.thoughtworks.android.ark.environment.Environment
import dagger.hilt.android.HiltAndroidApp
import kotlin.properties.Delegates

@HiltAndroidApp
class ArkApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DevelopToolsInitializer().init(this)
        Log.d("TAG", Environment.HOST)
        instance = this
    }

    companion object {
        private var instance: ArkApplication by Delegates.notNull()
        fun instance() = instance
    }
}