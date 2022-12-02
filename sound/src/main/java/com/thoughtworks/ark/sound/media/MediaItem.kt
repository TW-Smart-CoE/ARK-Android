package com.thoughtworks.ark.sound.media

import com.thoughtworks.ark.sound.SoundItem
import java.io.File

data class MediaItem(
    val soundRes: Int = 0,
    val soundAsset: String = "",
    val soundFile: File? = null
) : SoundItem