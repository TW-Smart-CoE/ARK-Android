package com.thoughtworks.ark.sound.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class TTSSpeaker(context: Context) {
    private val textToSpeech = TextToSpeech(context) {}

    init {
        textToSpeech.language = Locale.getDefault()
    }

    fun play(ttsItem: TTSItem) {
        stop()
        textToSpeech.speak(ttsItem.content, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun stop() {
        textToSpeech.stop()
    }

    fun release() {
        textToSpeech.shutdown()
    }
}