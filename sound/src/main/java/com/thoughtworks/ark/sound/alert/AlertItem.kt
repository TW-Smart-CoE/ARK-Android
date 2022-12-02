package com.thoughtworks.ark.sound.alert

import com.thoughtworks.ark.sound.SoundItem
import java.io.File

data class AlertItem(
    val soundRes: Int = 0,
    val soundAsset: String = "",
    val soundFile: File? = null,
) : SoundItem