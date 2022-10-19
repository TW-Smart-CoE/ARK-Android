package com.thoughtworks.ark.sample

import android.app.Application
import android.util.Log
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import com.thoughtworks.ark.core.logging.Logger
import com.thoughtworks.ark.core.logging.printer.AndroidPrinter
import com.thoughtworks.ark.core.logging.printer.decoration.BorderDecoration
import com.thoughtworks.ark.core.logging.printer.decoration.ThreadInfoDecoration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        Logger.setUp {
            it.setLogLevel(Log.DEBUG)
            it.addPrinter(
                AndroidPrinter(decorations = listOf(BorderDecoration(), ThreadInfoDecoration()))
            )
        }
        Logger.onceTag("SampleApp").i("onCreate")
    }

    override fun newImageLoader() = ImageLoader.Builder(this)
        .components { add(SvgDecoder.Factory()) }
        .build()
}
