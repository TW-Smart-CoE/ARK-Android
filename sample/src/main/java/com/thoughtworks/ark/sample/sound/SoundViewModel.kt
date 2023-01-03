package com.thoughtworks.ark.sample.sound

import androidx.lifecycle.ViewModel
import com.thoughtworks.ark.media.sound.alert.AlertItem
import com.thoughtworks.ark.media.sound.alert.AlertPlayer
import com.thoughtworks.ark.media.sound.media.MediaItem
import com.thoughtworks.ark.media.sound.media.MediaPlayer
import com.thoughtworks.ark.media.sound.tts.TTSItem
import com.thoughtworks.ark.media.sound.tts.TTSSpeaker
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SoundViewModel @Inject constructor(
    private val alertPlayer: AlertPlayer,
    private val mediaPlayer: MediaPlayer,
    private val ttsSpeaker: TTSSpeaker
) : ViewModel() {

    fun playAlert(alertItem: AlertItem) {
        alertPlayer.play(alertItem)
    }

    fun playMedia(mediaItem: MediaItem) {
        mediaPlayer.play(mediaItem)
    }

    fun playTts(ttsItem: TTSItem) {
        ttsSpeaker.play(ttsItem)
    }

    override fun onCleared() {
        super.onCleared()
        alertPlayer.release()
        mediaPlayer.release()
        ttsSpeaker.release()
    }
}