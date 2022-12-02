package com.thoughtworks.ark.core.demo.storage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.StatFs
import android.util.Log
import org.json.JSONObject
import java.io.File
import java.io.IOException

object StorageManager {

    const val TAG = "FileManager"

    fun loadFile(resPath: String, fileName: String): File? {
        val file = File(resPath, fileName)
        if (!file.exists()) {
            return null
        }
        return file
    }

    fun loadJson(resPath: String, fileName: String, jsonKey: String): String? {
        val jsonContent = loadFile(resPath, fileName)?.readText()
        return jsonContent?.let { JSONObject(it).getJSONObject(jsonKey).toString() }
    }

    fun loadResImage(resPath: String, imageFileName: String): Bitmap? {
        val file = File(resPath, imageFileName)
        if (!file.exists()) {
            return null
        }
        return BitmapFactory.decodeFile(file.absolutePath)
    }

    fun checkFileExist(resPath: String, imageFileName: String): Boolean {
        return File(resPath, imageFileName).exists()
    }

    fun writeFile(resPath: String, fileName: String, fileContent: String) {
        try {
            val file = File(resPath, fileName)
            file.writeText(fileContent)
        } catch (e: IOException) {
            Log.e(TAG, "outPutFile exception ${e.stackTrace}")
        }
    }

    fun removeFile(resPath: String, fileName: String): Boolean {
        val file = File(resPath, fileName)
        if (file.exists()) {
            return try {
                file.delete()
                true
            } catch (e: Exception) {
                false
            }
        }
        return false
    }

    val isSDCardMounted: Boolean
        get() =
            (Environment.getExternalStorageState() ==
                    Environment.MEDIA_MOUNTED)

    val sDCardBaseDir: String?
        get() = if (isSDCardMounted) {
            Environment.getExternalStorageDirectory().absolutePath
        } else null

    val sDCardSize: Long
        get() {
            if (isSDCardMounted) {
                val statFs = StatFs(sDCardBaseDir)
                val count = statFs.blockCountLong
                val size = statFs.blockSizeLong
                return count * size / 1024 / 1024
            }
            return 0
        }

    val sDCardFreeSize: Long
        get() {
            if (isSDCardMounted) {
                val statFs = StatFs(sDCardBaseDir)
                val count = statFs.freeBlocksLong
                val size = statFs.blockSizeLong
                return count * size / 1024 / 1024
            }
            return 0
        }

    val sDCardAvailableSize: Long
        get() {
            if (isSDCardMounted) {
                val statFs = StatFs(sDCardBaseDir)
                val count = statFs.availableBlocksLong
                val size = statFs.blockSizeLong
                return count * size / 1024 / 1024
            }
            return 0
        }

}

