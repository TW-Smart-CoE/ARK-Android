package com.thoughtworks.ark.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.Theme
import com.thoughtworks.ark.ui.theme.color.ExtendedColors

/**
 * Filled button with generic content slot.
 */
@Composable
fun AppFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    colors: ButtonColors = AppButtonDefault.filledButtonColors(),
    contentPadding: PaddingValues = AppButtonDefault.buttonContentPadding(small = small),
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = if (small) {
            modifier.heightIn(min = AppButtonDefault.SmallButtonHeight)
        } else {
            modifier
        },
        enabled = enabled,
        colors = colors,
        contentPadding = contentPadding,
        content = {
            ProvideTextStyle(value = MaterialTheme.typography.button) {
                content()
            }
        }
    )
}

/**
 * Filled button with text and icon content slots.
 */
@Composable
fun AppFilledButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    colors: ButtonColors = AppButtonDefault.filledButtonColors(),
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    AppFilledButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        small = small,
        colors = colors,
        contentPadding = AppButtonDefault.buttonContentPadding(
            small = small,
            leadingIcon = leadingIcon != null,
            trailingIcon = trailingIcon != null
        )
    ) {
        AppButtonContent(
            text = text,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
    }
}

/**
 * Outlined button with generic content slot.
 */
@Composable
fun AppOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    border: BorderStroke? = AppButtonDefault.outlinedButtonBorder(enabled = enabled),
    colors: ButtonColors = AppButtonDefault.outlinedButtonColors(),
    contentPadding: PaddingValues = AppButtonDefault.buttonContentPadding(small = small),
    content: @Composable RowScope.() -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = if (small) {
            modifier.heightIn(min = AppButtonDefault.SmallButtonHeight)
        } else {
            modifier
        },
        enabled = enabled,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = {
            ProvideTextStyle(value = MaterialTheme.typography.button) {
                content()
            }
        }
    )
}

/**
 * Outlined button with text and icon content slots.
 */
@Composable
fun AppOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    border: BorderStroke? = AppButtonDefault.outlinedButtonBorder(enabled = enabled),
    colors: ButtonColors = AppButtonDefault.outlinedButtonColors(),
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    AppOutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        small = small,
        border = border,
        colors = colors,
        contentPadding = AppButtonDefault.buttonContentPadding(
            small = small,
            leadingIcon = leadingIcon != null,
            trailingIcon = trailingIcon != null
        )
    ) {
        AppButtonContent(
            text = text,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
    }
}

/**
 * Text button with generic content slot.
 */
@Composable
fun AppTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    colors: ButtonColors = AppButtonDefault.textButtonColors(),
    contentPadding: PaddingValues = AppButtonDefault.buttonContentPadding(small = small),
    content: @Composable RowScope.() -> Unit,
) {
    TextButton(
        onClick = onClick,
        modifier = if (small) {
            modifier.heightIn(min = AppButtonDefault.SmallButtonHeight)
        } else {
            modifier
        },
        enabled = enabled,
        colors = colors,
        contentPadding = contentPadding,
        content = {
            ProvideTextStyle(value = MaterialTheme.typography.button) {
                content()
            }
        }
    )
}

/**
 * Text button with text and icon content slots.
 */
@Composable
fun AppTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    small: Boolean = false,
    colors: ButtonColors = AppButtonDefault.textButtonColors(),
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    AppTextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        small = small,
        colors = colors,
        contentPadding = AppButtonDefault.buttonContentPadding(
            small = small,
            leadingIcon = leadingIcon != null,
            trailingIcon = trailingIcon != null
        )
    ) {
        AppButtonContent(
            text = text,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon
        )
    }
}

@Composable
private fun RowScope.AppButtonContent(
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)?,
    trailingIcon: @Composable (() -> Unit)?,
) {
    leadingIcon?.run {
        Box(Modifier.sizeIn(maxHeight = AppButtonDefault.ButtonIconSize)) {
            leadingIcon()
        }
    }
    Box(
        Modifier
            .padding(
                start = if (leadingIcon != null) AppButtonDefault.ButtonContentSpacing else 0.dp,
                end = if (trailingIcon != null) AppButtonDefault.ButtonContentSpacing else 0.dp
            )
    ) {
        text()
    }
    trailingIcon?.run {
        Box(Modifier.sizeIn(maxHeight = AppButtonDefault.ButtonIconSize)) {
            trailingIcon()
        }
    }
}

