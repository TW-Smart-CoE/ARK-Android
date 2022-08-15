@file:Suppress("UnstableApiUsage")

package com.thoughtworks.android.ark

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

fun Project.androidApplication(block: BaseAppModuleExtension.() -> Unit = {}) {
    configBuildTypes()
    configFlavors()
    configBase().applyAs<BaseAppModuleExtension> {
        block()
    }
}

fun Project.androidLibrary(block: LibraryExtension.() -> Unit = {}) {
    configFlavors()
    configBase().applyAs<LibraryExtension> {
        block()
    }
}

private fun Project.configBase(): BaseExtension {
    return android.apply {
        val libs = getLibs()
        setCompileSdkVersion(libs.getVersion("compileSdkVersion"))

        defaultConfig {
            minSdk = libs.getVersion("minSdkVersion")
            targetSdk = libs.getVersion("targetSdkVersion")
            vectorDrawables { useSupportLibrary = true }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }

        packagingOptions {
            resources {
                excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            }
        }
    }
}

fun Project.enableCompose() {
    val libs = getLibs()

    android.apply {
        buildFeatures.compose = true

        composeOptions {
            kotlinCompilerExtensionVersion = libs.getVersionStr("composeVersion")
        }
    }
}

