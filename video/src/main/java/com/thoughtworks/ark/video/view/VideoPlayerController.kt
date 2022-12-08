@file:Suppress("TooManyFunctions")

package com.thoughtworks.ark.video.view

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.Listener
import com.thoughtworks.ark.video.VideoItem
import com.thoughtworks.ark.video.utils.toMediaItem

class VideoPlayerController(context: Context) {
    private val exoPlayer = createExoplayer(context)

    private fun createExoplayer(context: Context): ExoPlayer {
        return ExoPlayer.Builder(context).build()
    }

    fun getPlayer(): Player {
        return exoPlayer
    }

    fun addListener(listener: Listener) {
        exoPlayer.addListener(listener)
    }

    fun removeListener(listener: Listener) {
        exoPlayer.removeListener(listener)
    }

    fun isPlaying(): Boolean {
        return exoPlayer.isPlaying
    }

    fun isPlayEnd(): Boolean {
        return exoPlayer.playbackState == Player.STATE_ENDED
    }

    fun canPause(): Boolean {
        return exoPlayer.playbackState != Player.STATE_ENDED &&
            exoPlayer.playbackState != Player.STATE_IDLE &&
            exoPlayer.playWhenReady
    }

    fun play(videoItem: VideoItem) {
        startPlay(videoItem)
    }

    fun replay() {
        exoPlayer.seekTo(0)
        exoPlayer.play()
    }

    fun pause() {
        exoPlayer.pause()
    }

    fun resume() {
        exoPlayer.play()
    }

    fun stop() {
        exoPlayer.stop()
    }

    fun volumeOff() {
        exoPlayer.volume = 0f
    }

    fun volumeOn() {
        exoPlayer.volume = 1f
    }

    fun release() {
        exoPlayer.release()
    }

    private fun startPlay(videoItem: VideoItem) {
        val mediaItem = videoItem.toMediaItem()
        mediaItem?.let {
            exoPlayer.playWhenReady = true
            exoPlayer.setMediaItem(mediaItem)
            exoPlayer.prepare()
        }
    }
}