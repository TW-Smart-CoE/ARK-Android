package com.thoughtworks.ark.video.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.view.animation.AccelerateInterpolator
import android.view.animation.Interpolator
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.receiveAsFlow

private const val DefaultDuration = 500L
private const val FadeOutDelay = DefaultDuration / 3 * 2

fun crossFadeAnimation(fadeInFrom: Float = 0f, fadeOutFrom: Float = 1f): Flow<Pair<Float, Float>> {
    val fadeInChannel = Channel<Float>(capacity = 1000, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val fadeOutChannel = Channel<Float>(capacity = 1000, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    fadeOutChannel.trySend(fadeOutFrom)

    val animatorSet = AnimatorSet()
    val fadeIn = fade(
        from = fadeInFrom,
        to = 1f,
        onUpdate = {
            fadeInChannel.trySend(it)
        }
    ) {
        fadeInChannel.close()
    }
    val fadeOut = fade(
        from = fadeOutFrom,
        to = 0f,
        delay = FadeOutDelay,
        onUpdate = {
            fadeOutChannel.trySend(it)
        }
    ) {
        fadeOutChannel.close()
    }
    animatorSet.playTogether(fadeIn, fadeOut)
    animatorSet.start()

    return fadeInChannel.receiveAsFlow().combine(fadeOutChannel.receiveAsFlow()) { l, r ->
        Pair(l, r)
    }.onCompletion {
        animatorSet.cancel()
        fadeInChannel.close()
        fadeOutChannel.close()
    }
}

private fun fade(
    from: Float,
    to: Float,
    delay: Long = 0,
    duration: Long = DefaultDuration,
    interpolator: Interpolator = AccelerateInterpolator(),
    onUpdate: (Float) -> Unit,
    onEnd: () -> Unit = {}
): ValueAnimator {
    val valueAnimator = ValueAnimator.ofFloat(from, to)
    valueAnimator.duration = duration
    valueAnimator.startDelay = delay
    valueAnimator.interpolator = interpolator
    valueAnimator.addUpdateListener {
        onUpdate(it.animatedValue as Float)
    }
    valueAnimator.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            onEnd()
        }
    })
    return valueAnimator
}