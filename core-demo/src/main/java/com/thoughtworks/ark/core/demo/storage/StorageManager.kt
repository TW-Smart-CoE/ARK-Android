package com.thoughtworks.ark.core.demo.storage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.StatFs
import android.util.Log
import java.io.File
import java.io.IOException

// todo object
object StorageManager {

    const val TAG = "FileManager"
    
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
    
    // todo 通过判断/ 追加多级目录
    fun loadFile(path: String, fileName: String): File? {
        val file = File(path, fileName)
        if (!file.exists()) {
            return null
        }
        return file
    }

    fun loadFileContent(path: String, fileName: String): String? {
        return loadFile(path, fileName)?.readText()
    }

    fun loadResImage(path: String, imageFileName: String): Bitmap? {
        val file = File(path, imageFileName)
        if (!file.exists()) {
            return null
        }
        // Not yet unbearably large question
        return BitmapFactory.decodeFile(file.absolutePath)
    }

    fun checkFileExist(path: String, imageFileName: String): Boolean {
        return File(path, imageFileName).exists()
    }

    fun writeTextToFile(path: String, fileName: String, fileContent: String) {
        try {
            val file = File(path, fileName)
            file.writeText(fileContent)
        } catch (e: IOException) {
            Log.e(TAG, "$WRITE_TEXT_FILE_EXCEPTION ${e.stackTrace}")
        }
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