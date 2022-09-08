package com.thoughtworks.ark.ui.home.feeds.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.thoughtworks.ark.ui.home.feeds.FeedUiAction
import com.thoughtworks.ark.ui.home.feeds.FeedUiState
import com.thoughtworks.ark.ui.home.feeds.FeedViewModel

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
    dispatcherAction: (FeedUiAction) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                dispatcherAction(FeedUiAction.FriendListAction)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(start = 4.dp, end = 4.dp)
                .background(Color.Blue)
        ) {
            Text(
                text = "GetFriendList",
                fontSize = 15.sp,
                color = Color.White
            )
        }

        Text(
            text = uiState.dataText ?: "has null data",
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier.padding(4.dp)
        )
    }
}
