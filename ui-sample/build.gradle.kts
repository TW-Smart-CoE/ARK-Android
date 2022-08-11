import com.thoughtworks.android.ark.buildlogic.plugin.androidApplication
import com.thoughtworks.android.ark.buildlogic.plugin.configCompose

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.application)
    alias(libs.plugins.kotlin)
}

androidApplication {
    defaultConfig {
        applicationId = "com.thoughtworks.android.ark.uisample"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

configCompose()

dependencies {
    implementation(project(":ui"))
    implementation(libs.bundles.android)
    implementation(libs.bundles.navigation)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
}