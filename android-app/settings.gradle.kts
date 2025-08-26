pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.2.2" apply false
        // --- THIS IS THE FINAL FIX ---
        id("org.jetbrains.kotlin.android") version "1.9.22" apply false
        id("org.jetbrains.kotlin.kapt") version "1.9.22" apply false
        id("com.google.dagger.hilt.android") version "2.48" apply false
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
