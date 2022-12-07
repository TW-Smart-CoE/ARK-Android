package com.thoughtworks.ark.core.storage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.StatFs
import android.util.Log
import java.io.File
import java.io.IOException

class FileManager private constructor(private var path: String) : StorageInterface {

    companion object {
        const val TAG = "FileManager"
        private const val MBSize = 1024
        private const val WRITE_TEXT_FILE_EXCEPTION = "write text to file exception"
        private const val REMOVE_FILE_EXCEPTION = "remove file exception"
        private const val VALUE_MIN = 0L
    }

    // It is only works less than target sdk 29
    override val isExternalMounted: Boolean
        get() =
            Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()

    // It is only works less than target sdk 29
    override val externalBaseDir: String?
        get() = if (isExternalMounted) {
            Environment.getExternalStorageDirectory().absolutePath
        } else null

    /**
     * value's unit is MB
     */
    override val externalSize: Long
        get() {
            if (isExternalMounted) {
                val statFs = StatFs(externalBaseDir)
                val count = statFs.blockCountLong
                val size = statFs.blockSizeLong
                return formatValue(count * size)
            }
            return VALUE_MIN
        }

    /**
     * value's unit is MB
     */
    override val externalAvailableSize: Long
        get() {
            if (isExternalMounted) {
                val statFs = StatFs(externalBaseDir)
                val count = statFs.availableBlocksLong
                val size = statFs.blockSizeLong
                return formatValue(count * size)
            }
            return VALUE_MIN
        }

    override fun loadFile(fileName: String): File? {
        val file = File(path, fileName)
        if (!file.exists()) {
            return null
        }
        return file
    }

    override fun loadFileContent(fileName: String): String? {
        return loadFile(fileName)?.readText()
    }

    override fun loadImage(fileName: String): Bitmap? {
        val file = File(path, fileName)
        if (!file.exists()) {
            return null
        }
        // Not yet unbearably large question
        return BitmapFactory.decodeFile(file.absolutePath)
    }

    override fun exists(fileName: String): Boolean {
        return File(path, fileName).exists()
    }

    override fun createFile(fileName: String): Boolean {
        return File(path, fileName).createNewFile()
    }

    override fun writeTextToFile(fileName: String, content: String) {
        if (!exists(fileName)) {
            createFile(fileName)
        }
        try {
            val file = File(path, fileName)
            file.writeText(content)
        } catch (e: IOException) {
            Log.e(TAG, "$WRITE_TEXT_FILE_EXCEPTION : $e")
        }
    }

    override fun removeFile(fileName: String): Boolean {
        val file = File(path, fileName)
        if (file.exists()) {
            return try {
                file.delete()
            } catch (e: Exception) {
                // todo logger
                Log.e(TAG, "$REMOVE_FILE_EXCEPTION ${e.stackTrace}")
                false
            }
        }
        return false
    }

    private fun formatValue(value: Long) = value / MBSize / MBSize

}