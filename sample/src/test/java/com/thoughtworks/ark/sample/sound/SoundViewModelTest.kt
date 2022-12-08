package com.thoughtworks.ark.sample.sound

import com.thoughtworks.ark.sound.alert.AlertItem
import com.thoughtworks.ark.sound.alert.AlertPlayer
import com.thoughtworks.ark.sound.media.MediaItem
import com.thoughtworks.ark.sound.media.MediaPlayer
import com.thoughtworks.ark.sound.tts.TTSItem
import com.thoughtworks.ark.sound.tts.TTSSpeaker
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class SoundViewModelTest {
    private val alertPlayer = mockk<AlertPlayer>()
    private val mediaPlayer = mockk<MediaPlayer>()
    private val ttsSpeaker = mockk<TTSSpeaker>()

    private val viewModel = SoundViewModel(alertPlayer, mediaPlayer, ttsSpeaker)

    @Before
    fun setUp() {
        every { alertPlayer.play(any()) } returns Unit
        every { mediaPlayer.play(any()) } returns Unit
        every { ttsSpeaker.play(any()) } returns Unit
    }

    @Test
    fun testPlayAlert() {
        viewModel.playAlert(AlertItem.fromRes(1))

        verify { alertPlayer.play(any()) }
    }

    @Test
    fun testPlayMedia() {
        viewModel.playMedia(MediaItem.fromRes(1))

        verify { mediaPlayer.play(any()) }
    }

    @Test
    fun testPlayTts() {
        viewModel.playTts(TTSItem("test"))

        verify { ttsSpeaker.play(any()) }
    }
}