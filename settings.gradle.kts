@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage")

fun readConfig(name: String): String {
    return settings.extensions.extraProperties.properties[name] as String?
        ?: System.getenv(name) ?: ""
}

pluginManagement {
    fun createBuildLogicPath(): String {
        //If it is build by Jenkins, use the project directory to keep BuildLogic
        return if (System.getenv("BUILD_ID").isNullOrEmpty()) {
            "../BuildLogic"
        } else {
            "BuildLogic"
        }
    }

    fun initBuildLogic(buildLogicPath: String) {
        fun execCmd(workPath: String, cmd: String): String {
            val stdout = java.io.ByteArrayOutputStream()
            exec {
                workingDir = file(workPath)
                commandLine(cmd.split(" "))
                standardOutput = stdout
            }
            return stdout.toString().trim()
        }

        if (!file(buildLogicPath).exists()) {
            println("Init build logic...")
            //clone build logic to BuildLogic dir
            val result = execCmd(
                ".",
                "git clone -b main https://github.com/TW-Smart-CoE/BuildLogic.git $buildLogicPath"
            )
            if (result.isNotEmpty()) {
                println(result)
            }
            println("Build logic init success")
        } else {
            println("Update build logic...")
            val result = execCmd(buildLogicPath, "git pull")
            if (result.isNotEmpty()) {
                println(result)
            }
            println("Update build logic success")
        }
    }

    val buildLogicPath = createBuildLogicPath()
    initBuildLogic(buildLogicPath)
    includeBuild(buildLogicPath)

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

        if (readConfig("MAVEN_REPO").isNotEmpty()) {
            maven {
                url = uri(readConfig("MAVEN_REPO"))
                isAllowInsecureProtocol = true
                credentials {
                    username = readConfig("MAVEN_USER")
                    password = readConfig(("MAVEN_PWD"))
                }
            }
        } else {
            System.err.println("Please config your private Maven repo!")
        }
    }

    versionCatalogs {
        create("libs") {
            from("io.github.ssseasonnn:VersionCatalog:0.0.4")
        }
    }
}

rootProject.name = "ARK-Android"
// Main App Module
include(":app")
include(":sample")

// UI Module for cross feature UI components
include(":ui")
// Common Module/Utils/Extensions for cross feature
include(":core")
include(":core-testing")
include(":core-permission")
include(":core-media")
include(":core-storage")
include(":webview")
include(":foundation")