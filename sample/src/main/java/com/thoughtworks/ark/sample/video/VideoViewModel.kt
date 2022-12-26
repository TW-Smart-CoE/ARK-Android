package com.thoughtworks.ark.sample.video

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoughtworks.ark.media.video.VideoItem
import com.thoughtworks.ark.sample.video.ui.VideoScreenState
import com.thoughtworks.ark.sample.video.ui.VideoScreenState.CrossFadeVideoPlay
import com.thoughtworks.ark.sample.video.ui.VideoScreenState.CustomControlVideoPlay
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VideoViewModel : ViewModel() {
    private var job: Job? = null
    val screenState = mutableStateOf<VideoScreenState>(VideoScreenState.Main)

    fun openSimpleVideoPlayScreen() {
        job?.cancel()
        screenState.value = VideoScreenState.SimpleVideoPlay(VideoItem.fromAsset("test.mp4"))
    }

    fun openCrossFadeVideoPlayScreen() {
        job?.cancel()
        job = viewModelScope.launch {
            screenState.value = CrossFadeVideoPlay(VideoItem.fromUrl(TEST_VIDEO_1, enableDefaultControl = false))
            delay(CROSS_FADE_DELAY_TIME)
            screenState.value = CrossFadeVideoPlay(VideoItem.fromUrl(TEST_VIDEO_2, enableDefaultControl = false))
        }
    }

    fun openCustomControlPlayScreen() {
        job?.cancel()
        screenState.value = CustomControlVideoPlay(
            VideoItem.fromUrl(
                TEST_VIDEO_1,
                enableDefaultControl = false,
                enableDefaultLoading = false
            )
        )
    }

    fun onBackPressed() {
        job?.cancel()
        screenState.value = VideoScreenState.Main
    }

    companion object {
        private const val TEST_VIDEO_1 =
            "https://test-videos.co.uk/vids/bigbuckbunny/mp4/h265/1080/Big_Buck_Bunny_1080_10s_1MB.mp4"
        private const val TEST_VIDEO_2 = "https://test-videos.co.uk/vids/sintel/mp4/h264/1080/Sintel_1080_10s_1MB.mp4"

        private const val CROSS_FADE_DELAY_TIME = 10000L
    }
}