package com.thoughtworks.ark.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
    items: List<T>,
    onItemClick: (item: T) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    dismissOnItemClick: Boolean = true,
    title: String
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        AppOutlinedButton(
            enabled = enabled,
            onClick = { expanded = true },
            text = { Text(text = title) },
            trailingIcon = {
                AppIcon(
                    icon =
                    if (expanded)
                        Icon.DrawableResourceIcon(id = Icons.ArrowDropUp)
                    else Icon.DrawableResourceIcon(id = Icons.ArrowDropDown)
                )
            }
        )
        AppDropDownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            items = items,
            onItemClick = onItemClick,
            dismissOnItemClick = dismissOnItemClick
        )
    }
}