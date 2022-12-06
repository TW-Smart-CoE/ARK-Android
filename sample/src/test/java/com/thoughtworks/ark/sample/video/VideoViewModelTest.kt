package com.thoughtworks.ark.sample.video

import com.google.common.truth.Truth.assertThat
import com.thoughtworks.ark.core.testing.util.MainDispatcherRule
import com.thoughtworks.ark.sample.video.ui.VideoScreenState
import org.junit.Rule
import org.junit.Test

class VideoViewModelTest {
    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    private val viewModel = VideoViewModel()

    @Test
    fun shouldBeSimpleVideoPlayStateWhenCalledOpenSimpleVideoPlayScreen() {
        viewModel.openSimpleVideoPlayScreen()

        assertThat(viewModel.screenState.value).isInstanceOf(VideoScreenState.SimpleVideoPlay::class.java)
    }

    @Test
    fun shouldBeCrossFadeVideoPlayStateWhenCalledOpenCrossFadeVideoPlayScreen() {
        viewModel.openCrossFadeVideoPlayScreen()

        assertThat(viewModel.screenState.value).isInstanceOf(VideoScreenState.CrossFadeVideoPlay::class.java)
    }

    @Test
    fun shouldBeCustomControlPlayStateWhenCalledOpenCustomControlPlayScreen() {
        viewModel.openCustomControlPlayScreen()

        assertThat(viewModel.screenState.value).isInstanceOf(VideoScreenState.CustomControlVideoPlay::class.java)
    }

    @Test
    fun shouldBeMainStateWhenCalledOnBackPressed() {
        viewModel.onBackPressed()

        assertThat(viewModel.screenState.value).isEqualTo(VideoScreenState.Main)
    }
}