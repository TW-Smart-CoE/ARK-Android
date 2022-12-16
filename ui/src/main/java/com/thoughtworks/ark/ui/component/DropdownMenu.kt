package com.thoughtworks.ark.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.thoughtworks.ark.ui.theme.icon.AppIcon
import com.thoughtworks.ark.ui.theme.icon.Icon
import com.thoughtworks.ark.ui.theme.icon.Icons

@Composable
fun <T> AppDropDownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    items: List<T>,
    onItemClick: (item: T) -> Unit,
    dismissOnItemClick: Boolean = true,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        items.forEach { item ->
            DropdownMenuItem(onClick = {
                onItemClick(item)
                if (dismissOnItemClick) onDismissRequest()
            }) {
                Text(text = item.toString())
            }
        }
    }
}

@Composable
fun <T> AppDropDownMenuButton(
    modifier: Modifier = Modifier,
    items: List<T>,
    onItemClick: (item: T) -> Unit,
    enabled: Boolean = true,
    dismissOnItemClick: Boolean = true,
    title: String,
    buttonStyle: ButtonStyle = ButtonStyle.OutlinedButton,
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .then(modifier)
    ) {
        when (buttonStyle) {
            ButtonStyle.TextButton -> {
                AppTextButton(
                    enabled = enabled,
                    onClick = { expanded = true },
                    text = { Text(text = title) },
                    trailingIcon = {
                        AppIcon(
                            icon =
                            if (expanded) {
                                Icon.DrawableResourceIcon(id = Icons.ArrowDropUp)
                            } else {
                                Icon.DrawableResourceIcon(id = Icons.ArrowDropDown)
                            }
                        )
                    }
                )
            }

            ButtonStyle.FilledButton -> {
                AppFilledButton(
                    enabled = enabled,
                    onClick = { expanded = true },
                    text = { Text(text = title) },
                    trailingIcon = {
                        AppIcon(
                            icon =
                            if (expanded) {
                                Icon.DrawableResourceIcon(id = Icons.ArrowDropUp)
                            } else {
                                Icon.DrawableResourceIcon(id = Icons.ArrowDropDown)
                            }
                        )
                    }
                )
            }

            else -> {
                AppOutlinedButton(
                    enabled = enabled,
                    onClick = { expanded = true },
                    text = { Text(text = title) },
                    trailingIcon = {
                        AppIcon(
                            icon =
                            if (expanded) {
                                Icon.DrawableResourceIcon(id = Icons.ArrowDropUp)
                            } else Icon.DrawableResourceIcon(id = Icons.ArrowDropDown)
                        )
                    }
                )
            }
        }

        AppDropDownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            items = items,
            onItemClick = onItemClick,
            dismissOnItemClick = dismissOnItemClick
        )
    }
}

enum class ButtonStyle {
    FilledButton, OutlinedButton, TextButton
}