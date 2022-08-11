package com.thoughtworks.android.ark.buildlogic.plugin

import org.gradle.api.Project

fun Project.configBuildTypes() {
    android.apply {
        signingConfigs {
            create("ark") {
                storeFile = file("${rootDir.path}/ark.keystore")
                storePassword = "ark123"
                keyAlias = "ark"
                keyPassword = "ark123"
                enableV3Signing = true
                enableV4Signing = true
            }
        }

        buildTypes {
            getByName("debug") {
                isDebuggable = true
                isMinifyEnabled = false
                isShrinkResources = false
                signingConfig = signingConfigs.findByName("ark")
            }
            getByName("release") {
                isDebuggable = false
                isMinifyEnabled = true
                isShrinkResources = true
                signingConfig = signingConfigs.findByName("ark")
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
}