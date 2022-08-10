@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.application) apply false
    alias(libs.plugins.library) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.detekt) apply false
    id("ark") apply false
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}