package com.thoughtworks.ark.video.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.core.view.forEach
import com.google.android.exoplayer2.ExoPlayer
import com.thoughtworks.ark.video.VideoItem
import com.thoughtworks.ark.video.utils.crossFadeAnimation
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

class CrossFadeVideoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    private val createVideoOverlay: (Context, ExoPlayer) -> View? = { _, _ -> null }
) : FrameLayout(context, attrs) {
    private val mainScope = MainScope()

    private val recyclerVideoViewPool = RecyclerVideoViewPool()
    private var currentVideoView: RecyclerVideoView? = null

    private var animationJob: Job? = null

    fun play(videoItem: VideoItem?) {
        if (videoItem == null) {
            clearAllVideoView()
        } else {
            addNewVideoView(videoItem)
        }
    }

    fun getVideoOverlayView(): View? {
        return currentVideoView?.getVideoOverlayView()
    }

    fun getVideoPlayer(): ExoPlayer? {
        return currentVideoView?.getVideoPlayer()
    }

    fun setResizeMode(resizeMode: Int) {
        currentVideoView?.setResizeMode(resizeMode)
    }

    fun enableDefaultControl(enable: Boolean) {
        currentVideoView?.enableDefaultControl(enable)
    }

    private fun clearAllVideoView() {
        val oldVideoViewList = getOldVideoViewList()
        startCrossFadeAnimation(
            fadeOutFrom = oldVideoViewList.lastOrNull()?.alpha ?: 1f,
            onUpdate = { pair ->
                oldVideoViewList.forEach {
                    it.updateAlpha(pair.second)
                }
            },
            onComplete = {
                oldVideoViewList.forEach {
                    it.release()
                    recyclerVideoViewPool.add(it)
                }
            }
        )

        currentVideoView = null
    }

    private fun getOldVideoViewList(): List<RecyclerVideoView> {
        val oldVideoViewList = mutableListOf<RecyclerVideoView>()
        forEach {
            if (it is RecyclerVideoView) {
                it.volumeOff()
                oldVideoViewList.add(it)
            }
        }
        return oldVideoViewList
    }

    private fun addNewVideoView(videoItem: VideoItem) {
        val oldVideoViewList = getOldVideoViewList()

        val newVideoView = createNewVideoView()
        addView(newVideoView, 0, LayoutParams(MATCH_PARENT, MATCH_PARENT))

        newVideoView.play(
            videoItem,
            onPlayStart = {
                startCrossFadeAnimation(
                    fadeOutFrom = oldVideoViewList.lastOrNull()?.alpha ?: 1f,
                    onUpdate = { pair ->
                        newVideoView.updateAlpha(pair.first)
                        oldVideoViewList.forEach {
                            it.updateAlpha(pair.second)
                        }
                    },
                    onComplete = {
                        oldVideoViewList.forEach {
                            it.release()
                            recyclerVideoViewPool.add(it)
                        }
                    }
                )
            },
            onPlayEnd = {}
        )

        currentVideoView = newVideoView
    }

    private fun createNewVideoView(): RecyclerVideoView {
        var recyclerVideoView = recyclerVideoViewPool.peek()
        if (recyclerVideoView == null || recyclerVideoView.parent != null) {
            recyclerVideoView = RecyclerVideoView(context)
        }
        recyclerVideoView.setUp(createVideoOverlay)
        recyclerVideoView.updateAlpha(0f)
        return recyclerVideoView
    }

    private fun startCrossFadeAnimation(
        fadeInFrom: Float = 0f,
        fadeOutFrom: Float = 1f,
        onUpdate: (Pair<Float, Float>) -> Unit = {},
        onComplete: () -> Unit = {}
    ) {
        animationJob?.cancel()
        animationJob = crossFadeAnimation(fadeInFrom, fadeOutFrom)
            .onEach { onUpdate(it) }
            .onCompletion { onComplete() }
            .launchIn(mainScope)
    }

    fun resume() {
        forEach {
            if (it is RecyclerVideoView) {
                it.resume()
            }
        }
    }

    fun pause() {
        forEach {
            if (it is RecyclerVideoView) {
                it.pause()
            }
        }
    }

    fun stop() {
        forEach {
            if (it is RecyclerVideoView) {
                it.stop()
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        recyclerVideoViewPool.clear()
    }
}