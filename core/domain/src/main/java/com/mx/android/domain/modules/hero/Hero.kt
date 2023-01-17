package com.mx.android.domain.modules.hero


data class Hero(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: HeroThumbnail,
    var isFavorite: Boolean = false
)
