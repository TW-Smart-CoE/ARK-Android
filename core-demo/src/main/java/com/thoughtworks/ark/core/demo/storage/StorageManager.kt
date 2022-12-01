package com.thoughtworks.ark.core.demo.storage

import java.io.File

object StorageManager {

    const val TAG = "FileManager"

    fun loadFile(resPath: String, fileName: String): File? {
        val file = File(resPath, fileName)
        if (!file.exists()) {
            return null
        }
        return file
    }


}

