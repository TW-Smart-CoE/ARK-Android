package com.thoughtworks.ark.sample.video.ui

import com.thoughtworks.ark.media.video.VideoItem

sealed class VideoScreenState {
    object Main : VideoScreenState()
    class SimpleVideoPlay(val videoItem: VideoItem) : VideoScreenState()
    class CrossFadeVideoPlay(val videoItem: VideoItem) : VideoScreenState()
    class CustomControlVideoPlay(val videoItem: VideoItem) : VideoScreenState()
}