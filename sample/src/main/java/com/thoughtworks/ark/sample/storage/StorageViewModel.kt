package com.thoughtworks.ark.sample.storage

import android.graphics.Bitmap
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
)

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

    fun checkFileExist() {
        viewModelScope.launch {
            _storageState.update {
                it.copy(
                    fileIsFlag = storageManager.checkFileExist(defaultFileName)
                )
            }
        }
    }

    fun writeFile() {
        storageManager.writeTextToFile(defaultFileName, defaultWriteContent)
    }

    fun removeFile() {
        storageManager.removeFile(defaultFileName)
    }

    fun loadImage(): Bitmap? {
        return storageManager.loadResImage(defaultImageName)
    }

}