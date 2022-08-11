import com.thoughtworks.android.ark.buildlogic.plugin.androidLibrary

plugins {
    id("ark.library")
}

androidLibrary()

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit4)
}