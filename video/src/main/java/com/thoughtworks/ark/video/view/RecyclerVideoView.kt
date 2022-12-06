package com.thoughtworks.ark.video.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.thoughtworks.ark.video.R
import com.thoughtworks.ark.video.VideoItem
import com.thoughtworks.ark.video.utils.createExoplayer
import com.thoughtworks.ark.video.utils.toMediaItem

class RecyclerVideoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private val videoOverlayViewId = View.generateViewId()

    private val playView: StyledPlayerView
    private lateinit var exoPlayer: ExoPlayer

    private var onPlayStart: () -> Unit = {}
    private var onPlayEnd: () -> Unit = {}

    private var firstReadyFlag = true

    private val listener = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            if (playbackState == Player.STATE_READY) {
                if (firstReadyFlag) {
                    onPlayStart()
                    firstReadyFlag = false
                }
            } else if (playbackState == Player.STATE_ENDED) {
                onPlayEnd()
            }
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_recycler_video_view, this, true)
        playView = findViewById(R.id.player_view)
        enableDefaultLoading(true)
    }

    fun setUp(createVideoOverlay: (Context, ExoPlayer) -> View?) {
        firstReadyFlag = true
        exoPlayer = createExoplayer(context)
        exoPlayer.addListener(listener)
        playView.player = exoPlayer

        if (findViewById<View>(videoOverlayViewId) == null) {
            val videoOverlayView = createVideoOverlay(context, exoPlayer)
            videoOverlayView?.let {
                it.id = videoOverlayViewId
                addView(it, ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT))
            }
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
        exoPlayer.removeListener(listener)
        exoPlayer.release()
        if (parent != null) {
            (parent as ViewGroup).removeView(this)
        }
    }

    fun play(videoItem: VideoItem, onPlayStart: () -> Unit, onPlayEnd: () -> Unit) {
        this.onPlayStart = onPlayStart
        this.onPlayEnd = onPlayEnd

        enableDefaultControl(videoItem.enableDefaultControl)
        enableDefaultLoading(videoItem.enableDefaultLoading)
        setResizeMode(videoItem.resizeMode.mode)

        exoPlayer.startPlay(videoItem)
    }

    fun replay() {
        exoPlayer.seekTo(0)
        exoPlayer.play()
    }

    fun updateAlpha(newAlpha: Float) {
        playView.videoSurfaceView?.alpha = newAlpha
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