package com.thoughtworks.ark.sound.tts

interface TTSSpeaker {
    fun play(ttsItem: TTSItem)

    fun stop()

    fun release()
}