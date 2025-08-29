pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "8.5.0" apply false
        id("org.jetbrains.kotlin.android") version "1.9.23" apply false
        id("com.google.dagger.hilt.android") version "2.51.1" apply false
        id("com.google.devtools.ksp") version "1.9.23-1.0.20" apply false
        id("org.jetbrains.kotlin.plugin.parcelize") version "1.9.23" apply false
    }
}
// ... (rest of the file is the same)
