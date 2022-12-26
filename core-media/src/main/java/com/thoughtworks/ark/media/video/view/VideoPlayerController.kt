@file:Suppress("TooManyFunctions")

package com.thoughtworks.ark.media.video.view

import com.thoughtworks.ark.media.video.VideoItem
import com.thoughtworks.ark.media.video.player.VideoPlayState
import com.thoughtworks.ark.media.video.player.VideoPlayer
import com.thoughtworks.ark.media.video.player.VideoPlayerListener
import com.thoughtworks.ark.media.video.utils.toVideoDataSource

class VideoPlayerController(private val videoPlayer: VideoPlayer) {

    fun getPlayer(): VideoPlayer {
        return videoPlayer
    }

    fun addListener(videoPlayerListener: VideoPlayerListener) {
        videoPlayer.addListener(videoPlayerListener)
    }

    fun removeListener(videoPlayerListener: VideoPlayerListener) {
        videoPlayer.removeListener(videoPlayerListener)
    }

    fun isPlaying(): Boolean {
        return videoPlayer.isPlaying()
    }

    fun isPlayEnd(): Boolean {
        return videoPlayer.getPlayState() == VideoPlayState.STATE_ENDED
    }

    fun play(videoItem: VideoItem) {
        videoPlayer.play(videoItem.toVideoDataSource())
    }

    fun replay() {
        videoPlayer.replay()
    }

    fun pause() {
        videoPlayer.pause()
    }

    fun resume() {
        videoPlayer.resume()
    }

    fun stop() {
        videoPlayer.stop()
    }

    fun volumeOff() {
        videoPlayer.volumeOff()
    }

    fun volumeOn() {
        videoPlayer.volumeOn()
    }

    fun release() {
        videoPlayer.release()
    }
}