buildscript {
    val androidBuildToolsVersion = "7.2.1"
    val kotlinVersion = "1.6.21"

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:$androidBuildToolsVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}// Top-level build file where you can add configuration options common to all sub-projects/modules.

/*plugins {
    id 'com.android.application' version '7.2.1' apply false
    id 'com.android.library' version '7.2.1' apply false
    id 'org.jetbrains.kotlin.android' version '1.5.31' apply false
    id 'org.jetbrains.kotlin.jvm' version '1.6.21' apply false
}*/

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}