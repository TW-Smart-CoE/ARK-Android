package com.thoughtworks.android.ark.buildlogic.plugin

import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.use.PluginDependency
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions


fun Project.getApp(): BaseAppModuleExtension {
    return extensions.getByType()
}

fun Project.getLibrary(): LibraryExtension {
    return extensions.getByType()
}

fun Project.getLibs(): VersionCatalog {
    return extensions.getByType<VersionCatalogsExtension>().named("libs")
}

fun VersionCatalog.getVersion(name: String): Int {
    return findVersion(name).get().toString().toInt()
}

fun VersionCatalog.getVersionStr(name: String): String {
    return findVersion(name).get().toString()
}

fun VersionCatalog.getBundle(name: String): Any {
    return findBundle(name).get()
}

fun VersionCatalog.getPlugin(name: String): String {
    return (findPlugin(name).get() as Provider<PluginDependency>).get().pluginId
}

internal val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension
        ?: error("Project '$name' is not an Android module")

internal fun BaseExtension.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

internal fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

@Suppress("UNCHECKED_CAST")
internal fun <T> Any.applyAs(block: T.() -> Unit) {
    (this as T).block()
}