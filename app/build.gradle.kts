import com.thoughtworks.android.ark.buildlogic.plugin.androidApplication
import com.thoughtworks.android.ark.buildlogic.plugin.configCompose

@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.detekt)
}

androidApplication {
    defaultConfig {
        applicationId = "com.thoughtworks.android.ark"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

configCompose()

dependencies {
    implementation(project(":ui"))
    implementation(project(":core"))
    // kotlin
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.android)
    implementation(libs.bundles.navigation)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.bundles.unitTest)
    testImplementation(libs.coreTesting)
    testImplementation(libs.composeUiTest)

    testImplementation(libs.hilt.android)
    kaptTest(libs.hilt.compiler)
}