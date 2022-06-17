plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp") version "1.6.21-1.0.5"
}

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

android {
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = Android.appId
        minSdk = Android.minSdk
//        multiDexEnabled = Android.multiDexEnabled
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = Android.androidJUnitRunner
        vectorDrawables {
            useSupportLibrary = Android.useSupportLibrary
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-beta03"
    }
    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies {
    val composeVersion = "1.2.0-beta03"
    val accompanistVersion = "0.24.2-alpha"

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.lifecycleKtx)
    implementation(AndroidX.lifecycleVmKtx)
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")

    // Compose dependencies
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation("androidx.navigation:navigation-compose:2.5.0-rc02")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("com.google.accompanist:accompanist-flowlayout:0.17.0")
    implementation("androidx.paging:paging-compose:1.0.0-alpha15")
    implementation("androidx.activity:activity-compose:1.6.0-alpha03")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.24.2-alpha")

    // Coil
    implementation("io.coil-kt:coil-compose:1.3.2")
    implementation("io.coil-kt:coil-svg:1.3.2")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-native-mt")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.40.5")
    kapt("com.google.dagger:hilt-android-compiler:2.40.5")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.5")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.5")

    // Scarlet
    val scarletVersion = "0.1.12"
    implementation("com.tinder.scarlet:scarlet:$scarletVersion")
    implementation("com.tinder.scarlet:websocket-okhttp:$scarletVersion")
    implementation("com.tinder.scarlet:lifecycle-android:$scarletVersion")
    implementation("com.tinder.scarlet:message-adapter-gson:$scarletVersion")
    implementation("com.tinder.scarlet:stream-adapter-coroutines:$scarletVersion")

    // Ktor client
    implementation("io.ktor:ktor-client-core:2.0.2")
    implementation("io.ktor:ktor-client-cio:2.0.2")
    implementation("io.ktor:ktor-client-gson:1.6.5")
    implementation("io.ktor:ktor-client-logging:1.6.5")
    implementation("ch.qos.logback:logback-classic:1.2.11")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // uCrop
//    implementation("com.github.yalantis:ucrop:2.2.8")

    // Local Unit Tests
    implementation("androidx.test:core:1.4.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.hamcrest:hamcrest-all:1.3")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.robolectric:robolectric:4.5.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")
    testImplementation("io.mockk:mockk:1.10.5")
    testImplementation("org.robolectric:robolectric:4.5.1")
//    testImplementation("com.github.blocoio:faker:1.2.9")

    androidTestImplementation("com.google.dagger:hilt-android-testing:2.38.1")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.40.5")

    // Instrumented Unit Tests
    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("com.google.truth:truth:1.1.3")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test:core-ktx:1.4.0")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")
    androidTestImplementation("io.mockk:mockk-android:1.10.5")
//    androidTestImplementation("com.github.blocoio:faker:1.2.9")

    // Compose Nav Destinations
    implementation("io.github.raamcosta.compose-destinations:core:1.5.8-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.5.8-beta")

    // Room
    implementation("androidx.room:room-runtime:2.4.2")
    kapt("androidx.room:room-compiler:2.4.2")

    // Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.4.2")

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Pager and Indicators - Accompanist
    implementation("com.google.accompanist:accompanist-pager:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-pager-indicators:$accompanistVersion")

    // Faker
    implementation("com.github.blocoio:faker:1.2.9")

    // Swipe
    implementation("me.saket.swipe:swipe:1.0.0")

}