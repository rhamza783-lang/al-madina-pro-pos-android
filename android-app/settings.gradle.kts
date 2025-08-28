pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.2.2" apply false
        id("org.jetbrains.kotlin.android") version "1.9.0" apply false
        id("org.jetbrains.kotlin.kapt") version "1.9.0" apply false
        id("com.google.dagger.hilt.android") version "2.56.2" apply false
    }
}

// ✅ Force Kotlin version
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        libs {
            version("kotlin", "1.9.0")
            library("kotlin-stdlib").to("org.jetbrains.kotlin", "kotlin-stdlib").version("kotlin")
            library("kotlin-test").to("org.jetbrains.kotlin", "kotlin-test").version("kotlin")
        }
    }
}

include(":app")
