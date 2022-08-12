apply plugin: 'jacoco'

jacoco {
    toolVersion '0.8.7'
}

tasks.withType(Test) {
    jacoco.includeNoLocationClasses = true
    jacoco.excludes = ['jdk.internal.*']
}

def defaultFileFilter =  [
        '**/R.class',
        '**/R$*.class',
        '**/BuildConfig.*',
        '**/Manifest*.*',
        '**/*Test*.*',
        'android/**/*.*',
        'androidx/**/*.*',
        '**/*$ViewInjector*.*',
        '**/*Dagger*.*',
        '**/*MembersInjector*.*',
        '**/*_Factory.*',
        '**/*_Provide*Factory*.*',
        '**/*_ViewBinding*.*',
        '**/AutoValue_*.*',
        '**/R2.class',
        '**/R2$*.class',
        '**/*Directions$*',
        '**/*Directions.*',
        '**/*Binding.*'
]


def moduleFilter = [
        'com/thoughtworks/android/core/*',
        'com/thoughtworks/android/ark/ui/*'
]

defaultFileFilter.addAll(moduleFilter)

project.ext.setProperty("jacocoFileFilter", defaultFileFilter)








