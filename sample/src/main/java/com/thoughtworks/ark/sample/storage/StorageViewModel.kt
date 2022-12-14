package com.thoughtworks.ark.sample.storage

import android.content.Context
import android.widget.Toast
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
    data class WriteFileAction(val context: Context) : StorageUiAction()
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
        if (fileManager.path != null) {
            fileManager.path += defaultPath
        }
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

    private fun writeFile(context: Context) {
        if (fileManager.path == null) {
            showError(context)
        } else {
            fileManager.writeTextToFile(defaultFilename, defaultWriteContent)
        }
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

    private fun showError(context: Context) {
        Toast.makeText(
            context,
            ERROR,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun dispatchAction(action: StorageUiAction) {
        when (action) {
            is StorageUiAction.CheckAction -> checkFileExist()
            is StorageUiAction.WriteFileAction -> writeFile(action.context)
            is StorageUiAction.RemoveFileAction -> removeFile()
            is StorageUiAction.LoadImageAction -> loadImage()
        }
    }

    companion object {
        private const val ERROR = "Sorry, please reduced version to 29 and below"
    }

}