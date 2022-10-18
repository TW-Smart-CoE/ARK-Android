package com.thoughtworks.ark.sample.designsystem.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.flowlayout.FlowRow
import com.thoughtworks.ark.sample.designsystem.colorsystem.ui.ComposeColorSystemActivity
import com.thoughtworks.ark.sample.designsystem.colorsystem.ui.XmlColorSystemActivity
import com.thoughtworks.ark.sample.main.ui.state.Action
import com.thoughtworks.ark.sample.main.ui.state.NavigateActivityAction
import com.thoughtworks.ark.ui.component.AppButtonDefault
import com.thoughtworks.ark.ui.component.AppFilledButton
import com.thoughtworks.ark.ui.component.AppOutlinedButton
import com.thoughtworks.ark.ui.component.AppTextButton
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.Theme
import com.thoughtworks.ark.ui.theme.icon.AppIcon
import com.thoughtworks.ark.ui.theme.icon.Icon
import com.thoughtworks.ark.ui.theme.icon.Icons

@Composable
fun DesignSystemScreen(
    sendAction: (Action) -> Unit,
) {
    val contentPadding = WindowInsets
        .systemBars
        .add(
            WindowInsets(
                left = Dimensions.standardPadding,
                top = Dimensions.standardPadding,
                right = Dimensions.standardPadding,
                bottom = Dimensions.standardPadding
            )
        )
        .asPaddingValues()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(Dimensions.standardPadding)
    ) {
        sectionColorSystem(sendAction)
        sectionIcons()
        sectionButtons()
        sectionSmallButtons()
    }
}

private fun LazyListScope.sectionColorSystem(sendAction: (Action) -> Unit) {
    item { SectionLabel("Color System") }
    item {
        Column {
            AppFilledButton(
                onClick = {
                    sendAction.invoke(NavigateActivityAction(destination = ComposeColorSystemActivity::class.java))
                }
            ) {
                Text(text = "Compose color system")
            }
            AppFilledButton(
                onClick = {
                    sendAction.invoke(NavigateActivityAction(destination = XmlColorSystemActivity::class.java))
                }
            ) {
                Text(text = "XML color system")
            }
        }
    }
}

private fun LazyListScope.sectionIcons() {
    item { SectionLabel("Icons samples") }
    item {
        Row {
            Icons(tintColor = Theme.colors.onBackground, backgroundColor = Theme.colors.background)
            Spacer(modifier = Modifier.width(Dimensions.standardSpacing))
            Icons(tintColor = Theme.colors.onPrimary, backgroundColor = Theme.colors.primary)
        }
    }
}

private fun LazyListScope.sectionButtons() {
    item { SectionLabel("Buttons") }
    item {
        FlowRow(mainAxisSpacing = Dimensions.halfPadding) {
            AppFilledButton(onClick = {}) {
                Text(text = "Enabled")
            }
            AppOutlinedButton(onClick = {}) {
                Text(text = "Enabled")
            }
            AppTextButton(onClick = {}) {
                Text(text = "Enabled")
            }
        }
    }
    item { SectionLabel("Disabled buttons") }
    item {
        FlowRow(mainAxisSpacing = Dimensions.halfPadding) {
            AppFilledButton(onClick = {}, enabled = false) {
                Text(text = "Disabled")
            }
            AppOutlinedButton(onClick = {}, enabled = false) {
                Text(text = "Disabled")
            }
            AppTextButton(onClick = {}, enabled = false) {
                Text(text = "Disabled")
            }
        }
    }
    item { SectionLabel("Buttons with leading icons") }
    item {
        FlowRow(mainAxisSpacing = Dimensions.halfPadding) {
            AppFilledButton(
                onClick = {},
                text = { Text(text = "Enabled") },
                leadingIcon = {
                    AppIcon(
                        icon = Icon.DrawableResourceIcon(id = Icons.Favorite),
                        tint = Theme.colors.onPrimary
                    )
                }
            )
            AppOutlinedButton(
                onClick = {},
                text = { Text(text = "Enabled") },
                leadingIcon = {
                    AppIcon(icon = Icon.DrawableResourceIcon(id = Icons.Favorite))
                }
            )
            AppTextButton(
                onClick = {},
                text = { Text(text = "Enabled") },
                leadingIcon = {
                    AppIcon(icon = Icon.DrawableResourceIcon(id = Icons.Favorite))
                }
            )
        }
    }
    item { SectionLabel("Disabled buttons with trailing icons") }
    item {
        FlowRow(mainAxisSpacing = Dimensions.halfPadding) {
            AppFilledButton(
                onClick = {},
                enabled = false,
                text = { Text(text = "Disabled") },
                trailingIcon = {
                    AppIcon(
                        icon = Icon.DrawableResourceIcon(id = Icons.Favorite),
                        tint = Theme.colors.onPrimary.copy(alpha = AppButtonDefault.DisabledButtonContentAlpha)
                    )
                }
            )
            AppOutlinedButton(
                onClick = {},
                enabled = false,
                text = { Text(text = "Disabled") },
                trailingIcon = {
                    AppIcon(
                        icon = Icon.DrawableResourceIcon(id = Icons.Favorite),
                        tint = Theme.colors.primary.copy(alpha = AppButtonDefault.DisabledButtonContentAlpha)
                    )
                }
            )
            AppTextButton(
                onClick = {},
                enabled = false,
                text = { Text(text = "Disabled") },
                trailingIcon = {
                    AppIcon(
                        icon = Icon.DrawableResourceIcon(id = Icons.Favorite),
                        tint = Theme.colors.primary.copy(alpha = AppButtonDefault.DisabledButtonContentAlpha)
                    )
                }
            )
        }
    }
}

