apply plugin: 'jacoco'

jacoco {
    toolVersion '0.8.7'
}

tasks.withType(Test) {
    jacoco.includeNoLocationClasses = true
    jacoco.excludes = ['jdk.internal.*']
}

def defaultFileFilter =  [
        // android
        '**/R.class',
        '**/R$*.class',
        '**/BuildConfig.*',
        '**/Manifest*.*',
        'android/**/*.*',
        'androidx/**/*.*',
        '**/*Test*.*',
        // dagger + hilt
        '**/Hilt_*.*',
        '**/*_HiltModules*.*',
        '**/*_Provide*Factory*.*',
        '**/*_Factory*.*',
        '**/*_GeneratedInjector.*',
        'dagger/hilt/**/codegen/*.*',
        'hilt_aggregated_deps/*.*',
        '**/AutoValue_*.*',
        '**/*Directions$*',
        '**/*Directions.*',
        // custom
        '**/ui/**',
        '**/di/**',
        '**/entity/**',
        '**/model/**',
        '**/developmenu/**',
        '**/*Application*.*',
        '**/MainActivity*.*',
]


def moduleFilter = [
        'com/thoughtworks/ark/core/*',
        'com/thoughtworks/ark/ui/*'
]

defaultFileFilter.addAll(moduleFilter)

project.ext.setProperty("jacocoFileFilter", defaultFileFilter)
