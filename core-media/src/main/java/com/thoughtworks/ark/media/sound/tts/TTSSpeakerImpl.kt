package com.thoughtworks.ark.media.sound.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.*

class TTSSpeakerImpl(context: Context) : TTSSpeaker {
    private val textToSpeech = TextToSpeech(context) {
        if (it == TextToSpeech.ERROR) {
            Log.w(TAG, "TTS init failed!")
        }
    }

    init {
        textToSpeech.language = Locale.getDefault()
    }

    override fun play(ttsItem: TTSItem) {
        stop()
        textToSpeech.speak(ttsItem.content, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun stop() {
        textToSpeech.stop()
    }

    override fun release() {
        textToSpeech.shutdown()
    }

    companion object {
        private const val TAG = "TTSSpeaker"
    }
}