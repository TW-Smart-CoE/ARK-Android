import com.thoughtworks.ark.buildlogic.androidLibrary
import com.thoughtworks.ark.buildlogic.enableCompose

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.detekt)
}

apply(from = "../config/jacoco/modules.kts")

androidLibrary {
    namespace = "com.thoughtworks.ark.sound"
    enableCompose()
}

dependencies {
    implementation(libs.exoplayer)
    implementation(libs.kotlin.coroutines)
    implementation(libs.core.ktx)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.compose.ui.tool)

    testImplementation(libs.junit4)

    detektPlugins(libs.detekt.formatting)
}