package dependencies

object Dependencies {

    object Kotlin {

        object Androidx {
            const val core = "androidx.core:core-ktx:${Versions.Kotlin.androidx}"
        }

        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
        }
    }

    object JetPack {

        object Compose {
            const val bom = "androidx.compose:compose-bom:${Versions.JetPack.compose_boom}"
            const val material = "androidx.compose.material3:material3:${Versions.JetPack.material}"
            const val material_icons = "androidx.compose.material:material-icons-extended:${Versions.JetPack.compose}"
            const val compose_activity = "androidx.activity:activity-compose:${Versions.JetPack.compose_activity}"
            const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.JetPack.compose_lifecycle}"
            const val livedata = "androidx.compose.runtime:runtime-livedata:${Versions.JetPack.compose_livedata}"
            const val constraint = "androidx.constraintlayout:constraintlayout-compose:${Versions.JetPack.constraint_layout}"
            const val preview = "androidx.compose.ui:ui-tooling-preview:${Versions.JetPack.compose_tooling}"
            const val preview_debug = "androidx.compose.ui:ui-tooling:${Versions.JetPack.compose_tooling}"
        }

        object LifeCycle {
            const val lifecycle_vm = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.JetPack.lifecycle}"
            const val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.JetPack.lifecycle}"
        }

        object Paging {
            const val paging_compose = "androidx.paging:paging-compose:${Versions.JetPack.paging}"
        }

        object UI {
            const val appcompat = "androidx.appcompat:appcompat:${Versions.JetPack.appcompat}"
        }

        object Room {
            const val room_runtime = "androidx.room:room-runtime:${Versions.JetPack.room}"
            const val room_compiler = "androidx.room:room-compiler:${Versions.JetPack.room}"
            const val room_ktx = "androidx.room:room-ktx:${Versions.JetPack.room}"
        }

        object Navigation {
            const val nav_compose = "androidx.navigation:navigation-compose:${Versions.JetPack.nav}"
            const val hilt_nav = "androidx.hilt:hilt-navigation-compose:${Versions.JetPack.hilt_nav}"
        }

        object Hilt {
            const val hilt = "com.google.dagger:hilt-android:${Versions.JetPack.hilt}"
            const val hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.JetPack.hilt}"
        }
    }

    object ExternalLibs {
        object Retrofit {
            const val core = "com.squareup.retrofit2:retrofit:${Versions.ExternalLibs.retrofit}"
            const val converter = "com.squareup.retrofit2:converter-gson:${Versions.ExternalLibs.retrofit}"
            const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.ExternalLibs.interceptor}"
            const val adapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.ExternalLibs.retrofit_coroutine_adapter}"
        }

        object Lottie {
            const val core = "com.airbnb.android:lottie:${Versions.ExternalLibs.lottie}"
        }
        object Image {
            const val coil = "io.coil-kt:coil-compose:${Versions.ExternalLibs.coil}"
            const val glide = "com.github.bumptech.glide:compose:${Versions.ExternalLibs.glide}"
        }
    }
}