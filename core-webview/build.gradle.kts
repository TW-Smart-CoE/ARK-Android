import com.thoughtworks.ark.buildlogic.androidLibrary

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.detekt)
}

apply(from = "../config/jacoco/modules.kts")

androidLibrary {
    namespace = "com.thoughtworks.ark.webview"
}

dependencies {
    implementation(project(":ui"))
    implementation(project(":core"))
    implementation(libs.appcompat)
    implementation(libs.kotlin.coroutines)
    implementation(libs.core.ktx)

    testImplementation(libs.junit4)

    detektPlugins(libs.detekt.formatting)
}