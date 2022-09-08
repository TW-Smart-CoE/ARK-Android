@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    id("build.logic") apply false
    alias(libs.plugins.application) apply false
    alias(libs.plugins.library) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.detekt) apply false
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}