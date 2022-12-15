package com.thoughtworks.ark.sample.storage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoughtworks.ark.core.storage.StorageInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class StorageUseState {
    object Loading : StorageUseState()
    object Success : StorageUseState()
    object Error : StorageUseState()
}

data class StorageState(
    val fileIsFlag: Boolean? = null,
    val storageUseState: StorageUseState = StorageUseState.Loading
)

sealed class StorageUiAction {
    object CheckAction : StorageUiAction()
    object WriteFileAction : StorageUiAction()
    object RemoveFileAction : StorageUiAction()
}

@HiltViewModel
class StorageViewModel @Inject constructor(private val fileManager: StorageInterface) :
    ViewModel() {

    private val _storageState = MutableStateFlow(StorageState())
    val storageState: StateFlow<StorageState>
        get() = _storageState

    private val defaultPath = "/Documents"
    private val defaultFilename = "default.json"
    private val defaultWriteContent = "demo content"

    init {
        fileManager.path?.let { fileManager.path += defaultPath }
    }

    private fun checkFileExist() {
        viewModelScope.launch {
            _storageState.update {
                val result = if (fileManager.path.isNullOrBlank()) {
                    StorageUseState.Error
                } else {
                    StorageUseState.Success
                }
                it.copy(
                    fileIsFlag = fileManager.exists(defaultFilename),
                    storageUseState = result
                )
            }
        }
    }

    private fun writeFile() {
        viewModelScope.launch {
            _storageState.update {
                val result = if (fileManager.path.isNullOrBlank()) {
                    StorageUseState.Error
                } else {
                    fileManager.writeTextToFile(defaultFilename, defaultWriteContent)
                    StorageUseState.Success
                }
                it.copy(
                    storageUseState = result
                )
            }
        }
    }

    private fun removeFile() {
        viewModelScope.launch {
            _storageState.update {
                val result = if (fileManager.path.isNullOrBlank()) {
                    StorageUseState.Error
                } else {
                    fileManager.removeFile(defaultFilename)
                    StorageUseState.Success
                }
                it.copy(
                    storageUseState = result
                )
            }
        }
    }

    fun clearStorageUiResult() {
        _storageState.update {
            it.copy(storageUseState = StorageUseState.Loading)
        }
    }

    fun dispatchAction(action: StorageUiAction) {
        when (action) {
            is StorageUiAction.CheckAction -> checkFileExist()
            is StorageUiAction.WriteFileAction -> writeFile()
            is StorageUiAction.RemoveFileAction -> removeFile()
        }
    }
}