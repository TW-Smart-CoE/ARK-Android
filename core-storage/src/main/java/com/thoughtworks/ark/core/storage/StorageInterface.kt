package com.thoughtworks.ark.core.storage

import android.graphics.Bitmap
import java.io.File

interface StorageInterface {

    var path: String?

    val isExternalMounted: Boolean

    val externalBaseDir: String?

    val externalSize: Long

    val externalAvailableSize: Long

    fun loadFile(filename: String): File?

    fun loadFileContent(filename: String): String?

    fun loadImage(filename: String): Bitmap?

    fun exists(filename: String): Boolean

    fun createFile(filename: String): Boolean

    fun writeTextToFile(filename: String, content: String): Boolean

    fun removeFile(filename: String): Boolean
}