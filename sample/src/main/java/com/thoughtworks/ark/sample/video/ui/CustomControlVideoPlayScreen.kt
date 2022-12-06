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
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.Listener
import com.thoughtworks.ark.sample.R
import com.thoughtworks.ark.video.SimpleVideoView
import com.thoughtworks.ark.video.VideoItem
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

    private lateinit var player: ExoPlayer

    private val coroutineScope = MainScope()
    private var job: Job? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_video_control_layout, this, true)
        loading = findViewById(R.id.loading)
        btnPlay = findViewById(R.id.btn_play)
    }

    fun setupPlayer(exoPlayer: ExoPlayer) {
        this.player = exoPlayer

        exoPlayer.addListener(object : Listener {
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
            if (player.isPlaying) {
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
        if (shouldShowPauseButton()) {
            btnPlay.setImageResource(R.drawable.ic_pause)
            btnPlay.setOnClickListener {
                player.pause()
                job?.cancel()
            }
        } else {
            btnPlay.setImageResource(R.drawable.ic_play)
            btnPlay.setOnClickListener {
                val state = player.playbackState
                if (state == Player.STATE_IDLE) {
                    player.prepare()
                } else if (state == Player.STATE_ENDED) {
                    player.seekTo(0)
                }
                player.play()
                autoHideControl()
            }

            if (player.playbackState == Player.STATE_ENDED) {
                job?.cancel()
                btnPlay.visibility = View.VISIBLE
            }
        }
    }

    private fun shouldShowPauseButton(): Boolean {
        return player.playbackState != Player.STATE_ENDED &&
            player.playbackState != Player.STATE_IDLE &&
            player.playWhenReady
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        coroutineScope.cancel()
    }

    companion object {
        const val DISMISS_TIME = 3000L
    }
}