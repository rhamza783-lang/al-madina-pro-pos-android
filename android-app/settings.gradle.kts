pluginManagement {
    repositories {
        google()
        mavenCentral() // ✅ Required for KSP plugin
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.7.0" apply false
        id("org.jetbrains.kotlin.android") version "2.0.20" apply false
        id("org.jetbrains.kotlin.kapt") version "2.0.20" apply false
        id("com.google.dagger.hilt.android") version "2.56.2" apply false
        id("org.jetbrains.kotlin.plugin.compose") version "2.0.20" apply false
        id("com.google.devtools.ksp") version "2.0.20-1.0.27" apply false // ✅ KSP
    }
}

include(":app")
