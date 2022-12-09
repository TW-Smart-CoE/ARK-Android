package com.thoughtworks.ark.video.utils

import com.thoughtworks.ark.video.VideoItem
import com.thoughtworks.ark.video.player.VideoDataSource

fun VideoItem.toVideoDataSource(): VideoDataSource {
    return VideoDataSource(videoUri)
}

fun VideoItem?.isSameVideo(other: VideoItem): Boolean {
    if (this == null) return false
    return videoUri == other.videoUri
}