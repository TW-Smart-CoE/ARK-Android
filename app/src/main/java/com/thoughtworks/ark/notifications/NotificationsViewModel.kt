package com.thoughtworks.ark.notifications

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class NotificationsUiState(val label: String)

@HiltViewModel
class NotificationsViewModel @Inject constructor() : ViewModel() {
    val state by mutableStateOf(NotificationsUiState(label = "This is notifications Fragment"))
}