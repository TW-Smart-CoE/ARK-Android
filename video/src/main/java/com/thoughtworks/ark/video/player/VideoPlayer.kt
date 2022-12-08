@file:Suppress("TooManyFunctions")

package com.thoughtworks.ark.video.player

interface VideoPlayer {
    fun addListener(videoPlayerListener: VideoPlayerListener)

    fun removeListener(videoPlayerListener: VideoPlayerListener)

    fun play(videoDataSource: VideoDataSource)

    fun replay()

    fun pause()

    fun resume()

    fun stop()

    fun volumeOff()

    fun volumeOn()

    fun release()

    fun getPlayState(): Int

    fun isPlaying(): Boolean
}