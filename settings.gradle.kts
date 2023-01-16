@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

fun readConfig(name: String): String {
    return settings.extensions.extraProperties.properties[name] as String?
        ?: System.getenv(name) ?: ""
}

pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://jitpack.io")
        mavenLocal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
        mavenLocal()
        maven {
            url = uri(readConfig("MAVEN_REPO"))
            isAllowInsecureProtocol = true
            credentials {
                username = readConfig("MAVEN_USER")
                password = readConfig(("MAVEN_PWD"))
            }
        }
    }

    versionCatalogs {
        create("libs") {
            from("io.github.ssseasonnn:VersionCatalog:0.0.2")
        }
    }
}

rootProject.name = "ARK-Android"
// Main App Module
include(":app")
// UI Module for cross feature UI components
include(":ui")
// Common Module/Utils/Extensions for cross feature
include(":core")
include(":core-testing")
include(":core-permission")
include(":core-media")
include(":core-storage")
include(":sample")
include(":webview")