package com.thoughtworks.ark.video.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.thoughtworks.ark.video.R
import com.thoughtworks.ark.video.VideoItem

class RecyclerVideoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private val playView: StyledPlayerView
    private var videoOverlayView: View? = null

    private lateinit var videoPlayerController: VideoPlayerController

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

    fun setUp(createVideoOverlay: (Context, VideoPlayerController) -> View?) {
        firstReadyFlag = true
        videoPlayerController = VideoPlayerController(context)
        videoPlayerController.addListener(listener)

        playView.player = videoPlayerController.getPlayer()

        if (videoOverlayView == null) {
            val videoOverlayView = createVideoOverlay(context, videoPlayerController)
            videoOverlayView?.let {
                addView(it, ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT))
                this.videoOverlayView = it
            }
        }
    }

    fun getVideoOverlayView(): View? {
        return videoOverlayView
    }

    fun getVideoPlayController(): VideoPlayerController {
        return videoPlayerController
    }

    fun clear() {
        videoPlayerController.removeListener(listener)
        videoPlayerController.release()
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

        videoPlayerController.play(videoItem)
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
}