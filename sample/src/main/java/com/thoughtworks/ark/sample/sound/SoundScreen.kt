package com.thoughtworks.ark.sample.sound

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.thoughtworks.ark.sample.R
import com.thoughtworks.ark.sound.SoundPlayerImpl
import com.thoughtworks.ark.sound.alert.AlertItem
import com.thoughtworks.ark.sound.media.MediaItem
import com.thoughtworks.ark.sound.tts.TTSItem
import com.thoughtworks.ark.ui.component.AppFilledButton
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.Theme

@Composable
fun SoundScreen() {
    val ctx = LocalContext.current
    val soundPlayer = remember { SoundPlayerImpl(ctx) }

    DisposableEffect(ctx) {
        onDispose {
            soundPlayer.release()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(all = Dimensions.standardPadding)
    ) {
        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { soundPlayer.play(AlertItem(soundAsset = "wind_bell.mp3")) },
            text = {
                Text(text = "Play alert sound 1")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { soundPlayer.play(AlertItem(R.raw.guitar)) },
            text = {
                Text(text = "Play alert sound 2")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { soundPlayer.play(MediaItem(soundAsset = "music.mp3")) },
            text = {
                Text(text = "Play music")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { soundPlayer.play(TTSItem("hello world!")) },
            text = {
                Text(text = "TTS speak text")
            }
        )
    }
}