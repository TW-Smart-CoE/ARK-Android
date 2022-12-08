package com.thoughtworks.ark.sound.media

import java.io.File

class MediaItem private constructor(
    val mediaRes: Int = 0,
    val mediaAsset: String = "",
    val mediaFile: File? = null
) {
    companion object {
        fun fromRes(resId: Int): MediaItem {
            require(resId > 0) { "Invalid resId!" }
            return MediaItem(mediaRes = resId)
        }

        fun fromAsset(assetPath: String): MediaItem {
            require(assetPath.isNotEmpty()) { "Invalid assetPath!" }
            return MediaItem(mediaAsset = assetPath)
        }

        fun fromFile(file: File): MediaItem {
            require(file.exists() && file.isFile) { "Invalid file!" }
            return MediaItem(mediaFile = file)
        }
    }
}