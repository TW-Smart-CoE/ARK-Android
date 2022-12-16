package com.thoughtworks.ark.sample.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.thoughtworks.ark.ui.component.AppDropDownMenuButton
import com.thoughtworks.ark.ui.component.ButtonStyle

@Composable
fun ComponentScreen() {
    var squareColorForOutlinedButton by remember { mutableStateOf(Color.Red) }
    Column {
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
    }

}

enum class ColorItems {
    YELLOW, RED, BLUE
}


private const val YELLOW = "yellow"
private const val RED = "red"
private const val BLUE = "blue"

