package com.thoughtworks.ark.sample.feeds.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.ark.sample.feeds.FeedUiAction
import com.thoughtworks.ark.sample.feeds.FeedUiState
import com.thoughtworks.ark.sample.feeds.FeedViewModel
import com.thoughtworks.ark.ui.component.AppFilledButton
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.Theme

@Composable
fun FeedScreen(viewModel: FeedViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    FeedScreenContent(
        uiState = uiState,
        dispatchAction = viewModel::dispatchAction
    )
}

@Composable
fun FeedScreenContent(
    uiState: FeedUiState,
    dispatchAction: (FeedUiAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(color = Theme.colors.background)
            .padding(horizontal = Dimensions.standardPadding)
    ) {
        AppFilledButton(
            onClick = { dispatchAction(FeedUiAction.FeedListAction) },
            text = { Text(text = "GetFeedList") }
        )
        Text(
            text = uiState.dataText ?: "has null data",
            style = Theme.typography.body02,
            color = Theme.colors.onBackground,
            modifier = Modifier.padding(Dimensions.standardPadding)
        )
    }
}
