// This top part tells Gradle WHERE to find the plugins
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

// This part tells Gradle where to find your app's libraries
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

// This is your original code
rootProject.name = "AlMadinaPOS"
include(":app")
