pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        // Declare Android Application Plugin with version
        id("com.android.application") version "8.0.2"
    }
}

// Include your app module
include(":app")

// Optional: If you have other modules
// include(":feature:login")
// include(":core")

// If using Kotlin, also declare Kotlin plugin
// plugins {
//     id("org.jetbrains.kotlin.android") version "1.9.0"
// }
