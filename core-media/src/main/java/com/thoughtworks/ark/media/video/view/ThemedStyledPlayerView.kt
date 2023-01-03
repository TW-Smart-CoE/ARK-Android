package com.thoughtworks.ark.media.video.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ProgressBar
import com.google.android.exoplayer2.ui.StyledPlayerView

class ThemedStyledPlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : StyledPlayerView(context, attrs) {
    init {
        val progressBar = findViewById<ProgressBar>(com.google.android.exoplayer2.R.id.exo_buffering)
        progressBar.indeterminateTintList = ColorStateList.valueOf(Color.WHITE)
    }
}