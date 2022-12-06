package com.thoughtworks.ark.sample.storage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StorageState(
    val fileIsFlag: Boolean = false,
)

@HiltViewModel
class StorageViewModel @Inject constructor() : ViewModel() {

    private val _storageState = MutableStateFlow(StorageState())
    val storageState: StateFlow<StorageState>
        get() = _storageState

    fun updateStorageState() {
        viewModelScope.launch {
            _storageState.update {
                it.copy(
                    fileIsFlag = true
                )
            }
        }
    }

}