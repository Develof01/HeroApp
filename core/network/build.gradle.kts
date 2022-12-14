import dependencies.Dependencies
import java.util.Properties
import java.io.File
import java.io.FileInputStream


plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.mx.android.network"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        val properties = Properties().apply {
            load(FileInputStream(File(rootProject.rootDir, "local.properties")))
        }

        buildConfigField("String", "API_KEY", "\"${properties.getProperty("API_KEY")}\"")
        buildConfigField("String", "URL_BASE", "\"${properties.getProperty("URL_BASE")}\"")
        buildConfigField("String", "API_PRIVATE_KEY", "\"${properties.getProperty("API_PRIVATE_KEY")}\"")
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
    implementation(project(":core:domain"))

    // Kotlin
    implementation(Dependencies.Kotlin.Androidx.core)
    implementation(Dependencies.Kotlin.Coroutines.core)
    implementation(Dependencies.Kotlin.Coroutines.android)

    //Hilt
    implementation(Dependencies.JetPack.Hilt.hilt)
    kapt(Dependencies.JetPack.Hilt.hilt_compiler)

    //Retrofit
    implementation(Dependencies.ExternalLibs.Retrofit.core)
    implementation(Dependencies.ExternalLibs.Retrofit.converter)
    implementation(Dependencies.ExternalLibs.Retrofit.adapter)
    implementation(Dependencies.ExternalLibs.Retrofit.interceptor)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}