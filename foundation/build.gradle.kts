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
    namespace = "com.thoughtworks.ark.foundation"
}

dependencies {
    api(project(":ui"))
    api(project(":core"))
    api(project(":core-permission"))
    api(project(":core-media"))
    api(project(":core-storage"))
    api(project(":core-webview"))

    api(libs.appcompat)
    api(libs.kotlin.coroutines)
    api(libs.core.ktx)

    detektPlugins(libs.detekt.formatting)
}