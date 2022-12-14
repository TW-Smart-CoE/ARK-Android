package com.thoughtworks.ark.sample.feeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoughtworks.ark.core.network.entity.NetworkReachableException
import com.thoughtworks.ark.core.network.entity.Result
import com.thoughtworks.ark.sample.feeds.data.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FeedUiState(
    val dataText: String? = ""
)

sealed class FeedUiAction {
    object FeedListAction : FeedUiAction()
    object OtherAction : FeedUiAction()
}

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val userRepo: FeedRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FeedUiState())
    val uiState = _uiState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            _uiState.value
        )

    private fun getFeedList() {
        viewModelScope.launch {
            _uiState.update { it.copy(dataText = LOADING) }
            userRepo.getFeedList().collect { res ->
                _uiState.update {
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
            FeedUiAction.FeedListAction -> getFeedList()
            FeedUiAction.OtherAction -> {}
        }
    }

    companion object {
        const val LOADING = "Loading"
        const val ERROR = "Error"
    }
}