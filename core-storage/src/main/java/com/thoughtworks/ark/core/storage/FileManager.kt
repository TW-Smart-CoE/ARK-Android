package com.thoughtworks.ark.core.storage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import android.os.StatFs
import com.thoughtworks.ark.core.logging.Logger
import java.io.File
import java.io.IOException

class FileManager : StorageInterface {

    override var path: String? = externalBaseDir

    override val isExternalMounted: Boolean
        get() = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
        } else false

    override val externalBaseDir: String?
        get() = if (isExternalMounted && Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
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
                return formatBytesValueToMB(count * size)
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
                return formatBytesValueToMB(count * size)
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
        if (!path?.let { File(it).exists() }!!) {
            path?.let { File(it).mkdir() }
        }
        return File(path, filename).createNewFile()
    }

    override fun writeTextToFile(filename: String, content: String) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            if (!exists(filename)) {
                createFile(filename)
            }
            try {
                val file = File(path, filename)
                file.writeText(content)
            } catch (e: IOException) {
                Logger.e("$WRITE_TEXT_FILE_EXCEPTION $e")
            }
        }
    }

    override fun removeFile(filename: String): Boolean {
        val file = File(path, filename)
        if (file.exists()) {
            return try {
                file.delete()
            } catch (e: IOException) {
                Logger.e("$REMOVE_FILE_EXCEPTION $e")
                false
            }
        }
        return false
    }

    private fun formatBytesValueToMB(value: Long) = value / MBSize / MBSize

    companion object {
        private const val MBSize = 1024
        private const val WRITE_TEXT_FILE_EXCEPTION = "write text to file exception"
        private const val REMOVE_FILE_EXCEPTION = "remove file exception"
        private const val VALUE_MIN = 0L
    }
}