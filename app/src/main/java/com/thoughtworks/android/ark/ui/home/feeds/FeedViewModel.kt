package com.thoughtworks.android.ark.ui.home.feeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoughtworks.android.ark.ui.home.feeds.data.UserRepository
import com.thoughtworks.android.core.network.entity.RetrofitResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FeedUiState(
    val dataText: String? = "",
    val testDataText: String? = ""
)

sealed class FeedUiAction {
    object FriendListAction : FeedUiAction()
    object OtherAction : FeedUiAction()
}

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val userRepo: UserRepository
) : ViewModel() {

    private val homeUiState = MutableStateFlow(FeedUiState())
    val uiState = homeUiState
        .map { it }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            homeUiState.value
        )

    private fun getFriendList() {
        viewModelScope.launch {
            homeUiState.update { it.copy(dataText = "Loading") }
            userRepo.getFriendList().collect { res ->
                homeUiState.update {
                    val result = when (res) {
                        is RetrofitResponse.Loading -> "Loading"
                        is RetrofitResponse.Success -> res.data.data?.get(0).toString()
                        is RetrofitResponse.Error -> "error code ${res.code}"
                        else -> "unknown"
                    }
                    it.copy(dataText = result)
                }
            }
        }
    }

    fun dispatchAction(action: FeedUiAction) {
        when (action) {
            FeedUiAction.FriendListAction -> getFriendList()
            FeedUiAction.OtherAction -> {}
        }
    }
}