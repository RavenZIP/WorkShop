// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose.compiler) apply false
    kotlin("jvm") version "2.0.0"
}
