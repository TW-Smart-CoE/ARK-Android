package com.thoughtworks.ark.media.video.view

class RecyclerVideoViewPool {
    private val videoViews = mutableListOf<RecyclerVideoView>()

    fun add(recyclerVideoView: RecyclerVideoView) {
        videoViews.add(recyclerVideoView)
    }

    fun peek(): RecyclerVideoView? {
        val first = videoViews.firstOrNull { it.parent == null }
        if (first != null) {
            videoViews.remove(first)
        }
        return first
    }

    fun clear() {
        videoViews.clear()
    }
}
