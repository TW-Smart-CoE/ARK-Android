package com.thoughtworks.ark.sound.alert

import java.io.File

class AlertItem private constructor(
    val alertRes: Int = 0,
    val alertAsset: String = "",
    val alertFile: File? = null,
) {
    companion object {
        fun fromRes(resId: Int): AlertItem {
            require(resId > 0) { "Invalid resId!" }
            return AlertItem(alertRes = resId)
        }

        fun fromAsset(assetPath: String): AlertItem {
            require(assetPath.isNotEmpty()) { "Invalid assetPath!" }
            return AlertItem(alertAsset = assetPath)
        }

        fun fromFile(file: File): AlertItem {
            require(file.exists() && file.isFile) { "Invalid file!" }
            return AlertItem(alertFile = file)
        }
    }
}