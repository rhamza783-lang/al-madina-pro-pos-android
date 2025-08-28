pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.5.0" apply false
        id("org.jetbrains.kotlin.android") version "2.1.10" apply false
        id("org.jetbrains.kotlin.kapt") version "2.1.10" apply false
        id("com.google.dagger.hilt.android") version "2.56.2" apply false
        // ✅ Add Compose Compiler Plugin
        id("org.jetbrains.kotlin.plugin.compose") version "2.1.10" apply false
    }
}

include(":app")
