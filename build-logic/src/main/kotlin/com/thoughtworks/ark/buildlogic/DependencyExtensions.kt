package com.thoughtworks.ark.buildlogic

import com.thoughtworks.ark.buildlogic.MavenConfig.MAVEN_GROUP_ID
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.create

fun DependencyHandler.autoImplementation(
    name: String,
    version: String,
    group: String = MAVEN_GROUP_ID
) {
    add("devImplementation", create(group, "$name-dev", "$version-SNAPSHOT"))
    add("uatImplementation", create(group, "$name-uat", "$version-SNAPSHOT"))
    add("stagingImplementation", create(group, "$name-staging", "$version-SNAPSHOT"))
    add("prodImplementation", create(group, "$name-prod", version))
}

fun DependencyHandler.autoApi(
    name: String,
    version: String,
    group: String = MAVEN_GROUP_ID
) {
    add("devApi", create(group, "$name-dev", "$version-SNAPSHOT"))
    add("uatApi", create(group, "$name-uat", "$version-SNAPSHOT"))
    add("stagingApi", create(group, "$name-staging", "$version-SNAPSHOT"))
    add("prodApi", create(group, "$name-prod", version))
}
