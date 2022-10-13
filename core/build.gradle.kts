import com.thoughtworks.ark.androidLibrary

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin)
}

apply(from = "../config/jacoco/modules.kts")

androidLibrary()

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.bundles.kotlin)
    api(libs.retrofit.core)
    implementation(libs.bundles.retrofit)
    implementation(libs.lifecycle.extensions)
    implementation(libs.lifecycle.viewmodel.ktx)

    testImplementation(libs.junit4)
}