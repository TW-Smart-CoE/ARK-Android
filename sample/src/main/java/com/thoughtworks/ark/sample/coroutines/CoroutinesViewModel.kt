package com.thoughtworks.ark.sample.coroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoughtworks.ark.sample.coroutines.data.repository.CoroutinesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CoroutinesViewModelUiState(
    val label: String = "",
)

@HiltViewModel
class CoroutinesViewModel @Inject constructor(
    private val repository: CoroutinesRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CoroutinesViewModelUiState())
    val uiState = _uiState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        _uiState.value
    )

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            repository.loadData().collect { data ->
                _uiState.update {
                    it.copy(label = data)
                }
            }
        }
    }
}