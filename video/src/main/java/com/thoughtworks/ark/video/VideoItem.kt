package com.thoughtworks.ark.video

import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import java.io.File

class VideoItem private constructor(
    val videoFile: File? = null,
    val videoAsset: String = "",
    val videoUrl: String = "",
    val resizeMode: VideoResizeMode = VideoResizeMode.FIT,
    val enableDefaultControl: Boolean = true,
    val enableDefaultLoading: Boolean = true
) {
    companion object {
        fun fromUrl(
            url: String,
            resizeMode: VideoResizeMode = VideoResizeMode.FIT,
            enableDefaultControl: Boolean = true,
            enableDefaultLoading: Boolean = true
        ): VideoItem {
            return VideoItem(
                videoUrl = url,
                resizeMode = resizeMode,
                enableDefaultControl = enableDefaultControl,
                enableDefaultLoading = enableDefaultLoading
            )
        }

        fun fromAsset(
            assetPath: String,
            resizeMode: VideoResizeMode = VideoResizeMode.FIT,
            enableDefaultControl: Boolean = true,
            enableDefaultLoading: Boolean = true
        ): VideoItem {
            return VideoItem(
                videoAsset = assetPath,
                resizeMode = resizeMode,
                enableDefaultControl = enableDefaultControl,
                enableDefaultLoading = enableDefaultLoading
            )
        }

        fun fromFile(
            videoFile: File,
            resizeMode: VideoResizeMode = VideoResizeMode.FIT,
            enableDefaultControl: Boolean = true,
            enableDefaultLoading: Boolean = true
        ): VideoItem {
            return VideoItem(
                videoFile = videoFile,
                resizeMode = resizeMode,
                enableDefaultControl = enableDefaultControl,
                enableDefaultLoading = enableDefaultLoading
            )
        }
    }
}

enum class VideoResizeMode(val mode: Int) {
    FIT(AspectRatioFrameLayout.RESIZE_MODE_FIT),
    FILL(AspectRatioFrameLayout.RESIZE_MODE_FILL),
    ZOOM(AspectRatioFrameLayout.RESIZE_MODE_ZOOM),
    FIXED_WIDTH(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH),
    FIXED_HEIGHT(AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT)
}