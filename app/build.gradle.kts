plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.vladan.newsapp"
    compileSdk = Version.max_sdk

    defaultConfig {
        applicationId = "com.vladan.newsapp"
        minSdk = Version.min_sdk
        targetSdk = Version.max_sdk
        versionCode = 1
        versionName = "1.0"

        setProperty("archivesBaseName", "$versionName")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true
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
        dataBinding = true

    }

}

dependencies {
    common()
    ktx()
    networking()
    hilt()
    picasso()
    googlePlaces()
    room()
    test()
}

hilt {
    enableAggregatingTask = true
}

kapt {
    correctErrorTypes = true
}