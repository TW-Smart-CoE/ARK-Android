package com.thoughtworks.android.ark.uisample

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thoughtworks.android.ark.uisample.colorsystem.ColorSystemActivity
import com.thoughtworks.android.ark.uisample.state.Action
import com.thoughtworks.android.ark.uisample.state.NavigateActivityAction
import com.thoughtworks.android.ark.ui.themes.colors.ComposeColors


@Composable
fun SampleUIScreen(
    sendAction: (Action) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            // color system
            Item(sendAction, ColorSystemActivity::class.java, "color system")
        }
    }
}

@Composable
private fun Item(
    sendAction: (Action) -> Unit,
    destination: Class<ColorSystemActivity>,
    title: String
) {
    Button(
        modifier = Modifier
            .width(300.dp)
            .height(40.dp),
        onClick = {
            sendAction.invoke(NavigateActivityAction(destination))
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ComposeColors.Dynamic.ButtonBackground.colorValue(),
            contentColor = ComposeColors.Dynamic.ButtonContent.colorValue()
        )
    ) {
        Text(title, modifier = Modifier.align(Alignment.CenterVertically))
    }
}
