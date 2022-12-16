package com.thoughtworks.ark.sample.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thoughtworks.ark.ui.R
import com.thoughtworks.ark.ui.component.AppDropDownMenuButton
import com.thoughtworks.ark.ui.component.ButtonStyle
import com.thoughtworks.ark.ui.component.NiaNavigationBar
import com.thoughtworks.ark.ui.component.NiaNavigationBarItem

@Composable
fun ComponentScreen() {
    var squareColorForOutlinedButton by remember { mutableStateOf(Color.Red) }
    Column {
        Text("Spinner")
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            val items = listOf(YELLOW, RED, BLUE)
            AppDropDownMenuButton(
                items = items,
                onItemClick = {
                    when (it) {
                        YELLOW -> {
                            squareColorForOutlinedButton = Color.Yellow
                        }
                        RED -> {
                            squareColorForOutlinedButton = Color.Red
                        }
                        BLUE -> {
                            squareColorForOutlinedButton = Color.Blue
                        }
                    }
                },
                title = "Menu"
            )
            Spacer(modifier = Modifier.width(20.dp))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(squareColorForOutlinedButton)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        var squareColorForFilledButton by remember { mutableStateOf(Color.Red) }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            val items = listOf(YELLOW, RED, BLUE)
            AppDropDownMenuButton(
                items = items,
                onItemClick = {
                    when (it) {
                        YELLOW -> {
                            squareColorForFilledButton = Color.Yellow
                        }
                        RED -> {
                            squareColorForFilledButton = Color.Red
                        }
                        BLUE -> {
                            squareColorForFilledButton = Color.Blue
                        }
                    }
                },
                title = "Menu",
                buttonStyle = ButtonStyle.FilledButton
            )
            Spacer(modifier = Modifier.width(20.dp))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(squareColorForFilledButton)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        var squareColorForTextButton by remember { mutableStateOf(Color.Red) }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            val items = listOf(YELLOW, RED, BLUE)
            AppDropDownMenuButton(
                items = items,
                onItemClick = {
                    when (it) {
                        YELLOW -> {
                            squareColorForTextButton = Color.Yellow
                        }
                        RED -> {
                            squareColorForTextButton = Color.Red
                        }
                        BLUE -> {
                            squareColorForTextButton = Color.Blue
                        }
                    }
                },
                title = "Menu",
                buttonStyle = ButtonStyle.TextButton
            )
            Spacer(modifier = Modifier.width(20.dp))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(squareColorForTextButton)
            )
        }
        Text("Navigation")
        Navigation()
    }
}

@Composable
private fun Navigation() {
    NiaNavigationBar {
        val items = listOf("For you", "Episodes", "Saved", "Interests")
        var selectedItem by remember { mutableStateOf(0) }
        val tagIcon = Icons.Rounded.Tag
        val selectedIcons = listOf(
            R.drawable.ic_dashboard,
            R.drawable.ic_home,
            R.drawable.ic_menu
        )
        val icons = listOf(
            R.drawable.ic_dashboard,
            R.drawable.ic_home,
            R.drawable.ic_menu
        )
        items.forEachIndexed { index, item ->
            NiaNavigationBarItem(
                icon = {
                    if (index == NAVIGATION) {
                        Icon(imageVector = tagIcon, contentDescription = null)
                    } else {
                        Icon(
                            painter = painterResource(id = icons[index]),
                            contentDescription = item
                        )
                    }
                },
                selectedIcon = {
                    if (index == NAVIGATION) {
                        Icon(imageVector = tagIcon, contentDescription = null)
                    } else {
                        Icon(
                            painter = painterResource(id = selectedIcons[index]),
                            contentDescription = item
                        )
                    }
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

enum class ColorItems {
    YELLOW, RED, BLUE
}

private const val YELLOW = "yellow"
private const val RED = "red"
private const val BLUE = "blue"
private const val NAVIGATION = 3