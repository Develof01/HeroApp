package com.mx.android.network.dto.response.hero

data class Hero(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: HeroThumbnail
)
