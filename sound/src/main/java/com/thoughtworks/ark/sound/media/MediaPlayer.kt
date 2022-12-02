package com.thoughtworks.ark.sound.media

import android.content.Context
import android.media.MediaPlayer as AndroidMediaMediaPlayer

class MediaPlayer(private val context: Context) {
    private val androidMediaPlayer = AndroidMediaMediaPlayer()

    fun play(mediaItem: MediaItem) {
        androidMediaPlayer.reset()

        when {
            mediaItem.soundRes != 0 -> {
                context.resources.openRawResourceFd(mediaItem.soundRes).use {
                    androidMediaPlayer.setDataSource(context.resources.openRawResourceFd(mediaItem.soundRes))
                    androidMediaPlayer.prepare()
                    androidMediaPlayer.start()
                }
            }
            mediaItem.soundAsset.isNotEmpty() -> {
                context.assets.openFd(mediaItem.soundAsset).use {
                    androidMediaPlayer.setDataSource(it)
                    androidMediaPlayer.prepare()
                    androidMediaPlayer.start()
                }
            }
            mediaItem.soundFile != null -> {
                androidMediaPlayer.setDataSource(mediaItem.soundFile.path)
                androidMediaPlayer.prepare()
                androidMediaPlayer.start()
            }
        }
    }

    fun pause() {
        androidMediaPlayer.pause()
    }

    fun resume() {
        androidMediaPlayer.start()
    }

    fun stop() {
        androidMediaPlayer.stop()
    }

    fun release() {
        androidMediaPlayer.release()
    }
}