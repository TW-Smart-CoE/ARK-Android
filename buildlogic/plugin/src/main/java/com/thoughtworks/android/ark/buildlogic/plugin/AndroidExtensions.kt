@file:Suppress("UnstableApiUsage")

package com.thoughtworks.android.ark.buildlogic.plugin

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

fun Project.androidApplication(block: BaseAppModuleExtension.() -> Unit = {}) {
    configBuildTypes()
    val extension = androidCommon() as BaseAppModuleExtension
    extension.block()
}

fun Project.androidLibrary(block: LibraryExtension.() -> Unit = {}) {
    val extension = androidCommon() as LibraryExtension
    extension.block()
}

private fun Project.androidCommon(): BaseExtension {
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

        buildFeatures.viewBinding = true

        packagingOptions {
            resources {
                excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            }
        }
    }
}


fun Project.configCompose() {
    val libs = getLibs()

    android.apply {
        buildFeatures.compose = true

        composeOptions {
            kotlinCompilerExtensionVersion = libs.getVersionStr("composeVersion")
        }
    }

    dependencies {
        implementation(libs.getBundle("compose"))
    }
}

