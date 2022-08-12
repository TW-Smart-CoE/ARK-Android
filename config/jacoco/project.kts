apply from: "${rootDir}/config/jacoco/jacocoConfig.kts"

project.afterEvaluate {
    def variants = ["debug"]

    tasks.create(name: "allDebugCoverage", type: JacocoReport) {

        group = "Reporting"
        description = "Generate overall Jacoco coverage report for the debug build."

        reports {
            html.enabled(true)
            xml.enabled(true)
        }

        def excludes = project.ext.jacocoFileFilter

        def jClasses = subprojects.collect { proj ->
            variants.collect { variant ->
                "${proj.buildDir}/intermediates/javac/$variant/classes"
            }
        }.flatten()
        def kClasses = subprojects.collect { proj ->
            variants.collect { variant ->
                "${proj.buildDir}/tmp/kotlin-classes/$variant"
            }
        }.flatten()

        def javaClasses = jClasses.collect { path ->
            fileTree(dir: path, excludes: excludes)
        }
        def kotlinClasses = kClasses.collect { path ->
            fileTree(dir: path, excludes: excludes)
        }

        classDirectories.from = files([javaClasses, kotlinClasses])
        def sources = subprojects.collect { proj ->
            variants.collect { variant ->
                ["${proj.projectDir}/src/main/java", "${proj.projectDir}/src/main/kotlin",
                 "${proj.projectDir}/src/$variant/java", "${proj.projectDir}/src/$variant/kotlin"]
            }.flatten()
        }.flatten()
        sourceDirectories.from = files(sources)

        def executions = subprojects.collect { proj ->
            variants.collect { variant ->
                def path = "${proj.buildDir}/jacoco/test${variant.capitalize()}UnitTest.exec"
                if ((new File(path)).exists()) path else null
            }
        }.flatten()
        executions.removeAll([null])

        executionData.from = files(executions)
    }
}
