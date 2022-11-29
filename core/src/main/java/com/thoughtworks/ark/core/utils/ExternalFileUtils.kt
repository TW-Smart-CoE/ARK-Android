package com.thoughtworks.ark.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.os.StatFs
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


class ExternalFileUtils {

    // 从SDCard中寻找指定目录下的文件，返回Bitmap
    fun loadBitmapFromSDCard(filePath: String?): Bitmap? {
        val data = loadFileFromSDCard(filePath)
        if (data != null) {
            val bm: Bitmap? = BitmapFactory.decodeByteArray(data, 0, data.size)
            if (bm != null) {
                return bm
            }
        }
        return null
    }

    companion object {
        // 判断SD卡是否被挂载
        val isSDCardMounted: Boolean
            get() =
                (Environment.getExternalStorageState() ==
                        Environment.MEDIA_MOUNTED)

        // 获取SD卡的根目录
        val sDCardBaseDir: String
            get() = if (isSDCardMounted) {
                Environment.getExternalStorageDirectory().absolutePath
            } else "null"

        // 获取SD卡的完整空间大小，返回MB
        val sDCardSize: Long
            get() {
                if (isSDCardMounted) {
                    val fs = StatFs(sDCardBaseDir)
                    val count = fs.blockCountLong
                    val size = fs.blockSizeLong
                    return count * size / 1024 / 1024
                }
                return 0
            }

        // 获取SD卡的剩余空间大小
        val sDCardFreeSize: Long
            get() {
                if (isSDCardMounted) {
                    val fs = StatFs(sDCardBaseDir)
                    val count = fs.freeBlocksLong
                    val size = fs.blockSizeLong
                    return count * size / 1024 / 1024
                }
                return 0
            }

        // 获取SD卡的可用空间大小
        val sDCardAvailableSize: Long
            get() {
                if (isSDCardMounted) {
                    val fs = StatFs(sDCardBaseDir)
                    val count = fs.availableBlocksLong
                    val size = fs.blockSizeLong
                    return count * size / 1024 / 1024
                }
                return 0
            }

        // 往SD卡的公有目录下保存文件
        fun saveFileToSDCardPublicDir(
            data: ByteArray, type: String,
            fileName: String
        ): Boolean {
            var bos: BufferedOutputStream? = null
            if (isSDCardMounted) {
                val file: File = Environment.getExternalStoragePublicDirectory(type)
                try {
                    bos = BufferedOutputStream(
                        FileOutputStream(
                            File(
                                file, fileName
                            )
                        )
                    )
                    bos.write(data)
                    bos.flush()
                    return true
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    try {
                        bos!!.close()
                    } catch (e: IOException) {
                        // TODO Auto-generated catch block
                        e.printStackTrace()
                    }
                }
            }
            return false
        }

        // 往SD卡的自定义目录下保存文件
        fun saveFileToSDCardCustomDir(
            data: ByteArray?, dir: String,
            fileName: String?
        ): Boolean {
            var bos: BufferedOutputStream? = null
            if (isSDCardMounted) {
                val file = File(sDCardBaseDir + File.separator.toString() + dir)
                if (!file.exists()) {
                    file.mkdirs() // 递归创建自定义目录
                }
                try {
                    bos = BufferedOutputStream(
                        FileOutputStream(
                            fileName?.let {
                                File(
                                    file, it
                                )
                            }
                        )
                    )
                    bos.write(data)
                    bos.flush()
                    return true
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    try {
                        bos!!.close()
                    } catch (e: IOException) {
                        // TODO Auto-generated catch block
                        e.printStackTrace()
                    }
                }
            }
            return false
        }

        // 往SD卡的私有Files目录下保存文件
        fun saveFileToSDCardPrivateFilesDir(
            data: ByteArray?,
            type: String?, fileName: String?, context: Context
        ): Boolean {
            var bos: BufferedOutputStream? = null
            if (isSDCardMounted) {
                val file: File? = context.getExternalFilesDir(type)
                try {
                    bos = BufferedOutputStream(
                        FileOutputStream(
                            fileName?.let {
                                File(
                                    file, it
                                )
                            }
                        )
                    )
                    bos.write(data)
                    bos.flush()
                    return true
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    try {
                        bos!!.close()
                    } catch (e: IOException) {
                        // TODO Auto-generated catch block
                        e.printStackTrace()
                    }
                }
            }
            return false
        }

        // 往SD卡的私有Cache目录下保存文件
        fun saveFileToSDCardPrivateCacheDir(
            data: ByteArray?,
            fileName: String?, context: Context
        ): Boolean {
            var bos: BufferedOutputStream? = null
            if (isSDCardMounted) {
                val file: File? = context.externalCacheDir
                try {
                    bos = BufferedOutputStream(
                        FileOutputStream(
                            fileName?.let {
                                File(
                                    file, it
                                )
                            }
                        )
                    )
                    bos.write(data)
                    bos.flush()
                    return true
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    try {
                        bos!!.close()
                    } catch (e: IOException) {
                        // TODO Auto-generated catch block
                        e.printStackTrace()
                    }
                }
            }
            return false
        }

        // 保存bitmap图片到SDCard的私有Cache目录
        fun saveBitmapToSDCardPrivateCacheDir(
            bitmap: Bitmap,
            fileName: String?, context: Context
        ): Boolean {
            return if (isSDCardMounted) {
                var bos: BufferedOutputStream? = null
                // 获取私有的Cache缓存目录
                val file: File? = context.externalCacheDir
                try {
                    bos = BufferedOutputStream(
                        FileOutputStream(
                            fileName?.let {
                                File(
                                    file, it
                                )
                            }
                        )
                    )
                    if ((fileName != null)
                        && (fileName.contains(".png") || fileName
                            .contains(".PNG"))
                    ) {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
                    } else {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos)
                    }
                    bos.flush()
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    if (bos != null) {
                        try {
                            bos.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
                true
            } else {
                false
            }
        }

        // 从SD卡获取文件
        fun loadFileFromSDCard(fileDir: String?): ByteArray? {
            var bis: BufferedInputStream? = null
            val bas = ByteArrayOutputStream()
            try {
                bis = BufferedInputStream(
                    FileInputStream(fileDir?.let { File(it) })
                )
                val buffer = ByteArray(8 * 1024)
                var c = 0
                while (bis.read(buffer).also { c = it } != -1) {
                    bas.write(buffer, 0, c)
                    bas.flush()
                }
                return bas.toByteArray()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    bas.close()
                    bis!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return null
        }

        fun readSDCardFile(path: String, fileName: String): String {
            val file = File(path, fileName)
            if (file.isDirectory) {
                return ""
            }
            return file.readText()
        }

        // 获取SD卡公有目录的路径
        fun getSDCardPublicDir(type: String?): String {
            return Environment.getExternalStoragePublicDirectory(type).toString()
        }

        // 获取SD卡私有Cache目录的路径
        fun getSDCardPrivateCacheDir(context: Context): String {
            return context.externalCacheDir?.absolutePath ?: ""
        }

        // 获取SD卡私有Files目录的路径
        fun getSDCardPrivateFilesDir(context: Context, type: String?): String {
            return context.getExternalFilesDir(type)?.absolutePath ?: ""
        }

        fun isFileExist(filePath: String?): Boolean {
            val file = filePath?.let { File(it) }
            return file?.isFile ?: false
        }

        // 从sdcard中删除文件
        fun removeFileFromSDCard(filePath: String?): Boolean {
            val file = filePath?.let { File(it) }
            if ((file != null) && file.exists()) {
                return try {
                    file.delete()
                    true
                } catch (e: Exception) {
                    false
                }
            }
            return false
        }

    }
}