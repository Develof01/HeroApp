package com.mx.android.common.constants

object Generals {

    const val App_NAME ="HeroComposeApp"

    object RoomConstants {
        const val DB_VERSION = (1.0).toInt()
        const val DB_NAME = "hero-app-db"
    }

    object NetworkConstants {
        const val TIMEOUT: Long = 10
        const val TS = 1.toString()
    }
}