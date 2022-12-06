package com.thoughtworks.ark.sample.sound

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.thoughtworks.ark.sound.alert.AlertItem
import com.thoughtworks.ark.sound.alert.AlertPlayerImpl
import com.thoughtworks.ark.sound.media.MediaItem
import com.thoughtworks.ark.sound.media.MediaPlayerImpl
import com.thoughtworks.ark.sound.tts.TTSItem
import com.thoughtworks.ark.sound.tts.TTSSpeakerImpl

class SoundViewModel(application: Application) : AndroidViewModel(application) {
    private val alertPlayer by lazy { AlertPlayerImpl(application) }
    private val mediaPlayer by lazy { MediaPlayerImpl(application) }
    private val ttsSpeaker by lazy { TTSSpeakerImpl(application) }

    fun playAlert(alertItem: AlertItem) {
        alertPlayer.play(alertItem)
    }

    fun playMedia(mediaItem: MediaItem) {
        mediaPlayer.play(mediaItem)
    }

    fun playTts(ttsItem: TTSItem) {
        ttsSpeaker.play(ttsItem)
    }
}