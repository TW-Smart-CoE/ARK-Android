import com.thoughtworks.android.ark.buildlogic.plugin.configCompose
import com.thoughtworks.android.ark.buildlogic.plugin.androidLibrary

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin)
}

androidLibrary()
configCompose()

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.material)

    testImplementation(libs.junit4)

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}