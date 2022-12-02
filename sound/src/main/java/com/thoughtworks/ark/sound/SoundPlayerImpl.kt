package com.thoughtworks.ark.sound

import android.content.Context
import com.thoughtworks.ark.sound.alert.AlertItem
import com.thoughtworks.ark.sound.alert.AlertPlayer
import com.thoughtworks.ark.sound.media.MediaItem
import com.thoughtworks.ark.sound.media.MediaPlayer
import com.thoughtworks.ark.sound.tts.TTSItem
import com.thoughtworks.ark.sound.tts.TTSSpeaker

class SoundPlayerImpl(private val context: Context) : SoundPlayer {
    private var alertPlayer: AlertPlayer? = null
    private var ttsSpeaker: TTSSpeaker? = null
    private var mediaPlayer: MediaPlayer? = null

    override fun prepareSounds(soundItems: List<SoundItem>) {
        val alertItems = soundItems.filterIsInstance<AlertItem>()
        if (alertItems.isNotEmpty()) {
            initAlertPlayer()
            alertPlayer?.prepareSounds(alertItems)
        }
    }

    override fun play(soundItem: SoundItem) {
        when (soundItem) {
            is TTSItem -> {
                initTTSSpeaker()
                ttsSpeaker?.play(soundItem)
            }
            is AlertItem -> {
                initAlertPlayer()
                alertPlayer?.play(soundItem)
            }
            is MediaItem -> {
                initMediaPlayer()
                mediaPlayer?.play(soundItem)
            }
        }
    }

    override fun pause(soundItem: SoundItem) {
        if (soundItem is AlertItem) {
            alertPlayer?.pause()
        } else if (soundItem is MediaItem) {
            mediaPlayer?.pause()
        }
    }

    override fun resume(soundItem: SoundItem) {
        if (soundItem is AlertItem) {
            alertPlayer?.resume()
        } else if (soundItem is MediaItem) {
            mediaPlayer?.resume()
        }
    }

    override fun stop(soundItem: SoundItem) {
        when (soundItem) {
            is TTSItem -> {
                ttsSpeaker?.stop()
            }
            is AlertItem -> {
                alertPlayer?.stop()
            }
            is MediaItem -> {
                mediaPlayer?.stop()
            }
        }
    }

    override fun release() {
        ttsSpeaker?.release()
        alertPlayer?.release()
        mediaPlayer?.release()
    }

    private fun initTTSSpeaker() {
        if (ttsSpeaker == null) {
            ttsSpeaker = TTSSpeaker(context)
        }
    }

    private fun initAlertPlayer() {
        if (alertPlayer == null) {
            alertPlayer = AlertPlayer(context)
        }
    }

    private fun initMediaPlayer() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer(context)
        }
    }
}