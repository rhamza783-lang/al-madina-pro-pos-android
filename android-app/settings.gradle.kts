pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        // A stable, widely-used version of the Android Gradle Plugin
        id("com.android.application") version "8.2.2" apply false
        // The exact Kotlin version compatible with our Compose Compiler
        id("org.jetbrains.kotlin.android") version "1.9.22" apply false
        // The stable Kapt plugin for the same Kotlin version
        id("org.jetbrains.kotlin.kapt") version "1.9.22" apply false
        // A stable version of Hilt
        id("com.google.dagger.hilt.android") version "2.48" apply false
        // THE MISSING PARCELIZE PLUGIN
        id("org.jetbrains.kotlin.plugin.parcelize") version "1.9.22" apply false
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "AlMadinaPOS"
include(":app")
