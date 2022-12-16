package com.thoughtworks.ark.sample.main.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.thoughtworks.ark.sample.R
import com.thoughtworks.ark.sample.feeds.ui.FeedActivity
import com.thoughtworks.ark.sample.permission.ui.PermissionActivity
import com.thoughtworks.ark.sample.sound.ui.SoundActivity
import com.thoughtworks.ark.sample.storage.ui.StorageActivity
import com.thoughtworks.ark.sample.video.ui.VideoActivity
import com.thoughtworks.ark.ui.annotation.PreviewsDarkLight
import com.thoughtworks.ark.ui.component.AppFilledButton
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
            .padding(all = Dimensions.standardPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { context.startActivity(Intent(context, FeedActivity::class.java)) },
            text = {
                Text(text = "Feed Screen Demo")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { context.startActivity(Intent(context, PermissionActivity::class.java)) },
            text = {
                Text(text = "Permission Demo")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                context.startActivity(Intent(context, SoundActivity::class.java))
            },
            text = { Text(text = "Sound Demo") }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { context.startActivity(Intent(context, VideoActivity::class.java)) },
            text = {
                Text(text = "Video Demo")
            }
        )

        AppFilledButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                context.startActivity(Intent(context, StorageActivity::class.java))
            },
            text = { Text(text = "Storage Demo") }
        )

        Footer()
    }
}

@Composable
private fun ColumnScope.Footer() {
    val logoThoughtworks =
        """https://www.thoughtworks.com/etc.clientlibs/thoughtworks/
            |clientlibs/clientlib-site/resources/images/thoughtworks-logo.svg
        """.trimMargin()
    Row(modifier = Modifier.align(Alignment.End)) {
        Text(
            text = stringResource(id = R.string.powered_by_label),
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(end = Dimensions.smallPadding),
            style = Theme.typography.caption,
            color = Theme.colors.onBackground
        )
        AsyncImage(
            model = logoThoughtworks,
            modifier = Modifier.size(width = 153.dp, height = Dimensions.iconSmall),
            contentDescription = "Thoughtworks"
        )
    }
}

@PreviewsDarkLight
@Composable
fun PreviewMain() {
    Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Theme.colors.background)
                .padding(all = Dimensions.standardPadding)
        ) {
            Footer()
        }
    }
}