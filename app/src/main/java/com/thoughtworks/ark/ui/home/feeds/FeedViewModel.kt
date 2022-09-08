package com.thoughtworks.ark.ui.home.feeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoughtworks.ark.core.network.entity.NetworkReachableException
import com.thoughtworks.ark.core.network.entity.Result
import com.thoughtworks.ark.ui.home.feeds.data.repository.UserRepository
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
            homeUiState.update { it.copy(dataText = LOADING) }
            userRepo.getFriendList().collect { res ->
                homeUiState.update {
                    val result = when (res) {
                        is Result.Loading -> LOADING
                        is Result.Success -> res.data?.data?.get(0).toString()
                        is Result.Error -> {
                            when (res.exception) {
                                is NetworkReachableException -> res.exception.message
                                else -> ERROR
                            }
                        }
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

    companion object {
        const val LOADING = "Loading"
        const val ERROR = "Error"
    }
}