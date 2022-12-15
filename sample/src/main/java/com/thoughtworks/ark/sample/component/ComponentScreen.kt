package com.thoughtworks.ark.sample.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.thoughtworks.ark.sample.R
import com.thoughtworks.ark.ui.component.AppDropDownMenuButton

@Composable
fun ComponentScreen() {
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val items = listOf(
            stringResource(id = R.string.menu_action_share),
            stringResource(id = R.string.menu_action_report),
            stringResource(id = R.string.menu_action_share)
        )
        AppDropDownMenuButton(
            items = items,
            onItemClick = {
                when (it) {
                    SHARE -> {}
                    REPORT -> {}
                    ABOUT -> {}
                }
            },
            title = "Menu"
        )
    }
}





private const val SHARE = "分享"
private const val REPORT = "举报"
private const val ABOUT = "关于"

