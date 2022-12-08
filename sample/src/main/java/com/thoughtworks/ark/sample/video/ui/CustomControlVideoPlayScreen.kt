package com.thoughtworks.ark.sample.video.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.thoughtworks.ark.sample.R
import com.thoughtworks.ark.video.SimpleVideoView
import com.thoughtworks.ark.video.VideoItem
import com.thoughtworks.ark.video.player.VideoPlayState
import com.thoughtworks.ark.video.player.VideoPlayerListener
import com.thoughtworks.ark.video.view.VideoPlayerController
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CustomControlVideoPlayScreen(videoItem: VideoItem) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SimpleVideoView(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            videoItem = videoItem,
            createVideoOverlay = { ctx, exoplayer ->
                CustomVideoControlView(ctx).apply {
                    setupPlayer(exoplayer)
                }
            }
        )
    }
}

private class CustomVideoControlView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private var loading: ProgressBar
    private var btnPlay: ImageView

    private lateinit var videoPlayerController: VideoPlayerController

    private val coroutineScope = MainScope()
    private var hideControlJob: Job? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_video_control_layout, this, true)
        loading = findViewById(R.id.loading)
        btnPlay = findViewById(R.id.btn_play)
        setupButtonClick()
    }

    fun setupPlayer(videoPlayerController: VideoPlayerController) {
        this.videoPlayerController = videoPlayerController

        videoPlayerController.addListener(object : VideoPlayerListener {
            override fun onIsLoadingChanged(isLoading: Boolean) {
                loading.visibility = if (isLoading) View.VISIBLE else View.GONE
            }

            override fun onPlayStateChanged(state: Int) {
                updatePlayPauseButton(state)
            }
        })

        setOnClickListener {
            if (videoPlayerController.isPlaying()) {
                showControl()
                autoHideControl()
            }
        }
    }

    private fun autoHideControl() {
        hideControlJob?.cancel()
        hideControlJob = coroutineScope.launch {
            delay(DISMISS_TIME)
            hideControl()
        }
    }

    private fun showControl() {
        btnPlay.visibility = View.VISIBLE
        loading.visibility = View.GONE
    }

    private fun hideControl() {
        btnPlay.visibility = View.GONE
        loading.visibility = View.GONE
    }

    private fun setupButtonClick() {
        btnPlay.setOnClickListener {
            if (videoPlayerController.isPlaying()) {
                btnPlay.setImageResource(R.drawable.ic_play)
                videoPlayerController.pause()
                hideControlJob?.cancel()
            } else {
                btnPlay.setImageResource(R.drawable.ic_pause)
                if (videoPlayerController.isPlayEnd()) {
                    videoPlayerController.replay()
                } else {
                    videoPlayerController.resume()
                }
                autoHideControl()
            }
        }
    }

    private fun updatePlayPauseButton(state: Int) {
        if (state == VideoPlayState.STATE_ENDED) {
            hideControlJob?.cancel()
            btnPlay.setImageResource(R.drawable.ic_play)
            btnPlay.visibility = View.VISIBLE
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        coroutineScope.cancel()
    }

    companion object {
        const val DISMISS_TIME = 3000L
    }
}