package com.thoughtworks.ark.sample.storage

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoughtworks.ark.core.storage.StorageInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StorageState(
    val fileIsFlag: Boolean? = null,
    val imageBitmap: ImageBitmap? = null,
)

sealed class StorageUiAction {
    object CheckAction : StorageUiAction()
    object WriteFileAction : StorageUiAction()
    object RemoveFileAction : StorageUiAction()
    object LoadImageAction : StorageUiAction()
}

@HiltViewModel
class StorageViewModel @Inject constructor(private val fileManager: StorageInterface) : ViewModel() {

    private val _storageState = MutableStateFlow(StorageState())
    val storageState: StateFlow<StorageState>
        get() = _storageState

    private val defaultPath = "/Documents"
    private val defaultFilename = "default.json"
    private val defaultImageName = "default.png"
    private val defaultWriteContent = "demo content"

    init {
        fileManager.path += defaultPath
    }

    private fun checkFileExist() {
        viewModelScope.launch {
            _storageState.update {
                it.copy(
                    fileIsFlag = fileManager.exists(defaultFilename)
                )
            }
        }
    }

    private fun writeFile() {
        fileManager.writeTextToFile(defaultFilename, defaultWriteContent)
    }

    private fun removeFile() {
        fileManager.removeFile(defaultFilename)
    }

    private fun loadImage() {
        viewModelScope.launch {
            _storageState.update {
                it.copy(
                    imageBitmap = fileManager.loadImage(defaultImageName)?.asImageBitmap()
                )
            }
        }
    }

    fun dispatchAction(action: StorageUiAction) {
        when(action) {
            StorageUiAction.CheckAction -> checkFileExist()
            StorageUiAction.WriteFileAction -> writeFile()
            StorageUiAction.RemoveFileAction -> removeFile()
            StorageUiAction.LoadImageAction -> loadImage()
        }
    }
}