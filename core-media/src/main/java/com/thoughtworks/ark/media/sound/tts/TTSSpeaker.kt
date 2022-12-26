package com.thoughtworks.ark.media.sound.tts

interface TTSSpeaker {
    fun play(ttsItem: TTSItem)

    fun stop()

    fun release()
}