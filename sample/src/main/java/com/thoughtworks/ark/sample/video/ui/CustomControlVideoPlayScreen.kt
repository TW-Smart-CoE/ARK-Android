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
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.Listener
import com.thoughtworks.ark.sample.R
import com.thoughtworks.ark.video.SimpleVideoView
import com.thoughtworks.ark.video.VideoItem
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
    private var job: Job? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_video_control_layout, this, true)
        loading = findViewById(R.id.loading)
        btnPlay = findViewById(R.id.btn_play)
    }

    fun setupPlayer(videoPlayerController: VideoPlayerController) {
        this.videoPlayerController = videoPlayerController

        videoPlayerController.addListener(object : Listener {
            override fun onIsLoadingChanged(isLoading: Boolean) {
                loading.visibility = if (isLoading) View.VISIBLE else View.GONE
            }

            override fun onEvents(player: Player, events: Player.Events) {
                if (events.containsAny(Player.EVENT_PLAYBACK_STATE_CHANGED, Player.EVENT_PLAY_WHEN_READY_CHANGED)) {
                    updatePlayPauseButton()
                }
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
        job?.cancel()
        job = coroutineScope.launch {
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

    private fun updatePlayPauseButton() {
        if (videoPlayerController.canPause()) {
            btnPlay.setImageResource(R.drawable.ic_pause)
            btnPlay.setOnClickListener {
                videoPlayerController.pause()
                job?.cancel()
            }
        } else {
            btnPlay.setImageResource(R.drawable.ic_play)
            btnPlay.setOnClickListener {
                if (videoPlayerController.isPlayEnd()) {
                    videoPlayerController.replay()
                } else {
                    videoPlayerController.resume()
                }
                autoHideControl()
            }

            if (videoPlayerController.isPlayEnd()) {
                job?.cancel()
                btnPlay.visibility = View.VISIBLE
            }
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