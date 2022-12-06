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
                    androidMediaPlayer.setDataSource(context.resources.openRawResourceFd(mediaItem.mediaRes))
                    androidMediaPlayer.prepare()
                    androidMediaPlayer.start()
                }
            }
            mediaItem.mediaAsset.isNotEmpty() -> {
                context.assets.openFd(mediaItem.mediaAsset).use {
                    androidMediaPlayer.setDataSource(it)
                    androidMediaPlayer.prepare()
                    androidMediaPlayer.start()
                }
            }
            mediaItem.mediaFile != null -> {
                androidMediaPlayer.setDataSource(mediaItem.mediaFile.path)
                androidMediaPlayer.prepare()
                androidMediaPlayer.start()
            }
        }
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