object AppButtonDefault {
    private const val DisabledButtonBackgroundAlpha = 0.12f
    private const val DisabledButtonContentAlpha = 0.38f
    val SmallButtonHeight = Dimensions.dimension32
    private val ButtonHorizontalPadding = Dimensions.dimension24
    private val ButtonHorizontalIconPadding = Dimensions.dimension16
    private val ButtonVerticalPadding = Dimensions.dimension8
    private val SmallButtonVerticalPadding = 7.dp
    private val SmallButtonHorizontalPadding = Dimensions.dimension16
    private val SmallButtonHorizontalIconPadding = Dimensions.dimension12
    val ButtonContentSpacing = Dimensions.dimension8
    val ButtonIconSize = 18.dp

    fun buttonContentPadding(
        small: Boolean,
        leadingIcon: Boolean = false,
        trailingIcon: Boolean = false,
    ): PaddingValues {
        return PaddingValues(
            start = when {
                small && leadingIcon -> SmallButtonHorizontalIconPadding
                small -> SmallButtonHorizontalPadding
                leadingIcon -> ButtonHorizontalIconPadding
                else -> ButtonHorizontalPadding
            },
            top = if (small) SmallButtonVerticalPadding else ButtonVerticalPadding,
            end = when {
                small && trailingIcon -> SmallButtonHorizontalIconPadding
                small -> SmallButtonHorizontalPadding
                trailingIcon -> ButtonHorizontalIconPadding
                else -> ButtonHorizontalPadding
            },
            bottom = if (small) SmallButtonVerticalPadding else ButtonVerticalPadding
        )
    }

    @Composable
    fun filledButtonColors(
        backgroundColor: Color = ExtendedColors.ButtonBackground.color(),
        contentColor: Color = ExtendedColors.ButtonContent.color(),
        disabledBackgroundColor: Color = ExtendedColors.ButtonBackground.color(
            alpha = DisabledButtonBackgroundAlpha
        ),
        disabledContentColor: Color = ExtendedColors.ButtonBackground.color(
            alpha = DisabledButtonContentAlpha
        ),
    ) = ButtonDefaults.buttonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = disabledBackgroundColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun outlinedButtonBorder(
        enabled: Boolean,
        width: Dp = Dimensions.divider,
        color: Color = ExtendedColors.ButtonBackground.color(),
        disabledColor: Color = ExtendedColors.ButtonBackground.color(
            alpha = DisabledButtonBackgroundAlpha
        ),
    ): BorderStroke = BorderStroke(
        width = width,
        color = if (enabled) color else disabledColor
    )

    @Composable
    fun outlinedButtonColors(
        backgroundColor: Color = Color.Transparent,
        contentColor: Color = Theme.colors.onBackground,
        disabledContentColor: Color = Theme.colors.onBackground.copy(
            alpha = DisabledButtonContentAlpha
        ),
    ) = ButtonDefaults.outlinedButtonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor
    )

    @Composable
    fun textButtonColors(
        backgroundColor: Color = Color.Transparent,
        contentColor: Color = Theme.colors.onBackground,
        disabledContentColor: Color = Theme.colors.onBackground
            .copy(alpha = ContentAlpha.disabled),
    ) = ButtonDefaults.textButtonColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
fun PreviewTextButton() {
    Theme {
        TextButton(onClick = { }) {
            Text(text = "Hello")
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
fun PreviewFilledButton() {
    Theme {
        AppFilledButton(onClick = { }) {
            Text(text = "Hello")
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Light theme")
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Dark theme")
@Composable
fun PreviewOutlinedButton() {
    Theme {
        AppOutlinedButton(onClick = { }) {
            Text(text = "Hello")
        }
    }
}