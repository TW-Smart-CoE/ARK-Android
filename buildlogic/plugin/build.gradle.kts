plugins {
    `kotlin-dsl`
}

group = "com.thoughtworks.android.ark.buildlogic.plugin"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(libs.androidGradlePlugin)
    implementation(libs.kotlinGradlePlugin)
}

gradlePlugin {
    plugins.register("buildLogicPlugin") {
        id = "build.logic"
        implementationClass = "com.thoughtworks.android.ark.buildlogic.plugin.BuildLogicPlugin"
    }
}