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
}

androidApplication {
    defaultConfig {
        applicationId = "com.thoughtworks.ark.sample"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    enableCompose()
}

dependencies {
    implementation(project(":ui"))
    implementation(project(":core"))

    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.android)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.compose)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.bundles.coil)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.bundles.unit.test)
    testImplementation(libs.core.testing)
    testImplementation(libs.compose.ui.test)

    testImplementation(libs.hilt.android)
    kaptTest(libs.hilt.compiler)
    detektPlugins(libs.detekt.formatting)
    androidTestImplementation(libs.espresso.core)
}