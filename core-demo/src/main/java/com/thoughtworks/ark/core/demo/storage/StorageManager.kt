package com.thoughtworks.ark.core.demo.storage

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


}

