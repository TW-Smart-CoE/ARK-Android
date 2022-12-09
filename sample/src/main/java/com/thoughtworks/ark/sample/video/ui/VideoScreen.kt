package com.thoughtworks.ark.sample.video.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.ark.sample.video.VideoViewModel

@Composable
fun VideoScreen(viewModel: VideoViewModel = viewModel()) {
    val screenState = viewModel.screenState.value

    Box(modifier = Modifier.fillMaxSize()) {
        when (screenState) {
            is VideoScreenState.SimpleVideoPlay -> {
                SimpleVideoPlayScreen(screenState.videoItem)
            }
            is VideoScreenState.CrossFadeVideoPlay -> {
                CrossFadeVideoPlayScreen(videoItem = screenState.videoItem)
            }
            is VideoScreenState.CustomControlVideoPlay -> {
                CustomControlVideoPlayScreen(videoItem = screenState.videoItem)
            }
            else -> {
                VideoMainScreen(viewModel)
            }
        }
    }

    BackHandler(screenState != VideoScreenState.Main) {
        viewModel.onBackPressed()
    }
}