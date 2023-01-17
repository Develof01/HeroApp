import dependencies.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.mx.android.dashboard"
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }

}

dependencies {

    val composeBom = platform(Dependencies.JetPack.Compose.bom)

    implementation(project (":core:ui"))
    implementation(project (":core:common"))
    implementation(project(":features:heros"))

    // Kotlin
    implementation(Dependencies.Kotlin.Androidx.core)
    implementation(Dependencies.Kotlin.Coroutines.core)
    implementation(Dependencies.Kotlin.Coroutines.android)

    //Compose Library
    implementation(composeBom)
    implementation(Dependencies.JetPack.Compose.compose_activity)
    implementation(Dependencies.JetPack.Compose.material)
    implementation(Dependencies.JetPack.Compose.material_icons)
    implementation(Dependencies.JetPack.Compose.constraint)
    implementation(Dependencies.JetPack.Compose.lifecycle)
    implementation(Dependencies.JetPack.Compose.livedata)
    implementation(Dependencies.JetPack.Compose.preview)
    debugImplementation(Dependencies.JetPack.Compose.preview_debug)

    //Lifecycle
    implementation(Dependencies.JetPack.LifeCycle.lifecycle_vm)
    implementation(Dependencies.JetPack.LifeCycle.lifecycle_livedata)

    //Hilt
    implementation(Dependencies.JetPack.Hilt.hilt)
    kapt(Dependencies.JetPack.Hilt.hilt_compiler)

    //Navigation
    implementation(Dependencies.JetPack.Navigation.nav_compose)
    implementation(Dependencies.JetPack.Navigation.hilt_nav)

    //Lottie
    implementation(Dependencies.ExternalLibs.Lottie.core)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
}