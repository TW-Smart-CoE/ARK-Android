apply from: "${rootDir}/config/jacoco/jacocoConfig.kts"

project.afterEvaluate {

    def variants = (android.hasProperty('applicationVariants') ? android.'applicationVariants' : android.'libraryVariants')
    variants.all { variant ->
        def variantName = variant.name
        def testTaskName = "test${variantName.capitalize()}UnitTest"
        tasks.create(name: "${variantName}Coverage", type: JacocoReport, dependsOn: testTaskName) {

            group = "Reporting"
            description = "Generate Jacoco coverage reports for the ${variantName} build."

            reports {
                xml {
                    enabled true
                }
                html {
                    enabled true
                }
            }

            def excludes = project.ext.jacocoFileFilter

            def jClasses = "${project.buildDir}/intermediates/javac/${variantName}/classes"
            def kClasses = "${project.buildDir}/tmp/kotlin-classes/${variantName}"
            def javaClasses = fileTree(dir: jClasses, excludes: excludes)

            def kotlinClasses = fileTree(dir: kClasses, excludes: excludes)

            classDirectories.from = files([javaClasses, kotlinClasses])
            def sourceDirs = ["${project.projectDir}/src/main/java", "${project.projectDir}/src/main/kotlin",
                              "${project.projectDir}/src/${variantName}/java", "${project.projectDir}/src/${variantName}/kotlin"]

            sourceDirectories.from = files(sourceDirs)

            executionData.from = files(["${project.buildDir}/jacoco/${testTaskName}.exec"])
        }
    }

}
