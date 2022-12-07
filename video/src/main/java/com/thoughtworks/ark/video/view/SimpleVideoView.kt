@file:Suppress("TooManyFunctions")

package com.thoughtworks.ark.video.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.thoughtworks.ark.video.R
import com.thoughtworks.ark.video.VideoItem
import com.thoughtworks.ark.video.utils.createExoplayer
import com.thoughtworks.ark.video.utils.isSameVideo
import com.thoughtworks.ark.video.utils.toMediaItem

class SimpleVideoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    createVideoOverlay: (Context, ExoPlayer) -> View? = { _, _ -> null }
) : FrameLayout(context, attrs) {
    private val playView: StyledPlayerView
    private var exoPlayer: ExoPlayer

    private val videoOverlayViewId = View.generateViewId()
    private var oldVideoItem: VideoItem? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_simple_video_view, this, true)
        exoPlayer = createExoplayer(context)
        playView = findViewById(R.id.player_view)
        playView.player = exoPlayer
        enableDefaultLoading(true)

        val videoOverlayView = createVideoOverlay(context, exoPlayer)
        videoOverlayView?.let {
            it.id = videoOverlayViewId
            addView(it, ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT))
        }
    }

    fun getVideoOverlayView(): View? {
        return findViewById(videoOverlayViewId)
    }

    fun getVideoPlayer(): ExoPlayer {
        return exoPlayer
    }

    fun volumeOff() {
        exoPlayer.volume = 0f
    }

    fun volumeOn() {
        exoPlayer.volume = 1f
    }

    fun play(videoItem: VideoItem) {
        enableDefaultControl(videoItem.enableDefaultControl)
        enableDefaultLoading(videoItem.enableDefaultLoading)
        setResizeMode(videoItem.resizeMode.mode)

        if (!oldVideoItem.isSameVideo(videoItem)) {
            exoPlayer.startPlay(videoItem)
            oldVideoItem = videoItem
        }
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

    fun release() {
        exoPlayer.release()
    }

    fun setResizeMode(resizeMode: Int) {
        playView.resizeMode = resizeMode
    }

    fun enableDefaultControl(enable: Boolean) {
        playView.useController = enable
    }

    fun enableDefaultLoading(enable: Boolean) {
        if (enable) {
            playView.setShowBuffering(StyledPlayerView.SHOW_BUFFERING_ALWAYS)
        } else {
            playView.setShowBuffering(StyledPlayerView.SHOW_BUFFERING_NEVER)
        }
    }

    private fun ExoPlayer.startPlay(videoItem: VideoItem) {
        val mediaItem = videoItem.toMediaItem()
        if (mediaItem != null) {
            playWhenReady = true
            setMediaItem(mediaItem)
            prepare()
        }
    }
}