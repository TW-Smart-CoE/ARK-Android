package com.thoughtworks.android.ark

import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.findByType
import java.util.Locale.ROOT

enum class FlavorDimension {
    ContentType
}

enum class Flavor(
    val dimension: FlavorDimension = FlavorDimension.ContentType,
    val disableDebug: Boolean = false
) {
    Dev, Uat(disableDebug = true), Staging(disableDebug = true), Prod(disableDebug = true)
}

fun Project.configFlavors() {
    val srcPath = "$projectDir/src"

    android.apply {
        flavorDimensions(FlavorDimension.ContentType.name)

        productFlavors {
            Flavor.values().forEach {
                val flavorName = it.name.toLowerCase(ROOT)
                create(flavorName) {
                    dimension = it.dimension.name
                    proguardFile("$srcPath/$flavorName/proguard-rules.pro")
                }
            }
        }
    }

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