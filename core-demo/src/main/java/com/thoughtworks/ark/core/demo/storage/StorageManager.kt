package com.thoughtworks.ark.core.demo.storage

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.json.JSONObject
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


}

