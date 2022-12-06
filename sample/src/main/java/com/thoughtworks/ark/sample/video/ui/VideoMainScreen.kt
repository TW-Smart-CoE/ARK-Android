package com.thoughtworks.ark.sample.video.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.thoughtworks.ark.sample.video.VideoViewModel
import com.thoughtworks.ark.ui.component.AppFilledButton
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.Theme

@Composable
fun VideoMainScreen(viewModel: VideoViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(all = Dimensions.standardPadding)
    ) {
        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.openSimpleVideoPlayScreen() },
            text = {
                Text(text = "Play Video")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { viewModel.openCustomControlPlayScreen() },
            text = {
                Text(text = "Custom Video Control")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.openCrossFadeVideoPlayScreen()
            },
            text = {
                Text(text = "CrossFade Video Test")
            }
        )
    }
}