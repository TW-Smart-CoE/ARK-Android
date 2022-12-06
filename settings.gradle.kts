pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
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

include(":sound")
include(":video")

include(":sample")
include(":core-storage")
