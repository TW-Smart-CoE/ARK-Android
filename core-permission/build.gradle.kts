import com.thoughtworks.ark.androidLibrary
import com.thoughtworks.ark.enableCompose

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.detekt)
}

apply(from = "../config/jacoco/modules.kts")

androidLibrary(namespace = "com.thoughtworks.ark.core.permission") {
    enableCompose()
}

dependencies {
    implementation(project(":core"))
    implementation(project(":ui"))

    implementation(libs.core.ktx)
    implementation(libs.material)
    implementation(libs.bundles.compose)
    implementation(libs.accompanist.permission)
    testImplementation(libs.junit4)

    detektPlugins(libs.detekt.formatting)
}