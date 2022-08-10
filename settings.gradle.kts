pluginManagement {
    includeBuild("buildlogic")
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

rootProject.name = "Android-ARK"
// Main App Module
include(":app")
// UI Module for cross feature UI components
include(":ui")
// Common Module/Utils/Extensions for cross feature
include(":core")

include(":ui-sample")