import com.thoughtworks.ark.androidApplication
import com.thoughtworks.ark.enableCompose

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.detekt)
    alias(libs.plugins.router)
}

apply(from = "../config/jacoco/modules.kts")

androidApplication {
    defaultConfig {
        applicationId = "com.thoughtworks.ark"
        versionCode = 1
        versionName = "1.0.0"
    }

    enableCompose()
}

dependencies {
    implementation(project(":ui"))
    implementation(project(":core"))

    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.android)
    implementation(libs.bundles.compose)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.router)
    kapt(libs.router.compiler)

    implementation(libs.bundles.coil)

    testImplementation(project(":core-testing"))
    detektPlugins(libs.detekt.formatting)
}