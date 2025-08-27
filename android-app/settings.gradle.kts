pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.2.2"
        id("org.jetbrains.kotlin.android") version "1.9.0"
        id("org.jetbrains.kotlin.kapt") version "1.9.0"
        id("com.google.dagger.hilt.android") version "2.56.2" // ✅ Add Hilt plugin
    }
}

include(":app")
