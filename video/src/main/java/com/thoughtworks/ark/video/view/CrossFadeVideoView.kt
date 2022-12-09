@file:Suppress("TooManyFunctions")

package com.thoughtworks.ark.video.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.core.view.forEach
import com.thoughtworks.ark.video.VideoItem
import com.thoughtworks.ark.video.utils.crossFadeAnimation
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

class CrossFadeVideoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    private val createVideoOverlay: (Context, VideoPlayerController) -> View? = { _, _ -> null }
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

    fun getCurrentVideoView(): RecyclerVideoView? {
        return currentVideoView
    }

    fun getVideoOverlayView(): View? {
        return currentVideoView?.getVideoOverlayView()
    }

    fun getVideoPlayController(): VideoPlayerController? {
        return currentVideoView?.getVideoPlayController()
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
                    it.clear()
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
                it.getVideoPlayController().volumeOff()
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
                            it.clear()
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
                it.getVideoPlayController().resume()
            }
        }
    }

    fun pause() {
        forEach {
            if (it is RecyclerVideoView) {
                it.getVideoPlayController().pause()
            }
        }
    }

    fun stop() {
        forEach {
            if (it is RecyclerVideoView) {
                it.getVideoPlayController().stop()
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clear()
        recyclerVideoViewPool.clear()
        mainScope.cancel()
    }

    private fun clear() {
        forEach {
            if (it is RecyclerVideoView) {
                it.clear()
            }
        }
    }
}