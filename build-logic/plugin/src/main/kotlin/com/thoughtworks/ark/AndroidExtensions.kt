@file:Suppress("UnstableApiUsage")

package com.thoughtworks.ark

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.android.build.gradle.internal.dsl.BuildType
import org.gradle.api.JavaVersion
import org.gradle.api.Project

fun Project.androidApplication(block: BaseAppModuleExtension.() -> Unit = {}) {
    configBuildTypes()
    configFlavorsApplication()
    configBase().applyAs<BaseAppModuleExtension> {
        block()
    }
}

fun Project.androidLibrary(namespace: String? = null, block: LibraryExtension.() -> Unit = {}) {
    configFlavorsLibrary()
    configBase(namespace).applyAs<LibraryExtension> {
        block()
    }
}

private fun Project.configBase(namespace: String? = null): BaseExtension {
    return android.apply {
        this.namespace = namespace
        val libs = getLibs()
        setCompileSdkVersion(libs.getVersion("sdk-compile-version"))

        defaultConfig {
            minSdk = libs.getVersion("sdk-min-version")
            targetSdk = libs.getVersion("sdk-target-version")
            vectorDrawables { useSupportLibrary = true }
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }

        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
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

fun Project.addDefaultConstant(vararg constantPair: Pair<String, String>) {
    android.apply {
        defaultConfig {
            val addDefaultConstantLambda = createAddDefaultConstantLambda()
            constantPair.forEach {
                addDefaultConstantLambda(it.first, it.second)
            }
        }
    }
}

fun Project.addBuildTypeConstant(block: (BuildType) -> Map<String, String>) {
    android.apply {
        buildTypes {
            val addConstantToBuildTypeLambda = createAddBuildTypeConstantLambda()
            forEach {
                val constantMap = block(it)
                constantMap.forEach { (name, value) ->
                    addConstantToBuildTypeLambda(it, name, value)
                }
            }
        }
    }
}

fun Project.addFlavorConstant(block: (Flavor) -> Map<String, String>) {
    android.apply {
        productFlavors {
            forEach {
                val addConstantToFlavorLambda = createAddFlavorConstantLambda()
                val flavor = it.name.getFlavor()
                val constantMap = block(flavor)
                constantMap.forEach { (name, value) ->
                    addConstantToFlavorLambda(it, name, value)
                }
            }
        }
    }
}