private fun LazyListScope.sectionSmallButtons() {
    item { SectionLabel("Small buttons") }
    item {
        FlowRow(mainAxisSpacing = Dimensions.halfPadding) {
            AppFilledButton(onClick = {}, small = true) {
                Text(text = "Enabled")
            }
            AppOutlinedButton(onClick = {}, small = true) {
                Text(text = "Enabled")
            }
            AppTextButton(onClick = {}, small = true) {
                Text(text = "Enabled")
            }
        }
    }
    item { SectionLabel("Disabled small buttons") }
    item {
        FlowRow(mainAxisSpacing = Dimensions.halfPadding) {
            AppFilledButton(onClick = {}, enabled = false, small = true) {
                Text(text = "Disabled")
            }
            AppOutlinedButton(onClick = {}, enabled = false, small = true) {
                Text(text = "Disabled")
            }
            AppTextButton(onClick = {}, enabled = false, small = true) {
                Text(text = "Disabled")
            }
        }
    }
    item { SectionLabel("Small buttons with leading icons") }
    item {
        FlowRow(mainAxisSpacing = Dimensions.halfPadding) {
            AppFilledButton(
                onClick = {},
                text = { Text(text = "Enabled") },
                small = true,
                leadingIcon = {
                    AppIcon(
                        icon = Icon.DrawableResourceIcon(id = Icons.Favorite),
                        tint = Theme.colors.onPrimary
                    )
                }
            )
            AppOutlinedButton(
                onClick = {},
                text = { Text(text = "Enabled") },
                small = true,
                leadingIcon = {
                    AppIcon(icon = Icon.DrawableResourceIcon(id = Icons.Favorite))
                }
            )
            AppTextButton(
                onClick = {},
                text = { Text(text = "Enabled") },
                small = true,
                leadingIcon = {
                    AppIcon(icon = Icon.DrawableResourceIcon(id = Icons.Favorite))
                }
            )
        }
    }
    item { SectionLabel("Disabled small buttons with trailing icons") }
    item {
        FlowRow(mainAxisSpacing = Dimensions.halfPadding) {
            AppFilledButton(
                onClick = {},
                enabled = false,
                small = true,
                text = { Text(text = "Disabled") },
                trailingIcon = {
                    AppIcon(
                        icon = Icon.DrawableResourceIcon(id = Icons.Favorite),
                        tint = Theme.colors.onPrimary.copy(alpha = AppButtonDefault.DisabledButtonContentAlpha)
                    )
                }
            )
            AppOutlinedButton(
                onClick = {},
                enabled = false,
                small = true,
                text = { Text(text = "Disabled") },
                trailingIcon = {
                    AppIcon(
                        icon = Icon.DrawableResourceIcon(id = Icons.Favorite),
                        tint = Theme.colors.primary.copy(alpha = AppButtonDefault.DisabledButtonContentAlpha)
                    )
                }
            )
            AppTextButton(
                onClick = {},
                enabled = false,
                small = true,
                text = { Text(text = "Disabled") },
                trailingIcon = {
                    AppIcon(
                        icon = Icon.DrawableResourceIcon(id = Icons.Favorite),
                        tint = Theme.colors.primary.copy(alpha = AppButtonDefault.DisabledButtonContentAlpha)
                    )
                }
            )
        }
    }
}

@Composable
private fun SectionLabel(text: String) = Text(
    text = text,
    modifier = Modifier.padding(top = Dimensions.standardPadding),
    color = Theme.colors.onBackground,
    style = Theme.typography.h6
)

@Composable
private fun Icons(tintColor: Color, backgroundColor: Color) {
    Column {
        Row {
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icon.DrawableResourceIcon(id = Icons.ArrowBack),
                tint = tintColor,
            )
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icon.DrawableResourceIcon(id = Icons.ArrowForward),
                tint = tintColor,
            )
        }
        Row {
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icon.DrawableResourceIcon(id = Icons.Home),
                tint = tintColor,
            )
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icon.DrawableResourceIcon(id = Icons.Menu),
                tint = tintColor,
            )
        }
        Row {
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icon.DrawableResourceIcon(id = Icons.Close),
                tint = tintColor,
            )
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icon.DrawableResourceIcon(id = Icons.Cancel),
                tint = tintColor,
            )
        }
        Row {
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icon.DrawableResourceIcon(id = Icons.Delete),
                tint = tintColor,
            )
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icon.DrawableResourceIcon(id = Icons.Search),
                tint = tintColor,
            )
        }
        Row {
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icon.ImageVectorIcon(imageVector = Icons.Warning),
                tint = tintColor,
            )
            AppIcon(
                modifier = Modifier.background(backgroundColor),
                icon = Icon.ImageVectorIcon(imageVector = Icons.Info),
                tint = tintColor,
            )
        }
    }
}
