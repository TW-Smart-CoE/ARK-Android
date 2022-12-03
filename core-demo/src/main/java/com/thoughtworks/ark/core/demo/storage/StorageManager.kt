package com.thoughtworks.ark.core.demo.storage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.StatFs
import android.util.Log
import java.io.File
import java.io.IOException

class StorageManager private constructor(path: String) {

    private var _path: String? = externalBaseDir

    companion object {
        const val TAG = "FileManager"

        @Volatile
        private var instance: StorageManager? = null

        fun get(path: String): StorageManager {
            if (instance == null) {
                synchronized(StorageManager::class) {
                    if (instance == null) {
                        instance = StorageManager(path)
                    }
                }
            }
            return instance!!
        }
    }

    val isExternalMounted: Boolean
        get() =
            Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()

    val externalBaseDir: String?
        get() = if (isExternalMounted) {
            Environment.getExternalStorageDirectory().absolutePath
        } else null

    val externalSize: Long
        get() {
            if (isExternalMounted) {
                val statFs = StatFs(externalBaseDir)
                val count = statFs.blockCountLong
                val size = statFs.blockSizeLong
                return formatValue(count * size)
            }
            return VALUE_MIN
        }

    val externalAvailableSize: Long
        get() {
            if (isExternalMounted) {
                val statFs = StatFs(externalBaseDir)
                val count = statFs.availableBlocksLong
                val size = statFs.blockSizeLong
                return formatValue(count * size)
            }
            return VALUE_MIN
        }

    fun loadFile(fileName: String): File? {
        val file = File(_path, fileName)
        if (!file.exists()) {
            return null
        }
        return file
    }
    
    fun loadFile(path: String, fileName: String): File? {
        val file = File(path, fileName)
        if (!file.exists()) {
            return null
        }
        return file
    }

    fun loadFileContent(fileName: String): String? {
        return loadFile(fileName)?.readText()
    }

    fun loadFileContent(path: String, fileName: String): String? {
        return loadFile(path, fileName)?.readText()
    }

    fun loadResImage(imageFileName: String): Bitmap? {
        val file = File(_path, imageFileName)
        if (!file.exists()) {
            return null
        }
        // Not yet unbearably large question
        return BitmapFactory.decodeFile(file.absolutePath)
    }

    fun loadResImage(path: String, imageFileName: String): Bitmap? {
        val file = File(path, imageFileName)
        if (!file.exists()) {
            return null
        }
        // Not yet unbearably large question
        return BitmapFactory.decodeFile(file.absolutePath)
    }

    fun checkFileExist(imageFileName: String): Boolean {
        return File(_path, imageFileName).exists()
    }

    fun checkFileExist(path: String, imageFileName: String): Boolean {
        return File(path, imageFileName).exists()
    }

    fun writeTextToFile(fileName: String, fileContent: String) {
        try {
            val file = File(_path, fileName)
            file.writeText(fileContent)
        } catch (e: IOException) {
            Log.e(TAG, "$WRITE_TEXT_FILE_EXCEPTION ${e.stackTrace}")
        }
    }

    fun writeTextToFile(path: String, fileName: String, fileContent: String) {
        try {
            val file = File(path, fileName)
            file.writeText(fileContent)
        } catch (e: IOException) {
            Log.e(TAG, "$WRITE_TEXT_FILE_EXCEPTION ${e.stackTrace}")
        }
    }

    fun removeFile(fileName: String): Boolean {
        val file = File(_path, fileName)
        if (file.exists()) {
            return try {
                file.delete()
                true
            } catch (e: Exception) {
                Log.e(TAG, "$REMOVE_FILE_EXCEPTION ${e.stackTrace}")
                false
            }
        }
        return false
    }

    fun removeFile(path: String, fileName: String): Boolean {
        val file = File(path, fileName)
        if (file.exists()) {
            return try {
                file.delete()
                true
            } catch (e: Exception) {
                Log.e(TAG, "$REMOVE_FILE_EXCEPTION ${e.stackTrace}")
                false
            }
        }
        return false
    }

    private fun formatValue(value: Long): Long {
        return value / MBSize / MBSize
    }

}

private const val MBSize = 1024
private const val WRITE_TEXT_FILE_EXCEPTION = "write text to file exception"
private const val REMOVE_FILE_EXCEPTION = "remove file exception"
private const val VALUE_MIN = 0L