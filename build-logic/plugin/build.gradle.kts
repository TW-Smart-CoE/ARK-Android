plugins {
    `kotlin-dsl`
}

group = "com.thoughtworks.ark.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins.register("buildLogicPlugin") {
        id = "build.logic"
        implementationClass = "BuildLogicPlugin"
    }
}