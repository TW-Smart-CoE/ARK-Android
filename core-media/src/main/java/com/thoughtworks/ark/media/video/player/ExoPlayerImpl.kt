@file:Suppress("TooManyFunctions")

package com.thoughtworks.ark.media.video.player

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player.Listener

class ExoPlayerImpl(context: Context) : VideoPlayer {
    private val exoPlayer = createExoplayer(context)
    private val listeners = mutableListOf<VideoPlayerListener>()

    fun getExoPlayer(): ExoPlayer {
        return exoPlayer
    }

    private fun createExoplayer(context: Context): ExoPlayer {
        val player = ExoPlayer.Builder(context).build()
        player.addListener(object : Listener {
            override fun onIsLoadingChanged(isLoading: Boolean) {
                listeners.forEach { it.onIsLoadingChanged(isLoading) }
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                listeners.forEach { it.onPlayStateChanged(playbackState) }
            }
        })
        return player
    }

    override fun addListener(videoPlayerListener: VideoPlayerListener) {
        listeners.add(videoPlayerListener)
    }

    override fun removeListener(videoPlayerListener: VideoPlayerListener) {
        listeners.remove(videoPlayerListener)
    }

    override fun play(videoDataSource: VideoDataSource) {
        val mediaItem = MediaItem.Builder()
            .setUri(videoDataSource.videoUri)
            .build()
        exoPlayer.playWhenReady = true
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
    }

    override fun replay() {
        exoPlayer.seekTo(0)
        exoPlayer.play()
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun resume() {
        exoPlayer.play()
    }

    override fun stop() {
        exoPlayer.stop()
    }

    override fun volumeOff() {
        exoPlayer.volume = 0f
    }

    override fun volumeOn() {
        exoPlayer.volume = 1f
    }

    override fun release() {
        exoPlayer.release()
    }

    override fun getPlayState(): Int {
        return exoPlayer.playbackState
    }

    override fun isPlaying(): Boolean {
        return exoPlayer.isPlaying
    }
}