package com.thoughtworks.android.ark.buildlogic.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class ArkPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        //no-op
    }
}

class BaseAppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = getLibs()
            pluginManager.apply {
                apply(libs.getPlugin("application"))
                apply(libs.getPlugin("kotlin"))
                apply(libs.getPlugin("kapt"))
                apply(libs.getPlugin("hilt"))
                apply(libs.getPlugin("detekt"))
            }
        }
    }
}

class BaseLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = getLibs()
            pluginManager.apply {
                apply(libs.getPlugin("library"))
                apply(libs.getPlugin("kotlin"))
            }
        }
    }
}