pluginManagement {
    buildscript {
        repositories {
            gradlePluginPortal()
            mavenCentral()
        }
    }
    val kotlinVersion: String by extra
    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        id("org.springframework.boot") version extra["springBootVersion"].toString()
        id("io.spring.dependency-management") version extra["springDependencyManagement"].toString()
        id("com.github.ben-manes.versions") version extra["gradleVersionsPluginVersion"] as String
    }
}
val projectName: String by extra
rootProject.name = projectName
include(
    ":apps:consumer"
)
