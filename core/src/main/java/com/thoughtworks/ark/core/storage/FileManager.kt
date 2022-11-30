package com.thoughtworks.ark.core.storage


import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException

class FileManager(val context: Context) {

    private var filesPath: String = ""
    private var savesPath: String = ""

    private val tag = "FileManager"

    init {
        val documentFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
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

    fun listSDCardFiles(): List<String> {
        val fileList = File(filesPath).list()
        Log.i(tag, "listSDCardFiles:$fileList")
        return fileList?.map { it } ?: emptyList()
    }

    fun listSDCardFileChildPath(path: String): List<String> {
        val childDir = File("$filesPath/$path")
        if (!childDir.isDirectory || !childDir.exists()) {
            Log.i(tag, "child path is not a directory:${childDir.path}")
            return arrayListOf()
        }
        val childList = childDir.list()
        Log.i(tag, "childPath:${childDir.path}  phase child list size: ${childList?.size}")
        childList?.forEach {
            Log.i(tag, "childPath:${childDir.path}  phase child list: $it")
        }
        return childDir.list()?.map { it } ?: emptyList()
    }

    fun readSDCardFile(fileName: String): String {
        val file = File(filesPath, fileName)
        Log.i(tag, "json file name : $filesPath, name: $fileName")
        if (file.isDirectory) {
            return ""
        }
        val content = file.readText()
        Log.i(tag, "json file name : $filesPath, name: $fileName content: $content")
        return content
    }

    fun outPutFile(fileName: String, fileContent: String) {
        try {
            val file = File(savesPath, fileName)
            Log.i(tag, "outPutFile : $savesPath, name: $fileName")
            file.writeText(fileContent)
        } catch (e: IOException) {
            Log.e(tag, "outPutFile exception ${e.stackTrace}")
        }
    }


}
