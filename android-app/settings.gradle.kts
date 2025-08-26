// This top block is for defining the versions of the BUILD PLUGINS
pluginManagement {
    // This tells Gradle where to find the plugins themselves
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    // This is the new, crucial part. We define the plugin versions here.
    plugins {
        id("com.android.application") version "8.2.2" apply false
        id("org.jetbrains.kotlin.android") version "1.8.10" apply false
        id("com.google.dagger.hilt.android") version "2.48" apply false
    }
}

// This block is for defining the versions of your APP LIBRARIES
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AlMadinaPOS"
include(":app")
