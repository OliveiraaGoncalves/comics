@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.comics.core_network"
    compileSdk = 34

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        buildTypes {
            getByName("debug") {
                buildConfigField("String", "HOST", "\"https://gateway.marvel.com/v1/public/\"")
                buildConfigField("String", "TS", "\"1682982412\"")
                buildConfigField("String", "APIKEY", "\"b7e14bab409c70a5c4e7c2b319c09d7b\"")
                buildConfigField("String", "HASH", "\"3482f01e9bf207a437a4b621c91339ad\"")
            }
            getByName("release") {
                buildConfigField("String", "HOST", "\"https://gateway.marvel.com/v1/public/\"")
                buildConfigField("String", "TS", "\"1682982412\"")
                buildConfigField("String", "APIKEY", "\"b7e14bab409c70a5c4e7c2b319c09d7b\"")
                buildConfigField("String", "HASH", "\"3482f01e9bf207a437a4b621c91339ad\"")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.networking)
}