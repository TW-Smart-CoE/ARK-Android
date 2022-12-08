package com.thoughtworks.ark.video.player

interface VideoPlayerListener {
    fun onIsLoadingChanged(isLoading: Boolean) {}

    fun onPlayStateChanged(state: Int) {}
}