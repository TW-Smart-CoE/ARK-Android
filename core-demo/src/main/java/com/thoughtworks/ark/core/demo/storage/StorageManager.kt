package com.thoughtworks.ark.core.demo.storage

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException

// todo object
class StorageManager(context: Context) {

    private var filesPath: String = ""
    private var savesPath: String = ""

    companion object {
        private const val TAG = "FileManager"
    }

    init {
        val documentFile =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        val packageFile = File(documentFile, context.packageName)
        if (!packageFile.exists()) {
            packageFile.mkdir()
        }
        filesPath = packageFile.path + "/files"
        if (!File(filesPath).exists()) {
            File(filesPath).mkdir()
        }

        savesPath = packageFile.path + "/saves"
        if (!File(savesPath).exists()) {
            File(savesPath).mkdir()
        }
    }

    fun listFilesDir(): List<String> {
        val fileList = File(filesPath).list()
        return fileList?.map { it } ?: emptyList()
    }

    fun listFilesDirChildPath(path: String): List<String> {
        val childDir = File("$filesPath/$path")
        if (!childDir.isDirectory || !childDir.exists()) {
            return arrayListOf()
        }
        return childDir.list()?.map { it } ?: emptyList()
    }

    fun readFile(fileName: String): String? {
        val file = File(filesPath, fileName)
        if (file.isDirectory) {
            return null
        }
        return try {
            file.readText()
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
            return null
        }
    }

    fun writeFile(fileName: String, fileContent: String) {
        try {
            val file = File(filesPath, fileName)
            file.writeText(fileContent)
        } catch (e: IOException) {
            Log.e(TAG, e.toString())
        }
    }

}