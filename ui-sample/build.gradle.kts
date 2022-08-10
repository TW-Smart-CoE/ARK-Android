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
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}