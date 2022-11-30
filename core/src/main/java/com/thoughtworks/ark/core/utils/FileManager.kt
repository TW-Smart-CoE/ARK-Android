package com.thoughtworks.ark.core.utils


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.IOException

class FileManager(val context: Context) {
    private var filesPath: String = ""
    private var packSavePath: String = ""
    private var resPath: String = ""

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

        packSavePath = packageFile.path + "/saves"
        if (!File(packSavePath).exists()) {
            File(packSavePath).mkdir()
        }

        Log.i("storage2", "filesPath : $filesPath")
        Log.i("storage2", "packSavePath : $packSavePath")
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
            val file = File(packSavePath, fileName)
            Log.i(tag, "outPutFile : $packSavePath, name: $fileName")
            file.writeText(fileContent)
        } catch (e: IOException) {
            Log.e(tag, "outPutFile exception ${e.stackTrace}")
        }
    }


    fun loadResImage(imageFileName: String): Bitmap? {
        val file = File(resPath, imageFileName)
        if (!file.exists()) {
            return null
        }

        return BitmapFactory.decodeFile(file.absolutePath)
    }

    fun loadFile(fileName: String): File? {
        val file = File(resPath, fileName)
        if (!file.exists()) {
            return null
        }

        return file
    }

    fun checkFileExist(imageFileName: String): Boolean {
        return File(resPath, imageFileName).exists()
    }
}
