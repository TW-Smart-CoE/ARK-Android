package com.thoughtworks.ark.sample

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SampleApplication : Application(), ImageLoaderFactory {
    override fun newImageLoader() = ImageLoader.Builder(this)
        .components { add(SvgDecoder.Factory()) }
        .build()
}
