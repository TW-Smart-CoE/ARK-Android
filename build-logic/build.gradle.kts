@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

plugins {
    `kotlin-dsl`
    alias(libs.plugins.detekt)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)

    detektPlugins(libs.detekt.formatting)
}

gradlePlugin {
    plugins.register("buildLogicPlugin") {
        id = "build.logic"
        implementationClass = "BuildLogicPlugin"
    }
}
