import dependencies.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.mx.android.database"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
}

dependencies {

    implementation(project(":core:common"))

    // Kotlin
    implementation(Dependencies.Kotlin.Androidx.core)

    //Hilt
    implementation(Dependencies.JetPack.Hilt.hilt)
    kapt(Dependencies.JetPack.Hilt.hilt_compiler)

    //Room
    implementation(Dependencies.JetPack.Room.room_runtime)
    implementation(Dependencies.JetPack.Room.room_ktx)
    annotationProcessor(Dependencies.JetPack.Room.room_compiler)
    kapt(Dependencies.JetPack.Room.room_compiler)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}