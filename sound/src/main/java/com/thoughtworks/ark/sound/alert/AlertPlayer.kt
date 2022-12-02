package com.thoughtworks.ark.sound.alert

import android.content.Context
import android.media.SoundPool
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.ConcurrentHashMap
import kotlin.Result.Companion.success

class AlertPlayer(private val context: Context, maxStreams: Int = DEFAULT_MAX_STREAMS) {
    private val coroutineScope = MainScope()

    private val soundPool = SoundPool.Builder().setMaxStreams(maxStreams).build()
    private val soundMap = ConcurrentHashMap<AlertItem, Int?>()

    private var currentPlayStreamId: Int = CURRENT_PLAY_STREAM_NONE
    private var playSoundJob: Job? = null

    fun prepareSounds(alertItems: List<AlertItem>) {
        coroutineScope.launch(Dispatchers.IO) {
            alertItems.forEach {
                prepareSound(it)
            }
        }
    }

    fun play(alertItem: AlertItem) {
        stop()
        playSoundJob?.cancel()
        playSoundJob = coroutineScope.launch {
            var soundId = soundMap[alertItem]
            if (soundId == null) {
                val loadDeferred = async(Dispatchers.IO) {
                    loadSound(alertItem)
                    awaitLoadComplete()
                }
                soundId = loadDeferred.await()
                soundMap[alertItem] = soundId
            }

            soundId?.let {
                if (isActive) {
                    currentPlayStreamId = soundPool.play(it, 1f, 1f, 0, 0, 1f)
                }
            }
        }
    }

    fun pause() {
        if (currentPlayStreamId != CURRENT_PLAY_STREAM_NONE) {
            soundPool.pause(currentPlayStreamId)
        }
    }

    fun resume() {
        if (currentPlayStreamId != CURRENT_PLAY_STREAM_NONE) {
            soundPool.resume(currentPlayStreamId)
        }
    }

    fun stop() {
        if (currentPlayStreamId != CURRENT_PLAY_STREAM_NONE) {
            soundPool.stop(currentPlayStreamId)
        }
    }

    fun release() {
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
            alertItem.soundRes != 0 -> soundPool.load(context, alertItem.soundRes, 0)
            alertItem.soundAsset.isNotEmpty() -> {
                context.assets.openFd(alertItem.soundAsset).use {
                    soundPool.load(it, 0)
                }
            }
            alertItem.soundFile != null -> soundPool.load(alertItem.soundFile.path, 0)
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
        private const val DEFAULT_MAX_STREAMS = 5
        private const val CURRENT_PLAY_STREAM_NONE = 0
        private const val LOAD_SOUND_SUCCESS = 0
    }
}