package com.thoughtworks.ark

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import com.thoughtworks.ark.core.logging.Logger
import dagger.hilt.android.HiltAndroidApp
import kotlin.properties.Delegates

@HiltAndroidApp
class AppApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Logger.setUp()
    }

    override fun newImageLoader() = ImageLoader.Builder(this)
        .components { add(SvgDecoder.Factory()) }
        .build()

    companion object {
        private var instance: AppApplication by Delegates.notNull()
        fun instance() = instance
    }
}
