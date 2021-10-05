// global multi-module configurations to be applied for all sub-projects

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("com.github.ben-manes.versions")
    id("io.spring.dependency-management")
}

allprojects {
    // java configuration
    apply<JavaPlugin>()

    val javaVersion: String by project
    java.sourceCompatibility = JavaVersion.toVersion(javaVersion)

    repositories {
        mavenCentral()
    }

    // dependency management configuration
    apply<io.spring.gradle.dependencymanagement.DependencyManagementPlugin>()

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    // kotlin configuration
    apply<org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper>()

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            val freeCompilerArg: String by project
            freeCompilerArgs = listOf(freeCompilerArg)
            jvmTarget = javaVersion
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            showCauses = true
            showExceptions = true
            showStackTraces = true
            showStandardStreams = true
        }
    }

    // default tasks
    defaultTasks("clean", "build")

    // dependency updates
    apply<com.github.benmanes.gradle.versions.VersionsPlugin>()

    tasks.named<com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask>("dependencyUpdates") {
        resolutionStrategy {
            componentSelection {
                all {
                    val rejected = listOf("m", "SNAPSHOT")
                        .map { qualifier -> Regex("(?i).*[.-]$qualifier[.\\d-+]*") }
                        .any { it.matches(candidate.version) }
                    if (rejected) reject("Release candidate")
                }
            }
        }
        outputFormatter = "plain" // "json"
    }
}
