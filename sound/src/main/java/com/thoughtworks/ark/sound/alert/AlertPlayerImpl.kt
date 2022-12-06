package com.thoughtworks.ark.sound.alert

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.ConcurrentHashMap
import kotlin.Result.Companion.success

class AlertPlayerImpl(private val context: Context, maxStreams: Int = DEFAULT_MAX_STREAMS) : AlertPlayer {
    private val coroutineScope = MainScope()

    private val audioAttribute = AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build()
    private val soundPool = SoundPool.Builder().setMaxStreams(maxStreams).setAudioAttributes(audioAttribute).build()
    private val soundMap = ConcurrentHashMap<AlertItem, Int?>()

    private var currentPlayStreamId: Int = CURRENT_PLAY_STREAM_NONE

    override fun prepareSounds(alertItems: List<AlertItem>) {
        coroutineScope.launch(Dispatchers.IO) {
            alertItems.forEach {
                prepareSound(it)
            }
        }
    }

    override fun play(alertItem: AlertItem) {
        coroutineScope.launch {
            var soundId = soundMap[alertItem]
            if (soundId == null) {
                val loadDeferred = async(Dispatchers.IO) {
                    loadSound(alertItem)
                    awaitLoadComplete()
                }
                soundId = loadDeferred.await()
                soundMap[alertItem] = soundId
            }

            if (soundId != null) {
                currentPlayStreamId = soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
            } else {
                Log.w(TAG, "Alert sound play failed!")
            }
        }
    }

    override fun pause() {
        if (currentPlayStreamId != CURRENT_PLAY_STREAM_NONE) {
            soundPool.pause(currentPlayStreamId)
        }
    }

    override fun resume() {
        if (currentPlayStreamId != CURRENT_PLAY_STREAM_NONE) {
            soundPool.resume(currentPlayStreamId)
        }
    }

    override fun stop() {
        if (currentPlayStreamId != CURRENT_PLAY_STREAM_NONE) {
            soundPool.stop(currentPlayStreamId)
        }
    }

    override fun release() {
        stop()
        coroutineScope.cancel()
        soundPool.release()
    }

    private fun prepareSound(alertItem: AlertItem): Int? {
        if (soundMap[alertItem] == null) {
            val soundId = loadSound(alertItem)
            soundMap[alertItem] = soundId
        }
        return soundMap[alertItem]
    }

    private fun loadSound(alertItem: AlertItem): Int? {
        return when {
            alertItem.alertRes != 0 -> soundPool.load(context, alertItem.alertRes, 0)
            alertItem.alertAsset.isNotEmpty() -> {
                context.assets.openFd(alertItem.alertAsset).use {
                    soundPool.load(it, 0)
                }
            }
            alertItem.alertFile != null -> soundPool.load(alertItem.alertFile.path, 0)
            else -> null
        }
    }

    private suspend fun awaitLoadComplete(): Int? = suspendCancellableCoroutine {
        soundPool.setOnLoadCompleteListener { soundPool, sampleId, status ->
            if (it.isActive) {
                if (status == LOAD_SOUND_SUCCESS) {
                    it.resumeWith(success(sampleId))
                } else {
                    it.resumeWith(success(null))
                }
            }
            soundPool.setOnLoadCompleteListener(null)
        }
    }

    companion object {
        private const val TAG = "AlertPlayer"
        private const val DEFAULT_MAX_STREAMS = 5
        private const val CURRENT_PLAY_STREAM_NONE = 0
        private const val LOAD_SOUND_SUCCESS = 0
    }
}