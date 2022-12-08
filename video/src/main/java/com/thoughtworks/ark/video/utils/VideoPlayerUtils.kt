package com.thoughtworks.ark.video.utils

import android.net.Uri
import com.google.android.exoplayer2.MediaItem
import com.thoughtworks.ark.video.VideoItem

fun VideoItem.toMediaItem(): MediaItem? {
    return if (videoAsset.isNotEmpty()) {
        MediaItem.Builder()
            .setUri(Uri.parse("asset:///$videoAsset").toString())
            .build()
    } else if (videoFile != null) {
        MediaItem.Builder()
            .setUri(Uri.fromFile(videoFile).toString())
            .build()
    } else if (videoUrl.isNotEmpty()) {
        MediaItem.Builder()
            .setUri(videoUrl)
            .build()
    } else {
        null
    }
}

fun VideoItem?.isSameVideo(other: VideoItem): Boolean {
    if (this == null) return false
    return videoFile?.equals(other.videoFile) == true ||
        (videoAsset.isNotEmpty() && videoAsset == other.videoAsset) ||
        (videoUrl.isNotEmpty() && videoUrl == other.videoUrl)
}