package com.thoughtworks.ark.media.sound.media

interface MediaPlayer {
    fun play(mediaItem: MediaItem)

    fun pause()

    fun resume()

    fun stop()

    fun release()
}