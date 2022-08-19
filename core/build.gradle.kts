import com.thoughtworks.ark.androidLibrary

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin)
}

apply(from = "../config/jacoco/modules.kts")

androidLibrary()

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.bundles.kotlin)
    api(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp.logging)
    implementation(libs.lifecycle.extensions)
    implementation(libs.lifecycle.viewModelKtx)

    testImplementation(libs.junit4)
}