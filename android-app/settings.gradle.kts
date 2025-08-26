pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.2.2" apply false
        id("org.jetbrains.kotlin.android") version "1.8.10" apply false
        id("org.jetbrains.kotlin.kapt") version "1.8.10" apply false
        id("com.google.dagger.hilt.android") version "2.48" apply false
        id("org.jetbrains.kotlin.plugin.parcelize") version "1.8.10" apply false // <-- FINAL PLUGIN ADDED
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AlMadinaPOS"
include(":app")
