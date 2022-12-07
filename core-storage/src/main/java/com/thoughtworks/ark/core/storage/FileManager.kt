package com.thoughtworks.ark.core.storage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.StatFs
import android.util.Log
import java.io.File
import java.io.IOException

class FileManager (private var path: String) : StorageInterface {

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

    override fun loadFile(filename: String): File? {
        val file = File(path, filename)
        if (!file.exists()) {
            return null
        }
        return file
    }

    override fun loadFileContent(filename: String): String? {
        return loadFile(filename)?.readText()
    }

    override fun loadImage(filename: String): Bitmap? {
        val file = File(path, filename)
        if (!file.exists()) {
            return null
        }
        // Not yet unbearably large question
        return BitmapFactory.decodeFile(file.absolutePath)
    }

    override fun exists(filename: String): Boolean {
        return File(path, filename).exists()
    }

    override fun createFile(filename: String): Boolean {
        return File(path, filename).createNewFile()
    }

    override fun writeTextToFile(filename: String, content: String) {
        if (!exists(filename)) {
            createFile(filename)
        }
        try {
            val file = File(path, filename)
            file.writeText(content)
        } catch (e: IOException) {
            Log.e(TAG, "$WRITE_TEXT_FILE_EXCEPTION : $e")
        }
    }

    override fun removeFile(filename: String): Boolean {
        val file = File(path, filename)
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