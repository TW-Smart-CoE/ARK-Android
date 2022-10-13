@file:Suppress("UnstableApiUsage")

package com.thoughtworks.ark

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

fun Project.androidApplication(block: BaseAppModuleExtension.() -> Unit = {}) {
    configBuildTypes()
    configFlavorsApplication()
    configBase().applyAs<BaseAppModuleExtension> {
        block()
    }
}

fun Project.androidLibrary(block: LibraryExtension.() -> Unit = {}) {
    configFlavorsLibrary()
    configBase().applyAs<LibraryExtension> {
        block()
    }
}

private fun Project.configBase(): BaseExtension {
    return android.apply {
        val libs = getLibs()
        setCompileSdkVersion(libs.getVersion("sdk-compile-version"))

        defaultConfig {
            minSdk = libs.getVersion("sdk-min-version")
            targetSdk = libs.getVersion("sdk-target-version")
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
            kotlinCompilerExtensionVersion = libs.getVersionStr("compose")
        }
    }
}

