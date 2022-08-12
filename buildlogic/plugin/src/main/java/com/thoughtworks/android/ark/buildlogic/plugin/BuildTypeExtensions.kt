package com.thoughtworks.android.ark.buildlogic.plugin

import org.gradle.api.Project

fun Project.configBuildTypes() {
    android.apply {
        signingConfigs {
            create("ark") {
                storeFile = file("${rootDir.path}/ark.keystore")
                storePassword = System.getenv("SIGN_CONFIG_STORE_PASSWD")
                keyAlias = System.getenv("SIGN_CONFIG_KEY_ALIAS")
                keyPassword = System.getenv("SIGN_CONFIG_KEY_PASSWD")
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