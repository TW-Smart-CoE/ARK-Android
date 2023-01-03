package com.thoughtworks.ark.sample.video.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.thoughtworks.ark.media.video.SimpleVideoView
import com.thoughtworks.ark.media.video.VideoItem

@Composable
fun SimpleVideoPlayScreen(videoItem: VideoItem) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        SimpleVideoView(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            videoItem = videoItem
        )
    }
}