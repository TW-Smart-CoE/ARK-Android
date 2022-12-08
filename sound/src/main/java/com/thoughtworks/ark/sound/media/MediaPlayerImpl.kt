package com.thoughtworks.ark.sound.media

import android.content.Context
import android.media.MediaPlayer as AndroidMediaMediaPlayer

class MediaPlayerImpl(private val context: Context) : MediaPlayer {
    private val androidMediaPlayer = AndroidMediaMediaPlayer()

    override fun play(mediaItem: MediaItem) {
        androidMediaPlayer.reset()

        when {
            mediaItem.mediaRes != 0 -> {
                context.resources.openRawResourceFd(mediaItem.mediaRes).use {
                    androidMediaPlayer.setDataSource(it)
                    prepareAndStart()
                }
            }
            mediaItem.mediaAsset.isNotEmpty() -> {
                context.assets.openFd(mediaItem.mediaAsset).use {
                    androidMediaPlayer.setDataSource(it)
                    prepareAndStart()
                }
            }
            mediaItem.mediaFile != null -> {
                androidMediaPlayer.setDataSource(mediaItem.mediaFile.path)
                prepareAndStart()
            }
        }
    }

    private fun prepareAndStart() {
        androidMediaPlayer.prepare()
        androidMediaPlayer.start()
    }

    override fun pause() {
        androidMediaPlayer.pause()
    }

    override fun resume() {
        androidMediaPlayer.start()
    }

    override fun stop() {
        androidMediaPlayer.stop()
    }

    override fun release() {
        androidMediaPlayer.release()
    }
}