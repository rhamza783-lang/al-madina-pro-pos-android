pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        // Define all our plugins with compatible versions
        id("com.android.application") version "8.5.0" apply false
        id("org.jetbrains.kotlin.android") version "1.9.23" apply false // Use a stable KSP-compatible version
        id("com.google.dagger.hilt.android") version "2.51.1" apply false // Hilt version compatible with KSP
        id("com.google.devtools.ksp") version "1.9.23-1.0.20" apply false // <-- KSP PLUGIN ADDED
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
