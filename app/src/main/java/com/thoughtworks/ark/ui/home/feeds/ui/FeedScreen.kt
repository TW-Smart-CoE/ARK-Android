package com.thoughtworks.ark.ui.home.feeds.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.ark.ui.component.AppButtonDefault
import com.thoughtworks.ark.ui.home.feeds.FeedUiAction
import com.thoughtworks.ark.ui.home.feeds.FeedUiState
import com.thoughtworks.ark.ui.home.feeds.FeedViewModel
import com.thoughtworks.ark.ui.theme.Dimensions
import com.thoughtworks.ark.ui.theme.Theme

@Composable
fun FeedScreen(viewModel: FeedViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    FeedScreenContent(
        uiState = uiState,
        dispatcherAction = viewModel::dispatchAction
    )
}

@Composable
fun FeedScreenContent(
    uiState: FeedUiState,
    dispatcherAction: (FeedUiAction) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Theme.colors.background)
    ) {
        Button(
            onClick = {
                dispatcherAction(FeedUiAction.FeedListAction)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimensions.standardPadding)
                .height(Dimensions.dimension48),
            colors = AppButtonDefault.textButtonColors()
        ) {
            Text(
                text = "GetFeedList",
                fontSize = 15.sp,
                color = Theme.colors.onPrimary
            )
        }

        Text(
            text = uiState.dataText ?: "has null data",
            fontSize = 15.sp,
            color = Theme.colors.onBackground,
            modifier = Modifier.padding(Dimensions.standardPadding)
        )
    }
}
