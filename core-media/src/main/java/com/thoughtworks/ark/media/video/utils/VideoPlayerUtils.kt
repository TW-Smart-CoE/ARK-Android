package com.thoughtworks.ark.media.video.utils

import com.thoughtworks.ark.media.video.VideoItem
import com.thoughtworks.ark.media.video.player.VideoDataSource

fun VideoItem.toVideoDataSource(): VideoDataSource {
    return VideoDataSource(videoUri)
}

fun VideoItem?.isSameVideo(other: VideoItem): Boolean {
    if (this == null) return false
    return videoUri == other.videoUri
}