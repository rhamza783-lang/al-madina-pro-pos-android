pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.3.0" // ✅ Upgrade to 8.3.0
        id("org.jetbrains.kotlin.android") version "1.9.24" // ✅ Use latest 1.9.x
        id("org.jetbrains.kotlin.kapt") version "1.9.24"
        id("com.google.dagger.hilt.android") version "2.56.2"
    }
}

include(":app")
