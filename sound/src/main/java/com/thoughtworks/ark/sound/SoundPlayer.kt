package com.thoughtworks.ark.sound

interface SoundPlayer {
    fun prepareSounds(soundItems: List<SoundItem>)

    fun play(soundItem: SoundItem)

    fun pause(soundItem: SoundItem)

    fun resume(soundItem: SoundItem)

    fun stop(soundItem: SoundItem)

    fun release()
}