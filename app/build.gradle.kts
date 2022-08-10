@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinKapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.detekt)
}

android {
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig {
        applicationId = "com.thoughtworks.android.ark"
        minSdk = libs.versions.minSdkVersion.get().toInt()
        targetSdk = libs.versions.targetSdkVersion.get().toInt()
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTargetVersion.get()
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeVersion.get()
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(":ui"))
    implementation(project(":core"))
    // kotlin
    implementation(libs.kotlinStdlib)
    implementation(libs.kotlinxCoroutines)
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.constraintLayout)

    implementation(libs.navigationFragmentKtx)
    implementation(libs.navigationUiKtx)
    implementation(libs.bundles.compose)

    implementation(libs.lifecycleLiveDataKtx)
    implementation(libs.lifecycleRuntimeKtx)
    implementation(libs.lifecycleViewModelKtx)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit4)
    testImplementation(libs.truth)
    testImplementation(libs.archCoreTesting)
    testImplementation(libs.composeUiTest)
    testImplementation(libs.kotlinxCoroutinesTest)
//    testImplementation("org.robolectric:robolectric:$robolectric_version")
//    testImplementation("androidx.navigation:navigation-testing:$nav_version")
//    testImplementation("androidx.test.espresso:espresso-core:$espresso_version")
//    testImplementation("androidx.test.espresso:espresso-contrib:$espresso_version")
//    testImplementation("androidx.test.espresso:espresso-intents:$espresso_version")

    testImplementation(libs.hilt.android)
    kaptTest(libs.hilt.compiler)

    testImplementation(libs.mockk)
    testImplementation(libs.mockkAgent)

//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation("androidx.test.espresso:espresso-core:$espresso_version")
//    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
}