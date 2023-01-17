import dependencies.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.mx.android.ui"
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
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

    val composeBom = platform(Dependencies.JetPack.Compose.bom)

    implementation(Dependencies.Kotlin.Androidx.core)

    //Compose Library
    implementation(composeBom)
    implementation(Dependencies.JetPack.Compose.compose_activity)
    implementation(Dependencies.JetPack.Compose.material)
    implementation(Dependencies.JetPack.Compose.material_icons)
    implementation(Dependencies.JetPack.Compose.constraint)
    implementation(Dependencies.JetPack.Compose.lifecycle)
    implementation(Dependencies.JetPack.Compose.preview)
    debugImplementation(Dependencies.JetPack.Compose.preview_debug)

    //Lifecycle
    implementation(Dependencies.JetPack.LifeCycle.lifecycle_vm)
    implementation(Dependencies.JetPack.LifeCycle.lifecycle_livedata)


    //Navigation
    implementation(Dependencies.JetPack.Navigation.nav_compose)

    //Hilt
    implementation(Dependencies.JetPack.Hilt.hilt)
    kapt(Dependencies.JetPack.Hilt.hilt_compiler)


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
}