package com.thoughtworks.ark.dashboard

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class DashboardUiState(val label: String)

@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(DashboardUiState(label = "This is dashboard Fragment"))
    val uiState = _uiState.asStateFlow()
}