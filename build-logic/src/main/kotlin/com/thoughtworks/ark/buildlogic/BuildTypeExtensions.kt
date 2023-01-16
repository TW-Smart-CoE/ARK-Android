package com.thoughtworks.ark.buildlogic

import org.gradle.api.Project

fun Project.configBuildTypes() {
    android.apply {
        signingConfigs {
            create("app") {
                storeFile = file("${rootDir.path}/config/keystore/release.keystore")
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
            }
            getByName("release") {
                isDebuggable = false
                isMinifyEnabled = true
                isShrinkResources = true
                signingConfig = signingConfigs.findByName("app")
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
}
