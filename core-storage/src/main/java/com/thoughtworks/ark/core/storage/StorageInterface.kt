package com.thoughtworks.ark.core.storage

import android.graphics.Bitmap
import java.io.File

sealed interface StorageInterface {

    val isExternalMounted: Boolean

    val externalBaseDir: String?

    val externalSize: Long

    val externalAvailableSize: Long

    fun loadFile(fileName: String): File?

    fun loadFileContent(fileName: String): String?

    fun loadImage(fileName: String): Bitmap?

    fun exists(fileName: String): Boolean

    fun createFile(fileName: String): Boolean

    fun writeTextToFile(fileName: String, content: String)

    fun removeFile(fileName: String): Boolean

}