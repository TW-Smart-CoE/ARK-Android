package com.thoughtworks.ark.buildlogic

import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.thoughtworks.ark.buildlogic.AppConfig.APP_HOST_KEY
import com.thoughtworks.ark.buildlogic.AppConfig.APP_HOST_VALUE
import com.thoughtworks.ark.buildlogic.AppConfig.APP_SCHEME_KEY
import com.thoughtworks.ark.buildlogic.AppConfig.APP_SCHEME_VALUE
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import java.util.Locale.ROOT

enum class FlavorDimension {
    ContentType
}

enum class Flavor(
    val dimension: FlavorDimension = FlavorDimension.ContentType,
    val disableDebug: Boolean = false,
    val suffix: String = ""
) {
    Dev(suffix = "dev"),
    Uat(disableDebug = true, suffix = "uat"),
    Staging(disableDebug = true, suffix = "staging"),
    Prod(disableDebug = true)
}

fun Project.configFlavorsLibrary() {
    android.apply {
        flavorDimensions(FlavorDimension.ContentType.name)

        productFlavors {
            val addConstantToFlavorLambda = createAddFlavorConstantLambda()

            Flavor.values().forEach {
                val flavorName = it.name.toLowerCase(ROOT)
                create(flavorName) {
                    dimension = it.dimension.name
                    proguardFile("$projectDir/proguard-rules.pro")

                    addConstantToFlavorLambda(
                        this,
                        APP_SCHEME_KEY,
                        if (it.suffix.isNotEmpty()) "$APP_SCHEME_VALUE-${it.suffix}" else APP_SCHEME_VALUE
                    )
                    addConstantToFlavorLambda(this, APP_HOST_KEY, APP_HOST_VALUE)
                }
            }
        }
    }
    filterFlavors()
}

fun Project.configFlavorsApplication() {
    android.apply {
        flavorDimensions(FlavorDimension.ContentType.name)

        productFlavors {
            val addConstantToFlavorLambda = createAddFlavorConstantLambda()

            Flavor.values().forEach {
                val flavorName = it.name.toLowerCase(ROOT)
                create(flavorName) {
                    dimension = it.dimension.name
                    applicationIdSuffix = if (it.suffix.isNotEmpty()) ".${it.suffix}" else ""
                    proguardFile("$projectDir/proguard-rules.pro")

                    addConstantToFlavorLambda(
                        this,
                        APP_SCHEME_KEY,
                        if (it.suffix.isNotEmpty()) "$APP_SCHEME_VALUE-${it.suffix}" else APP_SCHEME_VALUE
                    )
                    addConstantToFlavorLambda(this, APP_HOST_KEY, APP_HOST_VALUE)
                }
            }
        }
    }
    filterFlavors()
}

private fun Project.filterFlavors() {
    findComponentExtension().beforeVariants { variant ->
        val disabled = Flavor.values()
            .filter { it.disableDebug }
            .map { it.name.toLowerCase(ROOT) }
        if (disabled.contains(variant.flavorName) && variant.buildType == "debug") {
            variant.enable = false
        }
    }
}

private fun Project.findComponentExtension(): AndroidComponentsExtension<*, *, *> {
    val applicationComponent = extensions.findByType<ApplicationAndroidComponentsExtension>()
    val libraryAndroidComponent = extensions.findByType<LibraryAndroidComponentsExtension>()
    return applicationComponent ?: libraryAndroidComponent as AndroidComponentsExtension<*, *, *>
}
