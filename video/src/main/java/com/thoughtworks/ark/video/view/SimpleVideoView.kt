package com.thoughtworks.ark.video.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.thoughtworks.ark.video.R
import com.thoughtworks.ark.video.VideoItem
import com.thoughtworks.ark.video.utils.isSameVideo

class SimpleVideoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    createVideoOverlay: (Context, VideoPlayerController) -> View? = { _, _ -> null }
) : FrameLayout(context, attrs) {
    private val playView: StyledPlayerView
    private val videoPlayerController = VideoPlayerController(context)

    private var videoOverlayView: View? = null
    private var oldVideoItem: VideoItem? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_simple_video_view, this, true)
        playView = findViewById(R.id.player_view)
        playView.player = videoPlayerController.getPlayer()
        enableDefaultLoading(true)

        val videoOverlayView = createVideoOverlay(context, videoPlayerController)
        videoOverlayView?.let {
            addView(it, ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT))
            this.videoOverlayView = it
        }
    }

    fun getVideoOverlayView(): View? {
        return videoOverlayView
    }

    fun getVideoPlayController(): VideoPlayerController {
        return videoPlayerController
    }

    fun play(videoItem: VideoItem) {
        enableDefaultControl(videoItem.enableDefaultControl)
        enableDefaultLoading(videoItem.enableDefaultLoading)
        setResizeMode(videoItem.resizeMode.mode)

        if (!oldVideoItem.isSameVideo(videoItem)) {
            videoPlayerController.play(videoItem)
            oldVideoItem = videoItem
        }
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