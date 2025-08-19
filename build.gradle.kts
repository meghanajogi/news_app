// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()  // Required for Hilt and other Android dependencies
        mavenCentral()  // A fallback repository
    }
    dependencies {
        // Make sure you're using a stable version of the Hilt Gradle plugin
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.49")
    }
}

plugins {
    // Apply Kotlin, Android, and Hilt plugin versions
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.android.library") version "8.12.0" apply false
    id("com.google.dagger.hilt.android") version "2.49" apply false  // Hilt plugin version
}