package com.thoughtworks.ark.uisample

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.thoughtworks.ark.ui.themes.Dimensions
import com.thoughtworks.ark.ui.themes.colors.ExtendedColors
import com.thoughtworks.ark.ui.themes.icon.AppIcon
import com.thoughtworks.ark.ui.themes.icon.Icons
import com.thoughtworks.ark.uisample.colorsystem.ComposeColorSystemActivity
import com.thoughtworks.ark.uisample.colorsystem.XmlColorSystemActivity
import com.thoughtworks.ark.uisample.state.Action
import com.thoughtworks.ark.uisample.state.NavigateActivityAction


@Composable
fun SampleUIScreen(
    sendAction: (Action) -> Unit,
) {
    val contentPadding = WindowInsets
        .systemBars
        .add(WindowInsets(left = Dimensions.standardPadding,
            top = Dimensions.standardPadding,
            right = Dimensions.standardPadding,
            bottom = Dimensions.standardPadding))
        .asPaddingValues()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(Dimensions.standardPadding)
    ) {
        item {
            ComposeColorSystem(sendAction)
        }
        item {
            XMLColorSystem(sendAction)
        }
        item {
            IconSamples()
        }
    }
}

@Composable
private fun ComposeColorSystem(sendAction: (Action) -> Unit) {
    Item(modifier = Modifier.padding(top = Dimensions.standardSpacing),
        sendAction = sendAction,
        destination = ComposeColorSystemActivity::class.java,
        title = "color system test in compose")
}

@Composable
private fun XMLColorSystem(sendAction: (Action) -> Unit) {
    Item(modifier = Modifier.padding(top = Dimensions.standardSpacing),
        sendAction = sendAction,
        destination = XmlColorSystemActivity::class.java,
        title = "color system test in xml")
}

@Composable
private fun IconSamples() {
    Row(modifier = Modifier.padding(top = Dimensions.standardSpacing)) {
        IconSample(Color.Green, Color.Black)
        Spacer(modifier = Modifier.width(Dimensions.standardSpacing))
        IconSample(Color.Black, Color.White)
    }
}

@Composable
private fun Item(
    modifier: Modifier = Modifier,
    sendAction: (Action) -> Unit,
    destination: Class<out AppCompatActivity>,
    title: String,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimensions.dimension40)
            .padding(horizontal = Dimensions.standardPadding),
        onClick = {
            sendAction.invoke(NavigateActivityAction(destination))
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ExtendedColors.ButtonBackground.color(),
            contentColor = ExtendedColors.ButtonContent.color()
        )
    ) {
        Text(title, modifier = Modifier.align(Alignment.CenterVertically))
    }
}

@Composable
private fun IconSample(tintColor: Color, backgroundColor: Color) {
    Column {
        Row {
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icons.ArrowBack,
                tint = tintColor,
            )
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icons.ArrowForward,
                tint = tintColor,
            )
        }
        Row {
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icons.Home,
                tint = tintColor,
            )
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icons.Menu,
                tint = tintColor,
            )
        }
        Row {
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icons.Close,
                tint = tintColor,
            )
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icons.Cancel,
                tint = tintColor,
            )
        }
        Row {
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icons.Delete,
                tint = tintColor,
            )
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icons.Search,
                tint = tintColor,
            )
        }
        Row {
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icons.More,
                tint = tintColor,
            )
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icons.Favorite,
                tint = tintColor,
            )
        }
    }
}
