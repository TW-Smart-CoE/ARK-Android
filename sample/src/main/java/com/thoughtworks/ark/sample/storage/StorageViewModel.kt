package com.thoughtworks.ark.sample.storage

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoughtworks.ark.core.demo.storage.StorageManager
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
class StorageViewModel @Inject constructor() : ViewModel() {

    private val _storageState = MutableStateFlow(StorageState())
    val storageState: StateFlow<StorageState>
        get() = _storageState

    private val defaultPath = "/storage/emulated/0/Documents/"
    private val defaultFileName = "default.json"
    private val defaultImageName = "default.png"
    private val defaultWriteContent = "demo content"
    private val storageManager = StorageManager.get(defaultPath)

    private fun checkFileExist() {
        viewModelScope.launch {
            _storageState.update {
                it.copy(
                    fileIsFlag = storageManager.checkFileExist(defaultFileName)
                )
            }
        }
    }

    private fun writeFile() {
        storageManager.writeTextToFile(defaultFileName, defaultWriteContent)
    }

    private fun removeFile() {
        storageManager.removeFile(defaultFileName)
    }

    private fun loadImage() {
        viewModelScope.launch {
            _storageState.update {
                it.copy(
                    imageBitmap = storageManager.loadResImage(defaultImageName)?.asImageBitmap()
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