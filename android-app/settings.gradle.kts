pluginManagement {
    repositories {
        google()
        mavenCentral() // ✅ Required for KSP
        gradlePluginPortal() // ✅ Required for Gradle plugins
    }
    plugins {
        id("com.android.application") version "8.7.0" apply false
        id("org.jetbrains.kotlin.android") version "2.0.20" apply false
        id("org.jetbrains.kotlin.kapt") version "2.0.20" apply false
        id("com.google.dagger.hilt.android") version "2.56.2" apply false
        id("org.jetbrains.kotlin.plugin.compose") version "2.0.20" apply false
        // ✅ KSP: Use exact version that exists on Maven Central
        id("com.google.devtools.ksp") version "2.0.20-1.0.27" apply false
    }
}

// ✅ Add this block: Ensure repositories are visible to plugin resolution
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":app")
