import com.thoughtworks.ark.enableCompose
import com.thoughtworks.ark.androidLibrary

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin)
}

apply(from = "../config/jacoco/modules.kts")

androidLibrary {
    enableCompose()
}

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.material)
    implementation(libs.bundles.compose)
    testImplementation(libs.junit4)
}