package com.thoughtworks.ark.uisample

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thoughtworks.ark.ui.themes.colors.APPExtendedColors
import com.thoughtworks.ark.uisample.colorsystem.ComposeColorSystemActivity
import com.thoughtworks.ark.uisample.colorsystem.XmlColorSystemActivity
import com.thoughtworks.ark.uisample.state.Action
import com.thoughtworks.ark.uisample.state.NavigateActivityAction


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
            // compose color system
            Spacer(modifier = Modifier.height(20.dp))
            Item(sendAction, ComposeColorSystemActivity::class.java, "color system test in compose")
            // xml color system
            Spacer(modifier = Modifier.height(20.dp))
            Item(sendAction, XmlColorSystemActivity::class.java, "color system test in xml")
        }
    }
}

@Composable
private fun Item(
    sendAction: (Action) -> Unit,
    destination: Class<out AppCompatActivity>,
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
            backgroundColor = APPExtendedColors.ButtonBackground.colorValue(),
            contentColor = APPExtendedColors.ButtonContent.colorValue()
        )
    ) {
        Text(title, modifier = Modifier.align(Alignment.CenterVertically))
    }
}