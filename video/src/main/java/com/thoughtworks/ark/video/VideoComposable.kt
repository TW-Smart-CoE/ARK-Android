package com.thoughtworks.ark.video

import android.content.Context
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.thoughtworks.ark.video.view.CrossFadeVideoView
import com.thoughtworks.ark.video.view.SimpleVideoView
import com.thoughtworks.ark.video.view.VideoPlayerController

@Composable
fun SimpleVideoView(
    modifier: Modifier = Modifier,
    videoItem: VideoItem,
    createVideoOverlay: (Context, VideoPlayerController) -> View? = { _, _ -> null },
    updateVideoOverlay: (VideoItem, View, VideoPlayerController) -> Unit = { _, _, _ -> }
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            SimpleVideoView(context, createVideoOverlay = createVideoOverlay)
        },
        update = { videoView ->
            videoView.play(videoItem)

            videoView.getVideoOverlayView()?.let {
                updateVideoOverlay(videoItem, it, videoView.getVideoPlayController())
            }
        }
    )
}

@Composable
fun CrossFadeVideoView(
    modifier: Modifier = Modifier,
    videoItem: VideoItem,
    createVideoOverlay: (Context, VideoPlayerController) -> View? = { _, _ -> null },
    updateVideoOverlay: (VideoItem, View, VideoPlayerController) -> Unit = { _, _, _ -> }
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            CrossFadeVideoView(context, createVideoOverlay = createVideoOverlay)
        },
        update = { crossFadeVideoView ->
            crossFadeVideoView.play(videoItem)

            crossFadeVideoView.getVideoOverlayView()?.let { videoOverlayView ->
                crossFadeVideoView.getVideoPlayController()?.let { exoPlayer ->
                    updateVideoOverlay(videoItem, videoOverlayView, exoPlayer)
                }
            }
        }
    )
}